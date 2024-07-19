package com.interview.notification.services.notification.pushNotificantion;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.interview.notification.enums.TypeChannel;
import com.interview.notification.model.Category;
import com.interview.notification.model.ChannelNotification;
import com.interview.notification.model.LogMessageSent;
import com.interview.notification.model.UserCustomer;
import com.interview.notification.repositories.ChannelNotificationRepository;
import com.interview.notification.repositories.LogMessageSentRepository;
import com.interview.notification.repositories.UserRepository;
import com.interview.notification.services.notification.NotificationMessageEvent;

@Component
public class PushNotification  implements ApplicationListener<NotificationMessageEvent> {

	
	private UserRepository userRepository;
	private LogMessageSentRepository logMessageRepository;
	private ChannelNotificationRepository channelNotificationRepository;
	
	@Override
	public void onApplicationEvent(NotificationMessageEvent event) {
		 System.out.println("Sending Push Notification to " + event.getCategory().getDescription() + ": " + event.getMessage());
		 
		 Category category = event.getCategory();
		 ChannelNotification channel = channelNotificationRepository.findByTypeChannel(TypeChannel.PUSH_NOTIFICATION);
	
		 List<UserCustomer> users = userRepository.findBySubscribedCategoriesAndChannels(category, channel);
		 String message = event.getMessage();
		 UserCustomer userOrigin = event.getUserOrigin();
		 
		  for(UserCustomer user: users) {
			  LogMessageSent log = new LogMessageSent();
			  log.setCategory(category);
			  log.setMessage(message);
			  log.setChannel(channel);
			  log.setUser_destination(user);
			  log.setUser_origin(userOrigin);
			  logMessageRepository.save(log);
		  }
	}
	
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Autowired
	public void setLogMessageRepository(LogMessageSentRepository logMessageRepository) {
		this.logMessageRepository = logMessageRepository;
	}
	
	@Autowired
	public void setChannelNotificationRepository(ChannelNotificationRepository channelNotificationRepository) {
		this.channelNotificationRepository = channelNotificationRepository;
	}

}
