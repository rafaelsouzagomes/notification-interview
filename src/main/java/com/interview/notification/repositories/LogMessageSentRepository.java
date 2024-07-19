package com.interview.notification.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.interview.notification.model.LogMessageSent;

public interface LogMessageSentRepository  extends JpaRepository<LogMessageSent, Long>  {

}

