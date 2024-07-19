package com.interview.notification.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.interview.notification.dtos.CategoryDTO;
import com.interview.notification.dtos.LogMessageSentDTO;
import com.interview.notification.dtos.NewMessageDTO;
import com.interview.notification.dtos.mappers.CategoryMapper;
import com.interview.notification.dtos.mappers.LogMessageSentMapper;
import com.interview.notification.model.Category;
import com.interview.notification.model.LogMessageSent;
import com.interview.notification.model.UserCustomer;
import com.interview.notification.repositories.CategoryRepository;
import com.interview.notification.repositories.LogMessageSentRepository;
import com.interview.notification.repositories.UserRepository;
import com.interview.notification.services.notification.NotificationMessageEvent;

@Service
public class MessageService {

	private ApplicationEventPublisher eventPublisher;
	private CategoryRepository categoryRepository;
	private LogMessageSentRepository logMessageSentRepository;
	private UserRepository userRepository;
	 
	public void send( NewMessageDTO newMessageDTO) {
		String message = newMessageDTO.getMessage();
		Long idCategory = newMessageDTO.getIdCategory();
		Long idUser_origin = newMessageDTO.getIdUser_origin();
		
		Category category = categoryRepository.findById(idCategory).get();
		UserCustomer userOrigin = userRepository.findById(idUser_origin).get();
		
		NotificationMessageEvent event = new NotificationMessageEvent(this, message, category, userOrigin);
	    eventPublisher.publishEvent(event);
	}
	
	public List<LogMessageSentDTO> findAllLogMessageSent() {
		List<LogMessageSent> logMessageSentList = logMessageSentRepository.findAll();
		return LogMessageSentMapper.INSTANCE.toDTOList(logMessageSentList);
	}
	
	public List<CategoryDTO> findAllCategories() {
		 List<Category> categories = categoryRepository.findAll();
		 return CategoryMapper.INSTANCE.toDTOList(categories);
	}
	
	@Autowired
	public void setLogMessageSentRepository(LogMessageSentRepository logMessageSentRepository) {
		this.logMessageSentRepository = logMessageSentRepository;
	}
	@Autowired
	public void setEventPublisher(ApplicationEventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}
	@Autowired
	public void setCategoryRepository(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}





}
