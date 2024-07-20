package com.interview.notification.services.notification.consumers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.interview.notification.model.Category;
import com.interview.notification.model.ChannelNotification;
import com.interview.notification.model.LogMessageSent;
import com.interview.notification.model.UserCustomer;
import com.interview.notification.repositories.CategoryRepository;
import com.interview.notification.repositories.ChannelNotificationRepository;
import com.interview.notification.repositories.LogMessageSentRepository;
import com.interview.notification.repositories.UserRepository;
import com.interview.notification.services.notification.consumers.categoryprocess.ICategoryProcess;
import com.interview.notification.services.notification.consumers.categoryprocess.ICategoryProcessFactory;
import com.interview.notification.services.notification.producers.beans.NotificationMessageBatchEvent;
import com.interview.notification.services.notification.producers.beans.NotificationMessageEvent;

@Service
public class NotificationConsumer {

	private LogMessageSentRepository logMessageRepository;
	private ICategoryProcessFactory ICategoryProcessFactory;
	private APIService service_API;
	private ChannelNotificationRepository channelRepository;
	private UserRepository userRepository;
	private CategoryRepository categoryRepository;
	
	public void sendNotification(NotificationMessageBatchEvent batchEvent) {
	        try {
	        	List<UserCustomer> usersToSend = userRepository.findByIdUserIn(batchEvent.getIdUsersBatch());
	        	ChannelNotification channel =  channelRepository.findById(batchEvent.getIdChannel()).get();
	            NotificationMessageEvent event = batchEvent.getOriginalEvent();
	            
	            Category category = categoryRepository.findById(event.getIdCategory()).get();            
	            String message = event.getMessage();
	            UserCustomer userOrigin = userRepository.findById(event.getIdUserOrigin()).get();
	            
	            ICategoryProcess categoryProcess = ICategoryProcessFactory.getCategoryBy(category.getTypeCategory().name());            
	            categoryProcess.onPreSending();
	            
	            service_API.createComunication(batchEvent);
	            
	            
	            for (UserCustomer user : usersToSend) {
	                LogMessageSent log = new LogMessageSent();
	                log.setCategory(category);
	                log.setMessage(message);
	                log.setChannel(channel);
	                log.setUser_origin(userOrigin);
	                log.setUser_destination(user);
	                LogMessageSent logManaged = logMessageRepository.save(log);
	                
	                service_API.processItem(batchEvent, user, logManaged);
	                categoryProcess.onPostMessageSent(message,user);
	            }
	            
	            service_API.onPostProcess(batchEvent);
	            categoryProcess.onPostProcess();
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	
	
	@Autowired
	public void setChannelRepository(ChannelNotificationRepository channelRepository) {
		this.channelRepository = channelRepository;
	}
	
	@Autowired
	public void setCategoryRepository(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	@Autowired
	public void setAPIService(@Lazy APIService aPIService) {
		service_API = aPIService;
	}
	
	@Autowired
	public void setICategoryProcessFactory(ICategoryProcessFactory iCategoryProcessFactory) {
		ICategoryProcessFactory = iCategoryProcessFactory;
	}
	
	@Autowired
	public void setLogMessageRepository(LogMessageSentRepository logMessageRepository) {
		this.logMessageRepository = logMessageRepository;
	}
	
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
}
