package com.interview.notification.services.notification.email;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import com.interview.notification.enums.TypeChannel;
import com.interview.notification.model.Category;
import com.interview.notification.model.ChannelNotification;
import com.interview.notification.model.LogMessageSent;
import com.interview.notification.model.UserCustomer;
import com.interview.notification.repositories.ChannelNotificationRepository;
import com.interview.notification.repositories.LogMessageRepository;
import com.interview.notification.repositories.UserRepository;
import com.interview.notification.services.notification.NotificationMessageEvent;

@Service
public class EmailNotification implements ApplicationListener<NotificationMessageEvent> {

	private UserRepository userRepository;
	private LogMessageRepository logMessageRepository;
	private ChannelNotificationRepository channelNotificationRepository;
	
	@Override
	public void onApplicationEvent(NotificationMessageEvent event) {
		
		System.out.println("Sending Email to " + event.getCategory().getDescription() + ": " + event.getMessage());
		 
		 Category category = event.getCategory();
		 List<UserCustomer> users = userRepository.findBySubscribedCategories(category);
		 ChannelNotification channel = channelNotificationRepository.findByTypeChannel(TypeChannel.EMAIL);
		 String message = event.getMessage();
		      
		  for(UserCustomer user: users) {
			  LogMessageSent log = new LogMessageSent();
			  log.setCategory(category);
			  log.setMessage(message);
			  log.setChannel(channel);
			  log.setUser(user);
			  logMessageRepository.save(log);
		  }
	}
	
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Autowired
	public void setLogMessageRepository(LogMessageRepository logMessageRepository) {
		this.logMessageRepository = logMessageRepository;
	}
	
	@Autowired
	public void setChannelNotificationRepository(ChannelNotificationRepository channelNotificationRepository) {
		this.channelNotificationRepository = channelNotificationRepository;
	}

}
