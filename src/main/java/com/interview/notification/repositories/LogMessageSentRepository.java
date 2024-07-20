package com.interview.notification.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.interview.notification.model.LogMessageSent;

@Repository
public interface LogMessageSentRepository  extends JpaRepository<LogMessageSent, Long>  {

    @Query("SELECT l FROM LogMessageSent l ORDER BY l.idLogMessageSent DESC")
    List<LogMessageSent> findAllOrderedByIdDesc();
}

