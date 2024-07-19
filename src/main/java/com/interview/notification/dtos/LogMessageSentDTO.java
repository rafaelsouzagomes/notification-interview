package com.interview.notification.dtos;

public class LogMessageSentDTO {

	private Long idLogMessageSent;
	
	private String  message;
	
	private String username_origin;
	
	private String username_destination;
	
	private String category_name;
	
	private String channel_name;

	public Long getIdLogMessageSent() {
		return idLogMessageSent;
	}

	public void setIdLogMessageSent(Long idLogMessageSent) {
		this.idLogMessageSent = idLogMessageSent;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUsername_origin() {
		return username_origin;
	}

	public void setUsername_origin(String username_origin) {
		this.username_origin = username_origin;
	}

	public String getUsername_destination() {
		return username_destination;
	}

	public void setUsername_destination(String username_destination) {
		this.username_destination = username_destination;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getChannel_name() {
		return channel_name;
	}

	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}
}
