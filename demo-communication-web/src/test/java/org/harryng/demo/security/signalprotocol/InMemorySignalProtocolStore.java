package org.harryng.demo.security.signalprotocol;

import org.whispersystems.libsignal.*;
import org.whispersystems.libsignal.state.*;
import org.whispersystems.libsignal.util.KeyHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemorySignalProtocolStore implements SignalProtocolStore {
    private final Map<Integer, PreKeyRecord> preKeys = new ConcurrentHashMap<>();
    private final Map<Integer, SignedPreKeyRecord> signedPreKeys = new ConcurrentHashMap<>();
    private final Map<SignalProtocolAddress, SessionRecord> sessions = new ConcurrentHashMap<>();
    private final IdentityKeyPair identityKeyPair;
    private final int registrationId;

    public InMemorySignalProtocolStore() throws InvalidKeyException {
        this.identityKeyPair = KeyHelper.generateIdentityKeyPair();
        this.registrationId = KeyHelper.generateRegistrationId(false);
        // Tạo PreKeys và SignedPreKey
        List<PreKeyRecord> generatedPreKeys = KeyHelper.generatePreKeys(0, 100);
        for (PreKeyRecord preKey : generatedPreKeys) {
            preKeys.put(preKey.getId(), preKey);
        }
        SignedPreKeyRecord signedPreKey = KeyHelper.generateSignedPreKey(identityKeyPair, 1);
        signedPreKeys.put(signedPreKey.getId(), signedPreKey);
    }

    @Override
    public IdentityKeyPair getIdentityKeyPair() {
        return identityKeyPair;
    }

    @Override
    public int getLocalRegistrationId() {
        return registrationId;
    }

    @Override
    public boolean saveIdentity(SignalProtocolAddress address, IdentityKey identityKey) {
        // Giả lập lưu trữ identity key
        return true;
    }

    @Override
    public boolean isTrustedIdentity(SignalProtocolAddress address, IdentityKey identityKey, Direction direction) {
        // Luôn tin tưởng trong ví dụ
        return true;
    }

    @Override
    public IdentityKey getIdentity(SignalProtocolAddress address) {
        return null; // Không cần trong ví dụ
    }

    @Override
    public PreKeyRecord loadPreKey(int preKeyId) throws InvalidKeyIdException {
        PreKeyRecord preKey = preKeys.get(preKeyId);
        if (preKey == null) {
            throw new InvalidKeyIdException("No such prekey: " + preKeyId);
        }
        return preKey;
    }

    @Override
    public void storePreKey(int preKeyId, PreKeyRecord record) {
        preKeys.put(preKeyId, record);
    }

    @Override
    public boolean containsPreKey(int preKeyId) {
        return preKeys.containsKey(preKeyId);
    }

    @Override
    public void removePreKey(int preKeyId) {
        preKeys.remove(preKeyId);
    }

    @Override
    public SessionRecord loadSession(SignalProtocolAddress address) {
        return sessions.computeIfAbsent(address, k -> new SessionRecord());
    }

    @Override
    public List<Integer> getSubDeviceSessions(String name) {
        return new ArrayList<>();
    }

    @Override
    public void storeSession(SignalProtocolAddress address, SessionRecord record) {
        sessions.put(address, record);
    }

    @Override
    public boolean containsSession(SignalProtocolAddress address) {
        return sessions.containsKey(address);
    }

    @Override
    public void deleteSession(SignalProtocolAddress address) {
        sessions.remove(address);
    }

    @Override
    public void deleteAllSessions(String name) {
        sessions.entrySet().removeIf(entry -> entry.getKey().getName().equals(name));
    }

    @Override
    public SignedPreKeyRecord loadSignedPreKey(int signedPreKeyId) throws InvalidKeyIdException {
        SignedPreKeyRecord signedPreKey = signedPreKeys.get(signedPreKeyId);
        if (signedPreKey == null) {
            throw new InvalidKeyIdException("No such signed prekey: " + signedPreKeyId);
        }
        return signedPreKey;
    }

    @Override
    public List<SignedPreKeyRecord> loadSignedPreKeys() {
        return List.of();
    }

    @Override
    public void storeSignedPreKey(int signedPreKeyId, SignedPreKeyRecord record) {
        signedPreKeys.put(signedPreKeyId, record);
    }

    @Override
    public boolean containsSignedPreKey(int signedPreKeyId) {
        return signedPreKeys.containsKey(signedPreKeyId);
    }

    @Override
    public void removeSignedPreKey(int signedPreKeyId) {
        signedPreKeys.remove(signedPreKeyId);
    }

    public PreKeyBundle getPreKeyBundle(int deviceId) throws InvalidKeyException {
        return new PreKeyBundle(
                registrationId,
                deviceId,
                preKeys.values().iterator().next().getId(),
                preKeys.values().iterator().next().getKeyPair().getPublicKey(),
                signedPreKeys.get(1).getId(),
                signedPreKeys.get(1).getKeyPair().getPublicKey(),
                signedPreKeys.get(1).getSignature(),
                identityKeyPair.getPublicKey()
        );
    }
}
