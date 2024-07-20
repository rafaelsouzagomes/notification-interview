package com.interview.notification.services.notification.consumers.email;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.notification.services.notification.consumers.NotificationConsumer;
import com.interview.notification.services.notification.producers.beans.NotificationMessageBatchEvent;

@Service
public class EmailNotificationConsumer {

	private EmailApiService emailApiService;
	private NotificationConsumer notificationConsumer;

	@RabbitListener(queues = "emailQueue", concurrency = "5-10")
	public void receiveNotificationBatch(NotificationMessageBatchEvent batchEvent) {
		notificationConsumer.setAPIService(emailApiService);
		notificationConsumer.sendNotification(batchEvent);
	}

	@Autowired
	public void setEmailApiService(EmailApiService emailApiService) {
		this.emailApiService = emailApiService;
	}
	
	@Autowired
	public void setNotificationConsumer(NotificationConsumer notificationConsumer) {
		this.notificationConsumer = notificationConsumer;
	}
}
