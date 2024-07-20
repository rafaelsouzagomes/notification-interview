package com.interview.notification.services.notification.consumers.categoryprocess;

import com.interview.notification.model.UserCustomer;

/**
 * if each of the categories (finance, films, sports) has some specific behavior 
 * to be performed during sending. Like some validation, 
 * or activation of some third party service.
 * */
public interface ICategoryProcess {

	void onPreSending();

	void onPostMessageSent(String message, UserCustomer user);

	void onPostProcess();

}
