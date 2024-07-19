package com.interview.notification.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class LogMessageSent {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMessage;
	
	private String message;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private UserCustomer user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Category category;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private ChannelNotification channel;

	public Long getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(Long idMessage) {
		this.idMessage = idMessage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UserCustomer getUser() {
		return user;
	}

	public void setUser(UserCustomer user) {
		this.user = user;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public ChannelNotification getChannel() {
		return channel;
	}

	public void setChannel(ChannelNotification channel) {
		this.channel = channel;
	}
	
	
}
