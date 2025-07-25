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
public class TestSignalProtocol3 {
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
//            generateOneTimePrekey();
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

    /**
     * Simulate HKDF by HMAC
     *
     * @param inputKey secret
     * @param info data
     * */
    private static byte[] hkdf(byte[] inputKey, byte[] info) {
        HMac hMac = new HMac(new SHA256Digest());
        hMac.init(new KeyParameter(inputKey));
        byte[] output = new byte[32];
        hMac.update(info, 0, info.length);
        hMac.doFinal(output, 0);
        return output;
    }

    /**
     * Perform Diffie-Hellman key exchange
     * @param privateKey A's private key
     * @param publicKey B's public key
     * */
    private static byte[] dhExchange(X25519PrivateKeyParameters privateKey, X25519PublicKeyParameters publicKey) {
        byte[] sharedSecret = new byte[32];
        privateKey.generateSecret(publicKey, sharedSecret, 0);
        return sharedSecret;
    }

    /**
     * X3DH key agreement with Signed Prekey and One-Time Prekey
     * <ul>
     *     <li>Create X3DH key: combinedKey= DH(IK<sub>A</sub>, SPK<sub>B</sub>)
     *     + DH(EK<sub>A</sub>, IK<sub>B</sub>)
     *     + DH(EK<sub>A</sub>, SPK<sub>B</sub>) </li>
     *     <li>rootKey = A's Root key = B's Root key = HKDF(combinedKey, HKDF_RANDOM)</li>
     *     <li>A's SendingChainKey = B's ReceivingChainKey = HKDF(rootKey, CHAIN_KEY_RANDOM)</li>
     * </ul>
     * */
    private static void performX3DH(User alice, User bob) {
        alice.generateEphemeralKeyPair();
        byte[] dh1 = dhExchange(alice.identityPrivateKey, bob.signedPrekeyPublic);
        byte[] dh2 = dhExchange(alice.ephemeralPrivateKey, bob.identityPublicKey);
        byte[] dh3 = dhExchange(alice.ephemeralPrivateKey, bob.signedPrekeyPublic);
//        byte[] dh4 = dhExchange(alice.ephemeralPrivateKey, bob.oneTimePrekeyPublic);
        byte[] combined = new byte[dh1.length + dh2.length + dh3.length];
//        byte[] combined = new byte[dh1.length + dh2.length + dh3.length + dh4.length];
        System.arraycopy(dh1, 0, combined, 0, dh1.length);
        System.arraycopy(dh2, 0, combined, dh1.length, dh2.length);
        System.arraycopy(dh3, 0, combined, dh1.length + dh2.length, dh3.length);
//        System.arraycopy(dh4, 0, combined, dh1.length + dh2.length + dh3.length, dh4.length);
        alice.rootKey = hkdf(combined, HKDF_INFO);
        bob.rootKey = alice.rootKey;
        alice.sendingChainKey = hkdf(alice.rootKey, "ChainKey".getBytes());
        bob.receivingChainKey = alice.sendingChainKey;
        bob.sendingChainKey = hkdf(bob.rootKey, "ChainKey".getBytes());
        alice.receivingChainKey = bob.sendingChainKey;
    }

    /**
     * Symmetric Ratchet
     *
     * @param chainKey
     * @return
     * <ul>
     *     <li>messageKey</li>
     *     <li>nextChainKey</li>
     * </ul>
     * */
    private static byte[][] symmetricRatchet(byte[] chainKey) {
        byte[] messageKey = hkdf(chainKey, "MessageKey".getBytes());
        byte[] nextChainKey = hkdf(chainKey, "NextChainKey".getBytes());
        return new byte[][] {messageKey, nextChainKey};
    }

    /**
     * DH Ratchet
     * <ul>
     * <li>Gen new Sender's Ephemeral KeyPair</li>
     * <li>Gen new RootKey for both</li>
     * <li>Update Sender's SendingChainKey and Receiver's ReceivingChainKey = HKDF(rootKey, CHAIN_KEY_RANDOM)</li>
     * </ul>
     * */
    private static void dhRatchet(User sender, User receiver, X25519PublicKeyParameters receiverPublicKey, boolean isSender) {
        if (isSender) {
            sender.generateEphemeralKeyPair();
        }
        byte[] sharedSecret = dhExchange(sender.ephemeralPrivateKey, receiverPublicKey);
        sender.rootKey = hkdf(sender.rootKey, sharedSecret);
        receiver.rootKey = sender.rootKey;
        if (isSender) {
            sender.sendingChainKey = hkdf(sender.rootKey, "ChainKey".getBytes());
            receiver.receivingChainKey = sender.sendingChainKey;
        } else {
            sender.receivingChainKey = hkdf(sender.rootKey, "ChainKey".getBytes());
            receiver.sendingChainKey = sender.receivingChainKey;
        }
    }

