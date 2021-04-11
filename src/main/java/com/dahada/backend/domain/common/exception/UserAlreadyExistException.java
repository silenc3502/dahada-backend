package com.dahada.backend.domain.common.exception;

/**
 * @author hyeyoom
 */
public final class UserAlreadyExistException extends IllegalStateException {
    public UserAlreadyExistException(String s) {
        super(s);
    }

    public UserAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
