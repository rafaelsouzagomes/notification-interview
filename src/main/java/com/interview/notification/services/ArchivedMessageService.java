package com.interview.notification.services;

import java.util.Date;

import org.hibernate.usertype.UserCollectionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.notification.model.ArchivedMessage;
import com.interview.notification.model.Category;
import com.interview.notification.model.ChannelNotification;
import com.interview.notification.model.UserCustomer;
import com.interview.notification.repositories.ArchivedMessageRepository;
import com.interview.notification.repositories.UserRepository;
import com.interview.notification.services.notification.producers.beans.NotificationMessageEvent;

@Service
public class ArchivedMessageService {

	private ArchivedMessageRepository archivedMessageRepository;
	private UserRepository userRepositoy;
	
	public void archive(ChannelNotification channel, Category category, NotificationMessageEvent event) {
		
		UserCustomer user_origin = userRepositoy.findById(event.getIdUserOrigin()).get();
		
		ArchivedMessage message = new ArchivedMessage();
		message.setCategory(category);
		message.setChannel(channel);
		message.setDate(new Date());
		message.setMessage(event.getMessage());
		message.setUserOrigin(user_origin);
		
		archivedMessageRepository.save(message);
	}
	
	@Autowired
	public void setArchivedMessageRepository(ArchivedMessageRepository archivedMessageRepository) {
		this.archivedMessageRepository = archivedMessageRepository;
	}
	
	@Autowired
	public void setUserRepositoy(UserRepository userRepositoy) {
		this.userRepositoy = userRepositoy;
	}
}
