package com.interview.notification.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class LogMessageSent {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idLogMessageSent;
	
	private String message;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_origin_id")  
	private UserCustomer user_origin;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_destination_id")  
	private UserCustomer user_destination;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")  
	private Category category;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "channel_id")  
	private ChannelNotification channel;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Date getDate() {
		return date;
	}

	public void setIdLogMessageSent(Long idLogMessageSent) {
		this.idLogMessageSent = idLogMessageSent;
	}
	
	public Long getIdLogMessageSent() {
		return idLogMessageSent;
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setUser_destination(UserCustomer user_destination) {
		this.user_destination = user_destination;
	}
	
	public UserCustomer getUser_destination() {
		return user_destination;
	}
	
	public void setUser_origin(UserCustomer user_origin) {
		this.user_origin = user_origin;
	}
	
	public UserCustomer getUser_origin() {
		return user_origin;
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
