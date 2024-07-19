package com.interview.notification.controllers;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interview.notification.dtos.NewMessageDTO;
import com.interview.notification.services.MessageService;

@CrossOrigin
@Transactional
@RestController
@RequestMapping("/message")
public class MessageController {
	
	private MessageService messageService;
	
	@CrossOrigin
	@PostMapping("/add")
	public void send(@Valid @RequestBody NewMessageDTO newMessageDTO)  {
		messageService.send(newMessageDTO);
	}
	
	
	@Autowired
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	
}
