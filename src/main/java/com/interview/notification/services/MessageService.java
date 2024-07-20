package com.interview.notification.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.interview.notification.dtos.NewMessageDTO;
import com.interview.notification.services.notification.producers.beans.NotificationMessageEvent;
import com.interview.notification.validations.ValidatorCategory;
import com.interview.notification.validations.ValidatorMessage;
import com.interview.notification.validations.ValidatorUserCostumer;

@Service
public class MessageService {

	private ApplicationEventPublisher eventPublisher;

	private ValidatorCategory validatorCategory;
	private ValidatorUserCostumer validatorUserCostumer;
	private ValidatorMessage validatorMessage;
	 
	public void send( NewMessageDTO newMessageDTO) {
		String message = newMessageDTO.getMessage();
		Long idCategory = newMessageDTO.getIdCategory();
		Long idUser_origin = newMessageDTO.getIdUser_origin();
		
		validatorMessage.validateMessage(message);
		validatorCategory.validateEntity(idCategory);
		validatorUserCostumer.validateEntity(idUser_origin);
		
		NotificationMessageEvent event = new NotificationMessageEvent(this, message, idCategory, idUser_origin);
	    eventPublisher.publishEvent(event);
	}
	
	@Autowired
	public void setValidatorMessage(ValidatorMessage validatorMessage) {
		this.validatorMessage = validatorMessage;
	}
	@Autowired
	public void setValidatorUserCostumer(ValidatorUserCostumer validatorUserCostumer) {
		this.validatorUserCostumer = validatorUserCostumer;
	}
	@Autowired
	public void setValidatorCategory(ValidatorCategory validatorCategory) {
		this.validatorCategory = validatorCategory;
	}
	@Autowired
	public void setEventPublisher(ApplicationEventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}






}
