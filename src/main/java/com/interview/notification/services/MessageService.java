package com.interview.notification.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.interview.notification.dtos.NewMessageDTO;
import com.interview.notification.enums.TypeCategory;
import com.interview.notification.model.Category;
import com.interview.notification.repositories.CategoryRepository;
import com.interview.notification.services.notification.NotificationMessageEvent;

@Service
public class MessageService {

	private ApplicationEventPublisher eventPublisher;
	
	private CategoryRepository categoryRepository;
	 
	public void send( NewMessageDTO newMessageDTO) {
		String message = newMessageDTO.getMessage();
		TypeCategory typCategory = TypeCategory.get(newMessageDTO.getCategory());
		
		Category category = categoryRepository.findByTypeCategory(typCategory);
		
		NotificationMessageEvent event = new NotificationMessageEvent(this, message, category);
	    eventPublisher.publishEvent(event);
	}
	
	@Autowired
	public void setEventPublisher(ApplicationEventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}
	
	@Autowired
	public void setCategoryRepository(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	

}
