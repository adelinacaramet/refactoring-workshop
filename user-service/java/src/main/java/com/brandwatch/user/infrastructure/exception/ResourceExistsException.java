package com.brandwatch.user.infrastructure.exception;

public class ResourceExistsException extends RuntimeException {

    public ResourceExistsException(String message) {
        super(message);
    }
}
