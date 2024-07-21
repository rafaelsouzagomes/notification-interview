package com.interview.notification.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


@Entity
public class UserCustomer {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUser;
	
	private String name;
	
	private String email;
	
	private String phoneNumber;

	@ManyToMany
    @JoinTable(
        name = "user_subscribed_categories", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> subscribedCategories;	
    
	
	 @ManyToMany
	    @JoinTable(
	        name = "user_channels", 
	        joinColumns = @JoinColumn(name = "user_id"), 
	        inverseJoinColumns = @JoinColumn(name = "channel_id")
	    )
    private List<ChannelNotification> channels;
    
    public void setChannels(List<ChannelNotification> channels) {
		this.channels = channels;
	}
    
    public List<ChannelNotification> getChannels() {
		return channels;
	}
	
	public void setSubscribedCategories(List<Category> subscribedCategories) {
		this.subscribedCategories = subscribedCategories;
	}
	
	public List<Category> getSubscribedCategories() {
		return subscribedCategories;
	}
	
	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
