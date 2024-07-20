package com.interview.notification.services.notification.producers.sms;

import org.springframework.stereotype.Service;

import com.interview.notification.enums.TypeChannel;
import com.interview.notification.services.notification.producers.IChannelProducer;
import com.interview.notification.services.notification.producers.beans.NotificationMessageEvent;

@Service
public class ChannelProducerSMS implements IChannelProducer{

	@Override
	public void onPrepare(NotificationMessageEvent event) {
		System.out.println("[PRODUCER] Sending SMS to Queue : [IDCATEGORY: " + event.getIdCategory() + "] Message: " + event.getMessage());
	}

	@Override
	public TypeChannel getTypeChannel() {
		return TypeChannel.SMS;
	}

	@Override
	public String getRoutingKeyProducer() {
		return "smsQueue";
	}

}
