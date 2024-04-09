package org.harryng.demo.cache;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.harryng.demo.main.Application;
import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.util.concurrent.locks.KeyAwareLockPromise;
import org.infinispan.util.concurrent.locks.LockManager;
import org.infinispan.util.concurrent.locks.impl.InfinispanLock;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = Application.class)
@Import(Application.class)
@Slf4j
public class TestCache {
    @Resource
    private EmbeddedCacheManager cacheManager;

    @BeforeAll
    public static void init() {
//        final GlobalConfigurationBuilder gcb = new GlobalConfigurationBuilder();
//        gcb.
//        gcb.globalJmxStatistics().enabled(false).allowDuplicateDomains(true);
//        gcb.transport().defaultTransport().addProperty(JGroupsTransport.CONFIGURATION_STRING,
//                configurator.getProtocolStackString());

//        final ConfigurationBuilder builder = new ConfigurationBuilder();
//        builder.clustering().cacheMode(CacheMode.DIST_SYNC).expiration().lifespan(24L, TimeUnit.HOURS);
//
//        builder.clustering().stateTransfer().awaitInitialTransfer(false);
//        builder.clustering().hash().numOwners(2);
//        final ConfigurationBuilderHolder holder = new ConfigurationBuilderHolder();
//        holder.getCurrentConfigurationBuilder().clust
//        final DefaultCacheManager defaultCacheManager = new DefaultCacheManager(holder, true);
    }

    @Test
    public void testCacheLock() {
        // do not need to lock by manual as below
        log.info("=====");
        final Cache<String, String> cache = cacheManager.getCache("dist-cache");

        final LockManager lockManager = cache.getAdvancedCache().getLockManager();
        final String key = "k1";
        final String owner = "owner 1";
        cache.put(key, "v1", 1000, TimeUnit.MINUTES);
        final KeyAwareLockPromise lock = lockManager.lock(key, owner, 10, TimeUnit.SECONDS);
        try {
            lock.lock();
            log.info("LockOwner:{}", lockManager.getOwner(key));
            log.info("Lock status:{} {}", lockManager.getLock(key).isLocked(), lockManager.isLocked(key));
            for (String cacheName : cacheManager.getCacheNames()) {
                log.info("cacheName:{}", cacheName);
            }
        } catch (InterruptedException e) {
            log.error("", e);
        } finally {
            lockManager.unlock(key, owner);

        }
        log.info("Lock status after unlock:{}", lockManager.isLocked(key));
    }
}
