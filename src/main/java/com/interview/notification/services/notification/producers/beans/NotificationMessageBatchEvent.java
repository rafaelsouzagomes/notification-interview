package com.interview.notification.services.notification.producers.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotificationMessageBatchEvent implements Serializable {

	private static final long serialVersionUID = 1L;

	private NotificationMessageEvent originalEvent;
    private List<Long> idUsersBatch;
    private Long idChannel;
    
    private String error;

    public NotificationMessageBatchEvent( @JsonProperty("originalEvent") NotificationMessageEvent originalEvent, 
    		 							  @JsonProperty("idUsersBatch") List<Long> idUsersBatch, 
    		 							  @JsonProperty("idChannel") Long idChannel) {
        this.originalEvent = originalEvent;
        this.idUsersBatch = idUsersBatch;
        this.idChannel = idChannel;
    }
    
	public Long getIdChannel() {
		return idChannel;
	}

    public NotificationMessageEvent getOriginalEvent() {
        return originalEvent;
    }

    public List<Long> getIdUsersBatch() {
		return idUsersBatch;
	}
    
    public void setError(String error) {
		this.error = error;
	}
    
    public String getError() {
		return error;
	}
}