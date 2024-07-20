package com.interview.notification.services.notification.producers.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import com.interview.notification.services.notification.producers.NotificationProducer;
import com.interview.notification.services.notification.producers.beans.NotificationMessageEvent;

@Service
public class SmsNotificationProducer implements ApplicationListener<NotificationMessageEvent> {
	
	private NotificationProducer notificationProducer;
	private ChannelProducerSMS channelProducerSMS;
	
	@Override
    public void onApplicationEvent(NotificationMessageEvent event) {
        notificationProducer.setChannelProducer(channelProducerSMS);
        notificationProducer.sendNotificationToQueue(event);  
    }
	
	@Autowired
	public void setChannelProducerSMS(ChannelProducerSMS channelProducerSMS) {
		this.channelProducerSMS = channelProducerSMS;
	}
	
	@Autowired
	public void setNotificationProducer(NotificationProducer notificationProducer) {
		this.notificationProducer = notificationProducer;
	}
}
