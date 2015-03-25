package com.brandwatch.user.application.exceptionhandler;

public class ResponseError {
    private final ErrorMessage error;

    public ResponseError(ErrorMessage error) {
        this.error = error;
    }

    public ErrorMessage getError() {
        return error;
    }
}
