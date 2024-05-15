package org.harryng.demo.impl.cache;

import lombok.RequiredArgsConstructor;
import org.infinispan.Cache;
import org.infinispan.manager.EmbeddedCacheManager;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CacheManager {

    public static String CACHE_SESSION = "session-cache";

    private final EmbeddedCacheManager cacheManager;

    public <K, V> Cache<K, V> getCache(String name) {
        return cacheManager.getCache(name, false);
    }
}
