package com.dahada.backend.application.auth;

import org.springframework.beans.factory.annotation.Value;

public enum ServiceBuilderFactory {
    ;

    @Value("abadsf")
    private String test;

    public String getTest() {
        return test;
    }
}
