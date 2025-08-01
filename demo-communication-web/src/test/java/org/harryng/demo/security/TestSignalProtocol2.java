package org.harryng.demo.security;

import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.X25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.X25519PublicKeyParameters;
import org.bouncycastle.crypto.generators.X25519KeyPairGenerator;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.junit.jupiter.api.Test;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;

@Slf4j
public class TestSignalProtocol2 {
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
        X25519PrivateKeyParameters signedPrekeyPrivate;
        X25519PublicKeyParameters signedPrekeyPublic;
        X25519PrivateKeyParameters oneTimePrekeyPrivate;
        X25519PublicKeyParameters oneTimePrekeyPublic;
        byte[] rootKey;
        byte[] sendingChainKey;
        byte[] receivingChainKey;
        int sendingMessageCounter;
        int receivingMessageCounter;

        User() {
            this.sendingMessageCounter = 0;
            this.receivingMessageCounter = 0;
            generateIdentityKeyPair();
            generateSignedPrekey();
            generateOneTimePrekey();
        }

        void generateIdentityKeyPair() {
            final KeyGenerationParameters keyGenerationParameters = new KeyGenerationParameters(new SecureRandom(), X25519KEY_LEN);
            X25519KeyPairGenerator keyGen = new X25519KeyPairGenerator();
            keyGen.init(keyGenerationParameters);
            AsymmetricCipherKeyPair keyPair = keyGen.generateKeyPair();
            identityPrivateKey = (X25519PrivateKeyParameters) keyPair.getPrivate();
            identityPublicKey = (X25519PublicKeyParameters) keyPair.getPublic();
        }

        void generateSignedPrekey() {
            final KeyGenerationParameters keyGenerationParameters = new KeyGenerationParameters(new SecureRandom(), X25519KEY_LEN);
            X25519KeyPairGenerator keyGen = new X25519KeyPairGenerator();
            keyGen.init(keyGenerationParameters);
            AsymmetricCipherKeyPair keyPair = keyGen.generateKeyPair();
            signedPrekeyPrivate = (X25519PrivateKeyParameters) keyPair.getPrivate();
            signedPrekeyPublic = (X25519PublicKeyParameters) keyPair.getPublic();
        }

        void generateOneTimePrekey() {
            final KeyGenerationParameters keyGenerationParameters = new KeyGenerationParameters(new SecureRandom(), X25519KEY_LEN);
            X25519KeyPairGenerator keyGen = new X25519KeyPairGenerator();
            keyGen.init(keyGenerationParameters);
            AsymmetricCipherKeyPair keyPair = keyGen.generateKeyPair();
            oneTimePrekeyPrivate = (X25519PrivateKeyParameters) keyPair.getPrivate();
            oneTimePrekeyPublic = (X25519PublicKeyParameters) keyPair.getPublic();
        }

