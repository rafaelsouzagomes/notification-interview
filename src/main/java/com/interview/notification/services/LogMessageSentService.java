package com.interview.notification.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.notification.dtos.LogMessageSentDTO;
import com.interview.notification.dtos.mappers.LogMessageSentMapper;
import com.interview.notification.model.LogMessageSent;
import com.interview.notification.repositories.LogMessageSentRepository;

@Service
public class LogMessageSentService {

	private LogMessageSentRepository logMessageSentRepository;
	
	public List<LogMessageSentDTO> findAllLogMessageSent() {
		List<LogMessageSent> logMessageSentList = logMessageSentRepository.findAll();
		return LogMessageSentMapper.INSTANCE.toDTOList(logMessageSentList);
	}
	
	@Autowired
	public void setLogMessageSentRepository(LogMessageSentRepository logMessageSentRepository) {
		this.logMessageSentRepository = logMessageSentRepository;
	}
}
