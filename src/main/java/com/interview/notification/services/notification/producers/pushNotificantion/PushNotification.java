package com.interview.notification.services.notification.producers.pushNotificantion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.interview.notification.services.notification.producers.NotificationProducer;
import com.interview.notification.services.notification.producers.beans.NotificationMessageEvent;

@Component
public class PushNotification  implements ApplicationListener<NotificationMessageEvent> {

	private NotificationProducer notificationProducer;
	private ChannelProducerPush channelProducerPush;
	
	@Override
	public void onApplicationEvent(NotificationMessageEvent event) {
		notificationProducer.setChannelProducer(channelProducerPush);
	    notificationProducer.sendNotificationToQueue(event);  
	}
	
	@Autowired
	public void setChannelProducerPush(ChannelProducerPush channelProducerPush) {
		this.channelProducerPush = channelProducerPush;
	}
	@Autowired
	public void setNotificationProducer(NotificationProducer notificationProducer) {
		this.notificationProducer = notificationProducer;
	}
}
