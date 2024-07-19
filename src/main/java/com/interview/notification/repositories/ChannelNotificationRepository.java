package com.interview.notification.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.interview.notification.enums.TypeChannel;
import com.interview.notification.model.ChannelNotification;

public interface ChannelNotificationRepository  extends JpaRepository<ChannelNotification, Long>  {


	ChannelNotification findByTypeChannel(TypeChannel email);

}