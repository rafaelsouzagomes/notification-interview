package com.interview.notification.services.notification.producers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.interview.notification.enums.TypeChannel;
import com.interview.notification.model.Category;
import com.interview.notification.model.ChannelNotification;
import com.interview.notification.model.UserCustomer;
import com.interview.notification.repositories.CategoryRepository;
import com.interview.notification.repositories.ChannelNotificationRepository;
import com.interview.notification.repositories.UserRepository;
import com.interview.notification.services.ArchivedMessageService;
import com.interview.notification.services.notification.producers.beans.NotificationMessageBatchEvent;
import com.interview.notification.services.notification.producers.beans.NotificationMessageEvent;

class NotificationProducerTest {

    @InjectMocks
    private NotificationProducer notificationProducer;

    @Mock
    private IChannelProducer channelProducer;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ChannelNotificationRepository channelNotificationRepository;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ArchivedMessageService archivedMessageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendNotificationToQueue_NoSubscribers() {
        NotificationMessageEvent event = new NotificationMessageEvent(this,"text",1l,1l);

        TypeChannel typeChannel = TypeChannel.EMAIL;
        ChannelNotification channelNotification = new ChannelNotification();
        Category category = new Category();

        when(channelProducer.getTypeChannel()).thenReturn(typeChannel);
        when(channelNotificationRepository.findByTypeChannel(typeChannel)).thenReturn(channelNotification);
        when(categoryRepository.findById(event.getIdCategory())).thenReturn(Optional.of(category));
        when(userRepository.findBySubscribedCategoriesAndChannels(category, channelNotification)).thenReturn(Collections.emptyList());

        notificationProducer.sendNotificationToQueue(event);

        verify(archivedMessageService, times(1)).archive(channelNotification, category, event);
        verify(rabbitTemplate, never()).convertAndSend(anyString(), any(NotificationMessageBatchEvent.class));
    }

    @Test
    void testSendNotificationToQueue_WithSubscribers() {
    	NotificationMessageEvent event = new NotificationMessageEvent(this,"text",1l,1l);

        TypeChannel typeChannel = TypeChannel.EMAIL;
        ChannelNotification channelNotification = new ChannelNotification();
        channelNotification.setIdChannelNotification(1L);
        Category category = new Category();
        UserCustomer user1 = new UserCustomer();
        user1.setIdUser(1L);
        UserCustomer user2 = new UserCustomer();
        user2.setIdUser(2L);

        when(channelProducer.getTypeChannel()).thenReturn(typeChannel);
        when(channelNotificationRepository.findByTypeChannel(typeChannel)).thenReturn(channelNotification);
        when(categoryRepository.findById(event.getIdCategory())).thenReturn(Optional.of(category));
        when(userRepository.findBySubscribedCategoriesAndChannels(category, channelNotification)).thenReturn(Arrays.asList(user1, user2));
        when(channelProducer.getRoutingKeyProducer()).thenReturn("test.routing.key");

        notificationProducer.sendNotificationToQueue(event);

        verify(rabbitTemplate, times(1)).convertAndSend(eq("test.routing.key"), any(NotificationMessageBatchEvent.class));
        verify(archivedMessageService, never()).archive(any(), any(), any());
    }

}
