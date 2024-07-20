package com.interview.notification.services.notification.producers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.interview.notification.enums.TypeChannel;
import com.interview.notification.model.Category;
import com.interview.notification.model.ChannelNotification;
import com.interview.notification.model.UserCustomer;
import com.interview.notification.repositories.CategoryRepository;
import com.interview.notification.repositories.ChannelNotificationRepository;
import com.interview.notification.repositories.UserRepository;
import com.interview.notification.services.notification.producers.beans.NotificationMessageBatchEvent;
import com.interview.notification.services.notification.producers.beans.NotificationMessageEvent;

@Service
public class NotificationProducer {

	private IChannelProducer channelProducer;
	private UserRepository userRepository;
	private ChannelNotificationRepository channelNotificationRepository;
	private RabbitTemplate rabbitTemplate;
	private CategoryRepository categoryRepository;
	
	private final int BATCH_SIZE = 100;     
	
	public void sendNotificationToQueue(NotificationMessageEvent event) {
		channelProducer.onPrepare(event);
		
		TypeChannel typeChannel = channelProducer.getTypeChannel();
        ChannelNotification channel = channelNotificationRepository.findByTypeChannel(typeChannel);
        Category category = categoryRepository.findById(event.getIdCategory()).get();
        
        List<UserCustomer> users = userRepository.findBySubscribedCategoriesAndChannels(category,channel);
        String rountingKeyProducer = channelProducer.getRoutingKeyProducer();
        
        for (int i = 0; i < users.size(); i += BATCH_SIZE) {
            List<UserCustomer> batch = users.subList(i, Math.min(i + BATCH_SIZE, users.size()));
            
            List<Long> idUsersBath = batch.stream().map(UserCustomer::getIdUser).collect(Collectors.toList());
            		
            NotificationMessageBatchEvent batchEvent = new NotificationMessageBatchEvent(event, idUsersBath, channel.getIdChannelNotification());
            rabbitTemplate.convertAndSend(rountingKeyProducer, batchEvent);
        }
	}
	@Autowired
	public void setCategoryRepository(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	@Autowired
	public void setChannelProducer(@Lazy IChannelProducer channelProducer) {
		this.channelProducer = channelProducer;
	}
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	@Autowired
	public void setChannelNotificationRepository(ChannelNotificationRepository channelNotificationRepository) {
		this.channelNotificationRepository = channelNotificationRepository;
	}
	@Autowired
	public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
}
