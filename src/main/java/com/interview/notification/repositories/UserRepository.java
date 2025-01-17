package com.interview.notification.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.interview.notification.model.Category;
import com.interview.notification.model.ChannelNotification;
import com.interview.notification.model.UserCustomer;

@Repository
public interface UserRepository extends JpaRepository<UserCustomer, Long>  {

	List<UserCustomer> findBySubscribedCategoriesAndChannels(Category category, ChannelNotification channel);

	List<UserCustomer> findByIdUserIn(List<Long> idUsersBatch);

}
