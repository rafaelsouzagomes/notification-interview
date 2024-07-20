package com.interview.notification.services.notification.consumers.sms;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.notification.services.notification.consumers.NotificationConsumer;
import com.interview.notification.services.notification.producers.beans.NotificationMessageBatchEvent;

@Service
public class SmsNotificationConsumer {
	
	private NotificationConsumer notificationConsumer;
	private SmsApiService smsApiService;
	
    @RabbitListener(queues = "smsQueue", concurrency = "5-10")
    public void receiveNotificationBatch(NotificationMessageBatchEvent batchEvent) {
    	notificationConsumer.setAPIService(smsApiService);
    	notificationConsumer.sendNotification(batchEvent);
    }

    @Autowired
    public void setNotificationConsumer(NotificationConsumer notificationConsumer) {
		this.notificationConsumer = notificationConsumer;
	}
    
    @Autowired
    public void setSmsApiService(SmsApiService smsApiService) {
		this.smsApiService = smsApiService;
	}
}
