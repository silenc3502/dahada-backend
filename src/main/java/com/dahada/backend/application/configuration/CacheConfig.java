package com.dahada.backend.application.configuration;

import com.dahada.backend.application.configuration.tyeps.CacheType;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Configuration
public class CacheConfig {

    @Bean
    public CacheManager localCacheManager() {
        final SimpleCacheManager cacheManager = new SimpleCacheManager();
        final List<CaffeineCache> caches = Arrays.stream(CacheType.values())
                .map(type -> new CaffeineCache(
                        type.getCacheName(),
                        Caffeine.newBuilder().recordStats()
                                .expireAfterWrite(type.getExpiredAfterWrite(), TimeUnit.SECONDS)
                                .maximumSize(type.getMaximumSize())
                                .build()
                ))
                .collect(Collectors.toList());
        cacheManager.setCaches(caches);
        return cacheManager;
    }
}
