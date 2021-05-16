package com.dahada.backend.application.configuration.tyeps;

import lombok.Getter;

@Getter
public enum CacheType {

    USER_DETAILS("user_details", 1 * 60L, 1000L);

    public static final String USER_DETAILS_CACHE_NAME = "user_details";

    private final String cacheName;   // name of cache
    private final Long expiredAfterWrite;     // in seconds
    private final Long maximumSize;       // size of cache

    CacheType(String cacheName, Long expiredAfterWrite, Long maximumSize) {
        this.cacheName = cacheName;
        this.expiredAfterWrite = expiredAfterWrite;
        this.maximumSize = maximumSize;
    }
}
