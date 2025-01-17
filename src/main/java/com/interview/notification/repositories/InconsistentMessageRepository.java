package com.interview.notification.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.interview.notification.model.InconsistentMessage;

@Repository
public interface InconsistentMessageRepository  extends JpaRepository<InconsistentMessage, Long>  {

}
