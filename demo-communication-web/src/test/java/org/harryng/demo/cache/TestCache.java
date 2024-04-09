package org.harryng.demo.cache;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.harryng.demo.main.Application;
import org.infinispan.Cache;
import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.configuration.parsing.ConfigurationBuilderHolder;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.remoting.transport.jgroups.JGroupsTransport;
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
    private DefaultCacheManager cacheManager;

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
    public void testCache() {
        log.info("=====");
        final Cache<String, String> cache = cacheManager.getCache("dist-cache");
        for (String cacheName: cacheManager.getCacheNames()){
            log.info("cacheName:{}", cacheName);
        }
    }
}
