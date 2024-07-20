package com.interview.notification.services.notification.consumers;

import com.interview.notification.model.LogMessageSent;
import com.interview.notification.model.UserCustomer;
import com.interview.notification.services.notification.producers.beans.NotificationMessageBatchEvent;

public interface APIService {

	void createComunication(NotificationMessageBatchEvent batchEvent);

	void processItem(NotificationMessageBatchEvent batchEvent, UserCustomer user, LogMessageSent log);

	void onPostProcess(NotificationMessageBatchEvent batchEvent);

}
