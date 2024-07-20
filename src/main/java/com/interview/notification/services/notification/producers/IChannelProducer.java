package com.interview.notification.services.notification.producers;

import com.interview.notification.enums.TypeChannel;
import com.interview.notification.services.notification.producers.beans.NotificationMessageEvent;

public interface IChannelProducer {

	void onPrepare(NotificationMessageEvent event);

	TypeChannel getTypeChannel();

	String getRoutingKeyProducer();


}
