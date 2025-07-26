package org.harryng.demo.security.signalprotocol;

import org.whispersystems.libsignal.InvalidKeyException;
import org.whispersystems.libsignal.SessionBuilder;
import org.whispersystems.libsignal.SessionCipher;
import org.whispersystems.libsignal.SignalProtocolAddress;
import org.whispersystems.libsignal.protocol.CiphertextMessage;
import org.whispersystems.libsignal.protocol.PreKeySignalMessage;
import org.whispersystems.libsignal.protocol.SignalMessage;
import org.whispersystems.libsignal.state.PreKeyBundle;

import java.nio.charset.StandardCharsets;

public class SignalClient {
    private final InMemorySignalProtocolStore store;
    private final SignalProtocolAddress address;
    private final MockKeyServer keyServer;
    private final String userId;

    public SignalClient(String userId, int deviceId, MockKeyServer keyServer) throws InvalidKeyException {
        this.userId = userId;
        this.store = new InMemorySignalProtocolStore();
        this.address = new SignalProtocolAddress(userId, deviceId);
        this.keyServer = keyServer;
        // Đăng ký PreKeyBundle với server
        try {
            keyServer.storePreKeyBundle(userId, store.getPreKeyBundle(deviceId));
        } catch (InvalidKeyException e) {
            throw new RuntimeException("Failed to generate PreKeyBundle", e);
        }
    }

    public void buildSession(String remoteUserId, int remoteDeviceId) throws Exception {
        PreKeyBundle remoteBundle = keyServer.getPreKeyBundle(remoteUserId);
        if (remoteBundle == null) {
            throw new IllegalStateException("No PreKeyBundle for " + remoteUserId);
        }
        SignalProtocolAddress remoteAddress = new SignalProtocolAddress(remoteUserId, remoteDeviceId);
        SessionBuilder sessionBuilder = new SessionBuilder(store, remoteAddress);
        sessionBuilder.process(remoteBundle);
    }

    public byte[] encryptMessage(String message, String remoteUserId, int remoteDeviceId) throws Exception {
        SignalProtocolAddress remoteAddress = new SignalProtocolAddress(remoteUserId, remoteDeviceId);
        SessionCipher sessionCipher = new SessionCipher(store, remoteAddress);
        CiphertextMessage ciphertext = sessionCipher.encrypt(message.getBytes(StandardCharsets.UTF_8));
        return ciphertext.serialize();
    }

    public String decryptMessage(byte[] ciphertext, String remoteUserId, int remoteDeviceId) throws Exception {
        SignalProtocolAddress remoteAddress = new SignalProtocolAddress(remoteUserId, remoteDeviceId);
        SessionCipher sessionCipher = new SessionCipher(store, remoteAddress);
        if (ciphertext[0] == CiphertextMessage.PREKEY_TYPE) {
            PreKeySignalMessage preKeyMessage = new PreKeySignalMessage(ciphertext);
            byte[] plaintext = sessionCipher.decrypt(preKeyMessage);
            return new String(plaintext, StandardCharsets.UTF_8);
        } else {
            SignalMessage signalMessage = new SignalMessage(ciphertext);
            byte[] plaintext = sessionCipher.decrypt(signalMessage);
            return new String(plaintext, StandardCharsets.UTF_8);
        }
    }
}
