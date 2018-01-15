package com.bogdevich.profile.controller.exception;

/**
 * Module exception class.
 * Thrown in cases of data not found.
 *
 * @author Eugene Bogdevich
 */
public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException() {
        super();
    }

    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataNotFoundException(Throwable cause) {
        super(cause);
    }
}
