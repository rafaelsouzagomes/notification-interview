package com.interview.notification.services.notification.consumers.push;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.notification.services.notification.consumers.NotificationConsumer;
import com.interview.notification.services.notification.producers.beans.NotificationMessageBatchEvent;

@Service
public class PushNotificationConsumer {

	private PushNotificationApiService pushNotificationApiService;
	private NotificationConsumer notificationConsumer;

	@RabbitListener(queues = "pushNotificationQueue", concurrency = "5-10")
	public void receiveNotificationBatch(NotificationMessageBatchEvent batchEvent) {
		notificationConsumer.setAPIService(pushNotificationApiService);
		notificationConsumer.sendNotification(batchEvent);
	}

	@Autowired
	public void setPushNotificationApiService(PushNotificationApiService pushNotificationApiService) {
		this.pushNotificationApiService = pushNotificationApiService;
	}
	@Autowired
	public void setNotificationConsumer(NotificationConsumer notificationConsumer) {
		this.notificationConsumer = notificationConsumer;
	}
}
