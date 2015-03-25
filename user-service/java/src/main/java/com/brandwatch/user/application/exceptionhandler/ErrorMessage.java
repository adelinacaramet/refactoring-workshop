package com.brandwatch.user.application.exceptionhandler;

public final class ErrorMessage {
    private final String message;

    public ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
