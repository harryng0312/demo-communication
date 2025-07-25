package org.harryng.demo.security;

import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.generators.X25519KeyPairGenerator;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.X25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.X25519PublicKeyParameters;
import org.junit.jupiter.api.Test;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;

//@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Import(Application.class)
//@ClientEndpoint
@Slf4j
public class TestSignalProtocol {
    // Constants
    private static final int AES_KEY_LENGTH = 32; // 256 bits
    private static final int GCM_NONCE_LENGTH = 12; // 96 bits
    private static final int GCM_TAG_LENGTH = 128; // 128-bit authentication tag
    private static final byte[] HKDF_INFO = "SignalProtocol".getBytes(StandardCharsets.UTF_8);
    private static final int X25519KEY_LEN = 256;

    // Key storage for Alice and Bob
    static class User {
        X25519PrivateKeyParameters identityPrivateKey;
        X25519PublicKeyParameters identityPublicKey;
        X25519PrivateKeyParameters ephemeralPrivateKey;
        X25519PublicKeyParameters ephemeralPublicKey;
        byte[] rootKey;
        byte[] chainKey;
        int messageCounter;

        User() {
            this.messageCounter = 0;
            generateIdentityKeyPair();
        }

        void generateIdentityKeyPair() {
            final KeyGenerationParameters keyGenerationParameters = new KeyGenerationParameters(new SecureRandom(), X25519KEY_LEN);
            final X25519KeyPairGenerator keyGen = new X25519KeyPairGenerator();
            keyGen.init(keyGenerationParameters);
            final AsymmetricCipherKeyPair keyPair = keyGen.generateKeyPair();
            identityPrivateKey = (X25519PrivateKeyParameters) keyPair.getPrivate();
            identityPublicKey = (X25519PublicKeyParameters) keyPair.getPublic();
        }

        void generateEphemeralKeyPair() {
            final KeyGenerationParameters keyGenerationParameters = new KeyGenerationParameters(new SecureRandom(), X25519KEY_LEN);
            final X25519KeyPairGenerator keyGen = new X25519KeyPairGenerator();
            keyGen.init(keyGenerationParameters);
            final AsymmetricCipherKeyPair keyPair = keyGen.generateKeyPair();
            ephemeralPrivateKey = (X25519PrivateKeyParameters) keyPair.getPrivate();
            ephemeralPublicKey = (X25519PublicKeyParameters) keyPair.getPublic();
        }
    }

    // Simulate HKDF (simplified for demo)
    private static byte[] hkdf(byte[] inputKey, byte[] info) {
        final HMac hMac = new HMac(new SHA256Digest());
        hMac.init(new KeyParameter(inputKey));
        final byte[] output = new byte[32];
        hMac.update(info, 0, info.length);
        hMac.doFinal(output, 0);
        return output;
    }

    // Perform Diffie-Hellman key exchange
    private static byte[] dhExchange(X25519PrivateKeyParameters privateKey, X25519PublicKeyParameters publicKey) {
        final byte[] sharedSecret = new byte[32];
        privateKey.generateSecret(publicKey, sharedSecret, 0);
        return sharedSecret;
    }

    // X3DH key agreement (simplified)
    private static void performX3DH(User alice, User bob) {
        // Alice generates ephemeral key pair
        alice.generateEphemeralKeyPair();
        // Bob generates prekey (for simplicity, using ephemeral key pair)
        bob.generateEphemeralKeyPair();

        // X3DH steps (simplified: DH1 = AliceIdentity + BobPrekey, DH2 = AliceEphemeral + BobIdentity, DH3 = AliceEphemeral + BobPrekey)
        final byte[] dh1 = dhExchange(alice.identityPrivateKey, bob.ephemeralPublicKey);
        final byte[] dh2 = dhExchange(alice.ephemeralPrivateKey, bob.identityPublicKey);
        final byte[] dh3 = dhExchange(alice.ephemeralPrivateKey, bob.ephemeralPublicKey);

        // Combine DH outputs (simplified concatenation for demo)
        final byte[] combined = new byte[dh1.length + dh2.length + dh3.length];
        System.arraycopy(dh1, 0, combined, 0, dh1.length);
        System.arraycopy(dh2, 0, combined, dh1.length, dh2.length);
        System.arraycopy(dh3, 0, combined, dh1.length + dh2.length, dh3.length);

        // Derive root key and initial chain key
        alice.rootKey = hkdf(combined, HKDF_INFO);
        bob.rootKey = alice.rootKey; // In practice, Bob performs same DH calculations
        alice.chainKey = hkdf(alice.rootKey, "ChainKey".getBytes());
        bob.chainKey = alice.chainKey;
    }

    // Symmetric Ratchet: Derive message key and update chain key
    private static byte[][] symmetricRatchet(byte[] chainKey) {
        final byte[] messageKey = hkdf(chainKey, "MessageKey".getBytes());
        final byte[] nextChainKey = hkdf(chainKey, "NextChainKey".getBytes());
        return new byte[][] {messageKey, nextChainKey};
    }

