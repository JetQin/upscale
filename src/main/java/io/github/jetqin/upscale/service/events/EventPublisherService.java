package io.github.jetqin.upscale.service.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class EventPublisherService {


    ApplicationEventPublisher publisher;

    @Autowired
    public EventPublisherService(ApplicationEventPublisher publisher){
        this.publisher = publisher;
    }

    public void publishMessage(ApplicationEvent event){
        publisher.publishEvent(event);
    }
}
