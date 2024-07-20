package com.interview.notification.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.interview.notification.model.ArchivedMessage;

@Repository
public interface ArchivedMessageRepository extends JpaRepository<ArchivedMessage, Long>  {

}
