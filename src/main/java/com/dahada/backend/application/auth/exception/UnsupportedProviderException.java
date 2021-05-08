package com.dahada.backend.application.auth.exception;

public final class UnsupportedProviderException extends RuntimeException {
    public UnsupportedProviderException(String message) {
        super(message);
    }
}
