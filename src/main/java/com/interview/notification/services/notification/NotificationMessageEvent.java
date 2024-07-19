package com.interview.notification.services.notification;

import org.springframework.context.ApplicationEvent;

import com.interview.notification.model.Category;

import com.interview.notification.model.Category;

public class NotificationMessageEvent extends ApplicationEvent {
	
	private static final long serialVersionUID = 1L;
	
	private String message;
    private Category category;

    public NotificationMessageEvent(Object source, String message, Category category) {
        super(source);
        this.message = message;
        this.category = category;
    }

    public String getMessage() {
        return message;
    }
    
    public Category getCategory() {
		return category;
	}

    

}
