package io.github.jetqin.upscale.service.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@Slf4j
public class EventListenerService {

    @EventListener(classes = {PersonCreateEvent.class})
    public void handlePersonCreate(PersonCreateEvent event){
        log.info("Peson create event:" + event);
    }

    @EventListener(classes = {PersonLoginEvent.class})
    public void handlePersonLogin(PersonLoginEvent event){
        log.info("Peson login event:" + event);
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handlePersonPersist(PersonCreateEvent event){
        log.info("*********************************************");
        log.info("Peson transaction create event:" + event);
    }
}
