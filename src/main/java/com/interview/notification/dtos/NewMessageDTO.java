package com.interview.notification.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NewMessageDTO {

	@NotNull(message = "Category cannot be null")
	@NotBlank(message = "Category cannot be empty")
	private final String category;
	
	@NotNull(message = "Message cannot be null")
	@NotBlank(message = "Category cannot be empty")
	private final String message;
	
	public NewMessageDTO(String category, String message) {
		this.category = category;
		this.message = message;
	}
	
	public String getCategory() {
		return category;
	}
	
	public String getMessage() {
		return message;
	}
	
	
}
