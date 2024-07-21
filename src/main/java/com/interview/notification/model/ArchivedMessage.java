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
public class ArchivedMessage {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idArchivedMessage;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "channel_id")  
	private ChannelNotification channel;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")  
	private Category category;
	
	private String message;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_origin_id")  
	private UserCustomer userOrigin;
	
	public Long getIdArchivedMessage() {
		return idArchivedMessage;
	}

	public void setIdArchivedMessage(Long idArchivedMessage) {
		this.idArchivedMessage = idArchivedMessage;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setUserOrigin(UserCustomer userOrigin) {
		this.userOrigin = userOrigin;
	}
	
	public UserCustomer getUserOrigin() {
		return userOrigin;
	}
	
	
}