    // DH Ratchet: Update root key and chain key
    private static void dhRatchet(User sender, User receiver) {
        // Sender generates new ephemeral key pair
        sender.generateEphemeralKeyPair();
        
        // Perform DH with receiver's previous ephemeral public key
        final byte[] sharedSecret = dhExchange(sender.ephemeralPrivateKey, receiver.ephemeralPublicKey);
        
        // Update root key
        sender.rootKey = hkdf(sender.rootKey, sharedSecret);
        receiver.rootKey = sender.rootKey; // In practice, receiver performs same DH
        
        // Derive new chain key
        sender.chainKey = hkdf(sender.rootKey, "ChainKey".getBytes());
        receiver.chainKey = sender.chainKey;
    }

    // Encrypt message using AES-GCM
    private static byte[] encryptMessage(byte[] messageKey, String message, int counter) throws Exception {
        final Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        byte[] nonce = new byte[GCM_NONCE_LENGTH];
        final SecureRandom random = new SecureRandom();
        random.nextBytes(nonce);

        final SecretKeySpec keySpec = new SecretKeySpec(messageKey, "AES");
        final GCMParameterSpec gcmSpec = new GCMParameterSpec(GCM_TAG_LENGTH, nonce);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmSpec);

        final byte[] plaintext = message.getBytes(StandardCharsets.UTF_8);
        final byte[] ciphertext = cipher.doFinal(plaintext);
        
        // Combine nonce and ciphertext
        final byte[] result = new byte[nonce.length + ciphertext.length];
        System.arraycopy(nonce, 0, result, 0, nonce.length);
        System.arraycopy(ciphertext, 0, result, nonce.length, ciphertext.length);
        return result;
    }

    // Decrypt message using AES-GCM
    private static String decryptMessage(byte[] messageKey, byte[] encryptedMessage, int counter) throws Exception {
        final Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        final byte[] nonce = Arrays.copyOfRange(encryptedMessage, 0, GCM_NONCE_LENGTH);
        final byte[] ciphertext = Arrays.copyOfRange(encryptedMessage, GCM_NONCE_LENGTH, encryptedMessage.length);

        final SecretKeySpec keySpec = new SecretKeySpec(messageKey, "AES");
        final GCMParameterSpec gcmSpec = new GCMParameterSpec(GCM_TAG_LENGTH, nonce);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmSpec);

        final byte[] plaintext = cipher.doFinal(ciphertext);
        return new String(plaintext, StandardCharsets.UTF_8);
    }

    @Test
    public void testDoubleRatchet() throws Exception {
        // Initialize Alice and Bob
        final User alice = new User();
        final User bob = new User();

        // Step 1: Perform X3DH to establish initial keys
        performX3DH(alice, bob);
        log.info("X3DH completed. Root Key established.");

        // Step 2: Alice sends a message to Bob
        String message1 = "Hello, Bob!";
        byte[][] keys = symmetricRatchet(alice.chainKey);
        alice.chainKey = keys[1]; // Update chain key
        byte[] encryptedMessage1 = encryptMessage(keys[0], message1, alice.messageCounter++);
        log.info("Alice sends encrypted message 1: " + Arrays.toString(encryptedMessage1));

        String message2 = "How are you? Bob";
        keys = symmetricRatchet(alice.chainKey);
        alice.chainKey = keys[1]; // Update chain key
        byte[] encryptedMessage2 = encryptMessage(keys[0], message2, alice.messageCounter++);
        log.info("Alice sends encrypted message 2: " + Arrays.toString(encryptedMessage2));

        // Step 3: Bob receives and decrypts the message
        keys = symmetricRatchet(bob.chainKey);
        bob.chainKey = keys[1]; // Update chain key
        String decryptedMessage1 = decryptMessage(keys[0], encryptedMessage1, bob.messageCounter++);
        log.info("Bob received and decrypted 1: " + decryptedMessage1);

        keys = symmetricRatchet(bob.chainKey);
        bob.chainKey = keys[1]; // Update chain key
        String decryptedMessage2 = decryptMessage(keys[0], encryptedMessage2, bob.messageCounter++);
        log.info("Bob received and decrypted 2: " + decryptedMessage2);

        // Step 4: Perform DH Ratchet (new session)
        dhRatchet(bob, alice);
        log.info("DH Ratchet performed. New root and chain keys established.");

        // Step 5: Bob sends a reply to Alice
        String message3 = "Hi, Alice!";
        keys = symmetricRatchet(bob.chainKey);
        bob.chainKey = keys[1];
        byte[] encryptedMessage3 = encryptMessage(keys[0], message3, bob.messageCounter++);
        log.info("Bob sends encrypted message: " + Arrays.toString(encryptedMessage3));

        // Step 6: Alice receives and decrypts the message
        keys = symmetricRatchet(alice.chainKey);
        alice.chainKey = keys[1];
        String decryptedMessage3 = decryptMessage(keys[0], encryptedMessage3, alice.messageCounter++);
        log.info("Alice received and decrypted: " + decryptedMessage3);
    }
}