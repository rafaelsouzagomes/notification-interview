package com.interview.notification.validations;

public class ValidationNotificationGlobalException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ValidationNotificationGlobalException(String message) {
        super(message);
    }
}