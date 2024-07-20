package com.interview.notification.services.notification.consumers.categoryprocess.impls;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.interview.notification.model.UserCustomer;
import com.interview.notification.services.notification.consumers.categoryprocess.ICategoryProcess;

@Qualifier("SPORTS")
@Service
public class SportCategory  implements ICategoryProcess{

	@Override
	public void onPreSending() {
		// A especific implementation for all of SPORTS MESSAGES
	}

	@Override
	public void onPostMessageSent(String message, UserCustomer user) {
		// A especific implementation for all of SPORTS MESSAGES
	}

	@Override
	public void onPostProcess() {
		// A especific implementation for all of SPORTS MESSAGES
	}

}
