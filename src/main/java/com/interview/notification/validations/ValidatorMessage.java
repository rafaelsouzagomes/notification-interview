package com.interview.notification.validations;

import java.util.Objects;

import org.springframework.stereotype.Service;

@Service
public class ValidatorMessage {

	public void validateMessage(String message) {
		if(Objects.isNull(message) || message.isEmpty())
			throw new ValidationException("The message cannot be empty.");
	}
}
