package com.interview.notification.services.notification.errorhandler;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.notification.model.Category;
import com.interview.notification.model.ChannelNotification;
import com.interview.notification.model.InconsistentMessage;
import com.interview.notification.repositories.CategoryRepository;
import com.interview.notification.repositories.ChannelNotificationRepository;
import com.interview.notification.repositories.InconsistentMessageRepository;
import com.interview.notification.services.notification.producers.beans.NotificationMessageBatchEvent;
import com.interview.notification.services.notification.producers.beans.NotificationMessageEvent;

@Service
public class DeadLetterQueueConsumer {
	
	private CategoryRepository categoryRepository;
	private ChannelNotificationRepository channelRepository;
	private InconsistentMessageRepository inconsistentMessageRepository;

	@RabbitListener(queues = "deadLetterQueue")
    public void receiveFromDeadLetterQueue(NotificationMessageBatchEvent event) {
		System.out.println("Received (Problem) message from Dead Letter Queue: " + event);
        
        String error = event.getError();
        Long idChannel = event.getIdChannel();
        ChannelNotification channelNotification = channelRepository.findById(idChannel).get();
        
        NotificationMessageEvent originalEvent = event.getOriginalEvent();
        String message = originalEvent.getMessage();
        
        Long idCategory = originalEvent.getIdCategory();
        Category category = categoryRepository.findById(idCategory).get();
        InconsistentMessage newError = new InconsistentMessage();
        newError.setError(error);
        newError.setCategory(category);
        newError.setChannel(channelNotification);
        newError.setMessage(message);
        inconsistentMessageRepository.save(newError);
        
        System.out.println("SAVED (Problem) message from Dead Letter Queue: " + event);
	}
	
	@Autowired
	public void setInconsistentMessageRepository(InconsistentMessageRepository inconsistentMessageRepository) {
		this.inconsistentMessageRepository = inconsistentMessageRepository;
	}
	
	@Autowired
	public void setChannelRepository(ChannelNotificationRepository channelRepository) {
		this.channelRepository = channelRepository;
	}
	
	@Autowired
	public void setCategoryRepository(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
}
