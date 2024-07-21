package com.interview.notification.services.notification.consumers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.interview.notification.enums.TypeCategory;
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

class NotificationConsumerTest {
	 @InjectMocks
	    private NotificationConsumer notificationConsumer;

	    @Mock
	    private APIService service_API;

	    @Mock
	    private ICategoryProcessFactory ICategoryProcessFactory;

	    @Mock
	    private RabbitTemplate rabbitTemplate;

	    @Mock
	    private LogMessageSentRepository logMessageRepository;

	    @Mock
	    private ChannelNotificationRepository channelRepository;

	    @Mock
	    private UserRepository userRepository;

	    @Mock
	    private CategoryRepository categoryRepository;

	    @Mock
	    private ICategoryProcess categoryProcess;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	        when(ICategoryProcessFactory.getCategoryBy(anyString())).thenReturn(categoryProcess);
	    }

	    @Test
	    void testSendNotification_Success() {
	        NotificationMessageBatchEvent batchEvent = createBatchEvent();
	        UserCustomer user = createUser(1L);
	        ChannelNotification channel = new ChannelNotification();
	        Category category = new Category();
	        category.setTypeCategory(TypeCategory.FINANCE);

	        when(userRepository.findByIdUserIn(batchEvent.getIdUsersBatch())).thenReturn(Arrays.asList(user));
	        when(channelRepository.findById(batchEvent.getIdChannel())).thenReturn(Optional.of(channel));
	        when(categoryRepository.findById(batchEvent.getOriginalEvent().getIdCategory())).thenReturn(Optional.of(category));
	        when(userRepository.findById(batchEvent.getOriginalEvent().getIdUserOrigin())).thenReturn(Optional.of(user));
	        when(logMessageRepository.save(any(LogMessageSent.class))).thenReturn(new LogMessageSent());

	        notificationConsumer.sendNotification(batchEvent);

	        verify(categoryProcess).onPreSending();
	        verify(service_API).createComunication(batchEvent);
	        verify(service_API).processItem(eq(batchEvent), eq(user), any(LogMessageSent.class));
	        verify(categoryProcess).onPostMessageSent(anyString(), eq(user));
	        verify(service_API).onPostProcess(batchEvent);
	        verify(categoryProcess).onPostProcess();
	    }

	    @Test
	    void testSendNotification_ExceptionHandling() {
	        NotificationMessageBatchEvent batchEvent = createBatchEvent();
	        batchEvent = spy(batchEvent); // Usar spy para verificar a chamada do método setError

	        when(userRepository.findByIdUserIn(batchEvent.getIdUsersBatch())).thenThrow(new RuntimeException("Test exception"));

	        notificationConsumer.sendNotification(batchEvent);

	        verify(rabbitTemplate).convertAndSend(eq("dlxExchange"), eq("#"), eq(batchEvent));
	        verify(batchEvent).setError(anyString()); // Verificar a chamada do método setError
	    }

	    private NotificationMessageBatchEvent createBatchEvent() {
	        NotificationMessageEvent event = new NotificationMessageEvent(this, "Message", 1L, 1L);
	        return new NotificationMessageBatchEvent(event, Arrays.asList(1L), 1L);
	    }

	    private UserCustomer createUser(Long id) {
	        UserCustomer user = new UserCustomer();
	        user.setIdUser(id);
	        return user;
	    }
}
