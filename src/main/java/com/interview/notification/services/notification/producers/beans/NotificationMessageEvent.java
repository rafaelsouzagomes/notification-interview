package com.interview.notification.services.notification.producers.beans;

import java.io.Serializable;
import java.util.Date;

import org.springframework.context.ApplicationEvent;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotificationMessageEvent extends ApplicationEvent implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String message;
    private Long idCategory;
    private Long idUserOrigin;
    private Date date;

    public NotificationMessageEvent(@JsonProperty("source") Object source, 
    								@JsonProperty("message") String message, 
    								@JsonProperty("idCategory") Long idCategory, 
    								@JsonProperty("idUserOrigin")Long idUserOrigin) {
        super(source);
        this.message = message;
        this.idCategory = idCategory;
        this.idUserOrigin = idUserOrigin;
        this.date = new Date();
    }
    
    public String getMessage() {
        return message;
    }
    
    public Long getIdCategory() {
		return idCategory;
	}
    
    public Long getIdUserOrigin() {
		return idUserOrigin;
	}
    
    public Date getDate() {
		return date;
	}

    

}