        void generateEphemeralKeyPair() {
            final KeyGenerationParameters keyGenerationParameters = new KeyGenerationParameters(new SecureRandom(), X25519KEY_LEN);
            X25519KeyPairGenerator keyGen = new X25519KeyPairGenerator();
            keyGen.init(keyGenerationParameters);
            AsymmetricCipherKeyPair keyPair = keyGen.generateKeyPair();
            ephemeralPrivateKey = (X25519PrivateKeyParameters) keyPair.getPrivate();
            ephemeralPublicKey = (X25519PublicKeyParameters) keyPair.getPublic();
        }
    }

    // Simulate HKDF (simplified for demo)
    private static byte[] hkdf(byte[] inputKey, byte[] info) {
        HMac hMac = new HMac(new SHA256Digest());
        hMac.init(new KeyParameter(inputKey));
        byte[] output = new byte[32];
        hMac.update(info, 0, info.length);
        hMac.doFinal(output, 0);
        return output;
    }

    // Perform Diffie-Hellman key exchange
    private static byte[] dhExchange(X25519PrivateKeyParameters privateKey, X25519PublicKeyParameters publicKey) {
        byte[] sharedSecret = new byte[32];
        privateKey.generateSecret(publicKey, sharedSecret, 0);
        return sharedSecret;
    }

    // X3DH key agreement with Signed Prekey and One-Time Prekey
    private static void performX3DH(User alice, User bob) {
        // Alice generates ephemeral key pair
        alice.generateEphemeralKeyPair();

        // Alice uses Bob's identity key, signed prekey, and one-time prekey
        byte[] dh1 = dhExchange(alice.identityPrivateKey, bob.signedPrekeyPublic); // DH1: Alice Identity + Bob Signed Prekey
        byte[] dh2 = dhExchange(alice.ephemeralPrivateKey, bob.identityPublicKey); // DH2: Alice Ephemeral + Bob Identity
        byte[] dh3 = dhExchange(alice.ephemeralPrivateKey, bob.signedPrekeyPublic); // DH3: Alice Ephemeral + Bob Signed Prekey
        byte[] dh4 = dhExchange(alice.ephemeralPrivateKey, bob.oneTimePrekeyPublic); // DH4: Alice Ephemeral + Bob One-Time Prekey

        // Combine DH outputs
        byte[] combined = new byte[dh1.length + dh2.length + dh3.length + dh4.length];
        System.arraycopy(dh1, 0, combined, 0, dh1.length);
        System.arraycopy(dh2, 0, combined, dh1.length, dh2.length);
        System.arraycopy(dh3, 0, combined, dh1.length + dh2.length, dh3.length);
        System.arraycopy(dh4, 0, combined, dh1.length + dh2.length + dh3.length, dh4.length);

        // Derive root key and initial chain keys
        alice.rootKey = hkdf(combined, HKDF_INFO);
        bob.rootKey = alice.rootKey; // Bob will perform same calculation when receiving
        alice.sendingChainKey = hkdf(alice.rootKey, "ChainKey".getBytes());
        bob.receivingChainKey = alice.sendingChainKey;
        bob.sendingChainKey = hkdf(bob.rootKey, "ChainKey".getBytes());
        alice.receivingChainKey = bob.sendingChainKey;
    }

    // Symmetric Ratchet: Derive message key and update chain key
    private static byte[][] symmetricRatchet(byte[] chainKey) {
        byte[] messageKey = hkdf(chainKey, "MessageKey".getBytes());
        byte[] nextChainKey = hkdf(chainKey, "NextChainKey".getBytes());
        return new byte[][] {messageKey, nextChainKey};
    }

    // DH Ratchet: Update root key and chain key for sender and receiver
    private static void dhRatchet(User sender, User receiver, X25519PublicKeyParameters receiverPublicKey, boolean isSender) {
        // Sender generates new ephemeral key pair if sending
        if (isSender) {
            sender.generateEphemeralKeyPair();
        }

        // Perform DH with receiver's public key
        byte[] sharedSecret = dhExchange(sender.ephemeralPrivateKey, receiverPublicKey);
        sender.rootKey = hkdf(sender.rootKey, sharedSecret);
        receiver.rootKey = sender.rootKey;

        // Update chain keys: sender updates sending chain, receiver updates receiving chain
        if (isSender) {
            sender.sendingChainKey = hkdf(sender.rootKey, "ChainKey".getBytes());
            receiver.receivingChainKey = sender.sendingChainKey;
        } else {
            sender.receivingChainKey = hkdf(sender.rootKey, "ChainKey".getBytes());
            receiver.sendingChainKey = sender.receivingChainKey;
        }
    }

    // Encrypt message using AES-GCM
    private static byte[] encryptMessage(byte[] messageKey, String message, int counter, X25519PublicKeyParameters ephemeralPublicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        byte[] nonce = new byte[GCM_NONCE_LENGTH];
        SecureRandom random = new SecureRandom();
        random.nextBytes(nonce);

        SecretKeySpec keySpec = new SecretKeySpec(messageKey, "AES");
        GCMParameterSpec gcmSpec = new GCMParameterSpec(GCM_TAG_LENGTH, nonce);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmSpec);

        byte[] plaintext = message.getBytes(StandardCharsets.UTF_8);
        byte[] ciphertext = cipher.doFinal(plaintext);

        // Combine nonce, ciphertext, and ephemeral public key
        byte[] pubKeyBytes = ephemeralPublicKey.getEncoded();
        byte[] result = new byte[nonce.length + ciphertext.length + pubKeyBytes.length];
        System.arraycopy(nonce, 0, result, 0, nonce.length);
        System.arraycopy(ciphertext, 0, result, nonce.length, ciphertext.length);
        System.arraycopy(pubKeyBytes, 0, result, nonce.length + ciphertext.length, pubKeyBytes.length);
        return result;
    }

    // Decrypt message using AES-GCM
    private static String decryptMessage(byte[] messageKey, byte[] encryptedMessage, int counter) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        byte[] nonce = Arrays.copyOfRange(encryptedMessage, 0, GCM_NONCE_LENGTH);
        byte[] ciphertext = Arrays.copyOfRange(encryptedMessage, GCM_NONCE_LENGTH, encryptedMessage.length - 32); // 32 bytes for public key
        SecretKeySpec keySpec = new SecretKeySpec(messageKey, "AES");
        GCMParameterSpec gcmSpec = new GCMParameterSpec(GCM_TAG_LENGTH, nonce);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmSpec);

        byte[] plaintext = cipher.doFinal(ciphertext);
        return new String(plaintext, StandardCharsets.UTF_8);
    }

    // Extract ephemeral public key from message
    private static X25519PublicKeyParameters extractEphemeralPublicKey(byte[] encryptedMessage) {
        byte[] pubKeyBytes = Arrays.copyOfRange(encryptedMessage, encryptedMessage.length - 32, encryptedMessage.length);
        return new X25519PublicKeyParameters(pubKeyBytes, 0);
    }

    private static String getHex(byte[] bytes) {
        final StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    @Test
    public void testDoubleRatchet() throws Exception {
        // Initialize Alice and Bob
        User alice = new User();
        User bob = new User();

        // Step 1: Perform X3DH with Signed Prekey and One-Time Prekey
        performX3DH(alice, bob);
        log.info("X3DH completed with Signed Prekey and One-Time Prekey. Root Key established.");

        // Step 2: Alice sends first message to Bob
        String message1 = "Hello, Bob!";
        byte[][] keys = symmetricRatchet(alice.sendingChainKey);
        alice.sendingChainKey = keys[1]; // Update sending chain key
        byte[] encryptedMessage1 = encryptMessage(keys[0], message1, alice.sendingMessageCounter++, alice.ephemeralPublicKey);
        log.info("[Step 2] Alice sends first encrypted message: " + getHex(encryptedMessage1));

        // Step 3: Alice sends second message to Bob
        String message2 = "How are you?";
        keys = symmetricRatchet(alice.sendingChainKey);
        alice.sendingChainKey = keys[1]; // Update sending chain key
        byte[] encryptedMessage2 = encryptMessage(keys[0], message2, alice.sendingMessageCounter++, alice.ephemeralPublicKey);
        log.info("[Step 3] Alice sends second encrypted message: " + getHex(encryptedMessage2));

        // Step 4: Bob receives and decrypts the first message
        keys = symmetricRatchet(bob.receivingChainKey);
        bob.receivingChainKey = keys[1]; // Update receiving chain key
        String decryptedMessage1 = decryptMessage(keys[0], encryptedMessage1, bob.receivingMessageCounter++);
        log.info("[Step 4] Bob received and decrypted first message: " + decryptedMessage1);

        // Step 5: Bob receives and decrypts the second message
        keys = symmetricRatchet(bob.receivingChainKey);
        bob.receivingChainKey = keys[1]; // Update receiving chain key
        String decryptedMessage2 = decryptMessage(keys[0], encryptedMessage2, bob.receivingMessageCounter++);
        log.info("[Step 5] Bob received and decrypted second message: " + decryptedMessage2);

        // Step 6: Bob performs DH Ratchet and sends a reply to Alice
        X25519PublicKeyParameters alicePublicKey = extractEphemeralPublicKey(encryptedMessage1); // Use Alice's public key from message
        dhRatchet(bob, alice, alicePublicKey, true); // Bob is sender
        String message3 = "Hi, Alice!";
        keys = symmetricRatchet(bob.sendingChainKey);
        bob.sendingChainKey = keys[1]; // Update sending chain key
        byte[] encryptedMessage3 = encryptMessage(keys[0], message3, bob.sendingMessageCounter++, bob.ephemeralPublicKey);
        log.info("[Step 6] Bob sends encrypted reply: " + getHex(encryptedMessage3));

        // Step 7: Alice receives and decrypts Bob's reply
        X25519PublicKeyParameters bobPublicKey = extractEphemeralPublicKey(encryptedMessage3); // Get Bob's new ephemeral public key
        dhRatchet(alice, bob, bobPublicKey, true); // Alice is receiver
        keys = symmetricRatchet(alice.receivingChainKey);
        alice.receivingChainKey = keys[1]; // Update receiving chain key
        String decryptedMessage3 = decryptMessage(keys[0], encryptedMessage3, alice.receivingMessageCounter++);
        log.info("[Step 7] Alice received and decrypted Bob's reply: " + decryptedMessage3);
    }
}