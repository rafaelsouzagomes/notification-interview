package com.interview.notification.services.notification.producers.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import com.interview.notification.services.notification.producers.NotificationProducer;
import com.interview.notification.services.notification.producers.beans.NotificationMessageEvent;

@Service
public class EmailNotification implements ApplicationListener<NotificationMessageEvent> {

	private NotificationProducer notificationProducer;
	private ChannelProducerEmail channelProducerEmail;
	
	@Override
	public void onApplicationEvent(NotificationMessageEvent event) {
		notificationProducer.setChannelProducer(channelProducerEmail);
        notificationProducer.sendNotificationToQueue(event);  
	}
	
	@Autowired
	public void setChannelProducerEmail(ChannelProducerEmail channelProducerEmail) {
		this.channelProducerEmail = channelProducerEmail;
	}
	
	@Autowired
	public void setNotificationProducer(NotificationProducer notificationProducer) {
		this.notificationProducer = notificationProducer;
	}
	

}
