package com.interview.notification.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.interview.notification.enums.TypeChannel;

@Entity
public class ChannelNotification {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idChannelNotification;
	
    @Enumerated(EnumType.STRING)
	private TypeChannel typeChannel;
	
	private String description;

    @ManyToMany(mappedBy = "channels")
    private List<UserCustomer> users;
	
    public void setUsers(List<UserCustomer> users) {
		this.users = users;
	}
    
    public List<UserCustomer> getUsers() {
		return users;
	}
    
	public Long getIdChannelNotification() {
		return idChannelNotification;
	}

	public void setIdChannelNotification(Long idChannelNotification) {
		this.idChannelNotification = idChannelNotification;
	}

	public TypeChannel getTypeChannel() {
		return typeChannel;
	}

	public void setTypeChannel(TypeChannel typeChannel) {
		this.typeChannel = typeChannel;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
