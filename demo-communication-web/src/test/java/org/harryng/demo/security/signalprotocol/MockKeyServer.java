package org.harryng.demo.security.signalprotocol;


import org.whispersystems.libsignal.state.PreKeyBundle;

import java.util.HashMap;
import java.util.Map;

public class MockKeyServer {
    private final Map<String, PreKeyBundle> keyBundles = new HashMap<>();

    public void storePreKeyBundle(String userId, PreKeyBundle bundle) {
        keyBundles.put(userId, bundle);
    }

    public PreKeyBundle getPreKeyBundle(String userId) {
        return keyBundles.get(userId);
    }
}
