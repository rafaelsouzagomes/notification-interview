package com.interview.notification.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.interview.notification.model.Category;
import com.interview.notification.model.ChannelNotification;
import com.interview.notification.model.UserCustomer;

public interface UserRepository extends JpaRepository<UserCustomer, Long>  {

	List<UserCustomer> findBySubscribedCategoriesAndChannels(Category category, ChannelNotification channel);

}