    /**
     * Encrypt message using AES-GCM
     *
     * @return none + cipherMessage + ephemeralPublicKey
     * */
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
        byte[] ciphertext = Arrays.copyOfRange(encryptedMessage, GCM_NONCE_LENGTH, encryptedMessage.length - 32);
        SecretKeySpec keySpec = new SecretKeySpec(messageKey, "AES");
        GCMParameterSpec gcmSpec = new GCMParameterSpec(GCM_TAG_LENGTH, nonce);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmSpec);
        byte[] plaintext = cipher.doFinal(ciphertext);
        return new String(plaintext, StandardCharsets.UTF_8);
    }

    /**
     * Extract ephemeral public key
     * <p>
     *     Get Ephemeral Public Key
     * </p>
     *
     * @param encryptedMessage
     * <ul>
     *     <li>first len(encryptedMessage)-32 bytes: encrypted message</li>
     *     <li>last 32 bytes: Ephemeral Public Key</li>
     * </ul>
     * */
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

        // Step 2: Alice sends initial message to Bob
        String message1 = "Hello, Bob!";
        byte[][] keys = symmetricRatchet(alice.sendingChainKey);
        alice.sendingChainKey = keys[1];
        byte[] encryptedMessage1 = encryptMessage(keys[0], message1, alice.sendingMessageCounter++, alice.ephemeralPublicKey);
        log.info("Alice sends initial encrypted message: " + getHex(encryptedMessage1));

        // Step 3: Bob receives and decrypts the initial message
        keys = symmetricRatchet(bob.receivingChainKey);
        bob.receivingChainKey = keys[1];
        String decryptedMessage1 = decryptMessage(keys[0], encryptedMessage1, bob.receivingMessageCounter++);
        log.info("Bob received and decrypted initial message: " + decryptedMessage1);

        // Step 4: Bob performs DH Ratchet and sends first reply to Alice
        X25519PublicKeyParameters alicePublicKey = extractEphemeralPublicKey(encryptedMessage1);
        dhRatchet(bob, alice, alicePublicKey, true);
        String message2 = "Hi, Alice!";
        keys = symmetricRatchet(bob.sendingChainKey);
        bob.sendingChainKey = keys[1];
        byte[] encryptedMessage2 = encryptMessage(keys[0], message2, bob.sendingMessageCounter++, bob.ephemeralPublicKey);
        log.info("Bob sends first encrypted reply: " + getHex(encryptedMessage2));

        // Step 5: Bob sends second reply to Alice
        String message3 = "Good to hear from you!";
        keys = symmetricRatchet(bob.sendingChainKey);
        bob.sendingChainKey = keys[1];
        byte[] encryptedMessage3 = encryptMessage(keys[0], message3, bob.sendingMessageCounter++, bob.ephemeralPublicKey);
        log.info("Bob sends second encrypted reply: " + getHex(encryptedMessage3));

        // Step 6: Alice receives and decrypts Bob's first reply
        X25519PublicKeyParameters bobPublicKey = extractEphemeralPublicKey(encryptedMessage2);
        dhRatchet(alice, bob, bobPublicKey, true);
        keys = symmetricRatchet(alice.receivingChainKey);
        alice.receivingChainKey = keys[1];
        String decryptedMessage2 = decryptMessage(keys[0], encryptedMessage2, alice.receivingMessageCounter++);
        log.info("Alice received and decrypted Bob's first reply: " + decryptedMessage2);

        // Step 7: Alice receives and decrypts Bob's second reply
        keys = symmetricRatchet(alice.receivingChainKey);
        alice.receivingChainKey = keys[1];
        String decryptedMessage3 = decryptMessage(keys[0], encryptedMessage3, alice.receivingMessageCounter++);
        log.info("Alice received and decrypted Bob's second reply: " + decryptedMessage3);
    }
}