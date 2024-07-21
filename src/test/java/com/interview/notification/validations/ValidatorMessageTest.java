package com.interview.notification.validations;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ValidatorMessageTest {

    private final ValidatorMessage validatorMessage = new ValidatorMessage();

    @Test
    void testValidateMessage_NullMessage() {
        String message = null;

        assertThrows(ValidationNotificationGlobalException.class, () -> validatorMessage.validateMessage(message));
    }

    @Test
    void testValidateMessage_EmptyMessage() {
        String message = "";

        assertThrows(ValidationNotificationGlobalException.class, () -> validatorMessage.validateMessage(message));
    }

    @Test
    void testValidateMessage_ValidMessage() {
        String message = "Valid message";

        assertDoesNotThrow(() -> validatorMessage.validateMessage(message));
    }
}
