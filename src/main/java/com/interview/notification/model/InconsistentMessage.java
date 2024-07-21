package com.interview.notification.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class InconsistentMessage {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idInconsistentMessage;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "channel_id")  
	private ChannelNotification channel;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")  
	private Category category;
	
	private String error;
	
	private String message;

	public Long getIdInconsistentMessage() {
		return idInconsistentMessage;
	}

	public void setIdInconsistentMessage(Long idInconsistentMessage) {
		this.idInconsistentMessage = idInconsistentMessage;
	}

	public ChannelNotification getChannel() {
		return channel;
	}

	public void setChannel(ChannelNotification channel) {
		this.channel = channel;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
