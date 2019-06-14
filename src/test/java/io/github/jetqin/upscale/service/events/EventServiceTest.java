package io.github.jetqin.upscale.service.events;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.ApplicationEventPublisher;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceTest {

    @Mock
    ApplicationEventPublisher publisher;

    @Mock
    EventListenerService eventListenerService;

    @InjectMocks
    EventPublisherService  eventPublisherService;


    @Before
    public void init(){
        eventPublisherService = new EventPublisherService(publisher);
        eventListenerService = new EventListenerService();
    }

    @Test
    public void testEventPublishService(){
        PersonCreateEvent event = new PersonCreateEvent("","Person Created");
        eventPublisherService.publishMessage(event);
        verify(eventListenerService, timeout(1000)).handlePersonCreate(any(PersonCreateEvent.class));
        verify(publisher, times(1)).publishEvent(any(PersonCreateEvent.class));

    }
}
