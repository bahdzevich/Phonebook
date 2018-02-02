package com.bogdevich.profile.controller.exception;

public class InternalServiceException extends RuntimeException {
    public InternalServiceException() {
        super();
    }

    public InternalServiceException(String message) {
        super(message);
    }

    public InternalServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalServiceException(Throwable cause) {
        super(cause);
    }

    protected InternalServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
