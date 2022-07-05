package com.work.authorization.exception;

public class PasswordMatcherException extends RuntimeException {

    public PasswordMatcherException() {
        super();
    }

    public PasswordMatcherException(String message) {
        super(message);
    }

    public PasswordMatcherException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordMatcherException(Throwable cause) {
        super(cause);
    }

    protected PasswordMatcherException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
