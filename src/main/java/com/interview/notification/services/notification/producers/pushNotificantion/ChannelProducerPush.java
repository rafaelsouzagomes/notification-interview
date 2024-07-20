package com.interview.notification.services.notification.producers.pushNotificantion;

import org.springframework.stereotype.Service;

import com.interview.notification.enums.TypeChannel;
import com.interview.notification.services.notification.producers.IChannelProducer;
import com.interview.notification.services.notification.producers.beans.NotificationMessageEvent;

@Service
public class ChannelProducerPush implements IChannelProducer {

	@Override
	public TypeChannel getTypeChannel() {
		return TypeChannel.PUSH_NOTIFICATION;
	}

	@Override
	public String getRoutingKeyProducer() {
		return "pushNotificationQueue";
	}

	@Override
	public void onPrepare(NotificationMessageEvent event) {
		System.out.println("[PRODUCER] Sending PUSH NOTIFICATION to Queue : [IDCATEGORY: " + event.getIdCategory() + "] Message: " + event.getMessage());
	}

}
