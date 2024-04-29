package org.harryng.demo.cache;

import jakarta.annotation.Resource;
import jakarta.transaction.TransactionManager;
import lombok.extern.slf4j.Slf4j;
import org.harryng.demo.Application;
import org.infinispan.AdvancedCache;
import org.infinispan.Cache;
import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.util.concurrent.locks.KeyAwareLockPromise;
import org.infinispan.util.concurrent.locks.LockManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

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
//        cache.put(key, "v1", 1000, TimeUnit.MINUTES);
        final KeyAwareLockPromise lock = lockManager.lock(key, owner, 10, TimeUnit.SECONDS);
        try {
            lock.lock();
            cache.put(key, "v1", 1000, TimeUnit.MINUTES);
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

    @Test
    public void testCacheMultithreadLock() {
        // lock by LockManager does not need to put in Txn scope
        final Cache<String, String> cache = cacheManager.getCache("dist-cache");
        final String key = "k1";
        final AtomicInteger val = new AtomicInteger();
        final String owner = "owner_";
        final Random random = new Random();
//        cache.put(key, "1", 1000, TimeUnit.SECONDS);
        try (var executor = Executors.newFixedThreadPool(50)) {
            final List<Future<Integer>> futures = new ArrayList<>();
            for (int i = 0; i < 5000; i++) {
                final Future<Integer> fut = executor.submit(() -> {
                    final AdvancedCache<String, String> advancedCache = cache.getAdvancedCache();
//                    final TransactionManager txnManager = advancedCache.getTransactionManager();
//                    txnManager.begin();
                    final var ownerThread = owner + Thread.currentThread().getName();
                    final LockManager lockManager = advancedCache.getLockManager();
                    final var lockPromise = lockManager.lock(key, ownerThread, 50, TimeUnit.SECONDS); //cache.get(key);
                    lockPromise.lock();
                    final var cacheVal = advancedCache.get(key);
                    if (cacheVal != null) {
//                        Thread.sleep(random.nextInt(100));
                        val.set(Integer.parseInt(cacheVal));
                    }
                    int currVal = 0;
                    try {
                        currVal = val.incrementAndGet();
                        Thread.sleep(random.nextInt(10));
                        log.info("currVal increment:{} by {} in locked[{}]", currVal, ownerThread, lockManager.isLocked(key));
                        advancedCache.put(key, String.valueOf(currVal), 1000, TimeUnit.SECONDS);
//                        txnManager.commit();
                    } catch (InterruptedException e) {
//                        txnManager.rollback();
                        log.error("", e);
                    } finally {
                        lockManager.unlock(key, ownerThread);
                        log.info("isLock:{} by {}", lockManager.isLocked(key), lockManager.getOwner(key));
                    }
                    return currVal;
                });
                futures.add(fut);
            }
            log.info("Fut result count:{}", futures.size());
            for (var fut : futures) {
                log.info("currVal return:{}", fut.get());
            }
            log.info("Result:{}", val.get());
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testCacheTxnLock() {
        // Lock by AdvanceCache must put lock in txn scope
        final Cache<String, String> cache = cacheManager.getCache("dist-cache");
//        final Cache<String, String> cache = cacheManager.getCache("repl-cache");
        final String key = "k1";
        final AtomicInteger val = new AtomicInteger();
        final String owner = "owner_";
        final Random random = new Random();
//        cache.put(key, "1", 1000, TimeUnit.SECONDS);
        try (var executor = Executors.newFixedThreadPool(50)) {
            final List<Future<Integer>> futures = new ArrayList<>();
            for (int i = 0; i < 5000; i++) {
                final Future<Integer> fut = executor.submit(() -> {
                    var ownerThread = owner + Thread.currentThread().getName();
                    final AdvancedCache<String, String> advancedCache = cache.getAdvancedCache();
                    final LockManager lockManager = advancedCache.getLockManager();
                    final TransactionManager txnManager = advancedCache.getTransactionManager();
                    txnManager.begin();
//                    final var cacheVal = advancedCache.withFlags(Flag.FORCE_WRITE_LOCK).get(key);
                    int currVal = 0;
                    try {
                        if (advancedCache.lock(key)) {
                            final var cacheVal = advancedCache.get(key);
                            if (cacheVal != null) {
                                val.set(Integer.parseInt(cacheVal));
                            }
                        }
                        currVal = val.incrementAndGet();
                        Thread.sleep(random.nextInt(10));
                        advancedCache.put(key, String.valueOf(currVal), 1000, TimeUnit.SECONDS);
                        log.info("currVal increment:{} by {} in locked[{}]", currVal, lockManager.getOwner(key), lockManager.isLocked(key));
                        txnManager.commit();
                    } catch (InterruptedException e) {
                        txnManager.rollback();
                        log.error("", e);
                    } finally {
                        lockManager.unlock(key, ownerThread);
                        log.info("After unlock locked status:{} by {}", lockManager.isLocked(key), lockManager.getOwner(key));
                    }
                    return currVal;
                });
                futures.add(fut);
            }
            log.info("Fut result count:{}", futures.size());
            for (var fut : futures) {
                log.info("currVal return:{}", fut.get());
            }
            log.info("Result:{}", val.get());
//            final boolean rs = executor.awaitTermination(3, TimeUnit.SECONDS);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
