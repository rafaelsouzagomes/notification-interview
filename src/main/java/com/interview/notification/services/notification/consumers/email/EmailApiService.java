package com.interview.notification.services.notification.consumers.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.notification.model.LogMessageSent;
import com.interview.notification.model.UserCustomer;
import com.interview.notification.services.notification.consumers.APIService;
import com.interview.notification.services.notification.producers.beans.NotificationMessageBatchEvent;
import com.interview.notification.validations.ValidatorUserCostumer;

@Service
public class EmailApiService implements APIService {
	
	private ValidatorUserCostumer validator;

	@Override
	public void createComunication(NotificationMessageBatchEvent batchEvent) {
		System.out.println("EMAIL - STARTING, SETTING API");
	}

	@Override
	public void processItem(NotificationMessageBatchEvent batchEvent, UserCustomer user, LogMessageSent log) {
		validator.validateEmail(user);
		System.out.println("EMAIL - USING API TO SAVE LOG: " + log.getIdLogMessageSent());
	}

	@Override
	public void onPostProcess(NotificationMessageBatchEvent batchEvent) {
		// SOMETHING
	}
	
	@Autowired
	public void setValidator(ValidatorUserCostumer validator) {
		this.validator = validator;
	}

}
