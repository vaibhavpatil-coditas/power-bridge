package com.coditas.powerbridge.exception;

public class UserNotAuthenticatedException extends RuntimeException {
    public UserNotAuthenticatedException(String userNotAuthenticated) {
        super(userNotAuthenticated);
    }
}
