package io.github.jetqin.upscale.service.message.consumer;

import io.github.jetqin.upscale.domain.Person;
import io.github.jetqin.upscale.service.message.RabbitMessageContants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PersonRequestListener {

    @RabbitListener(queues = RabbitMessageContants.PERSON_REQUEST_QUEUE)
    public void processPersonRequest(Person person){
        log.info("Consume Person Request:"+ person);
    }

    @RabbitListener(queues = RabbitMessageContants.PERSON_RESPONSE_QUEUE)
    public void processPersonResponse(Person person){
        log.info("Consume Person Response:"+ person);
    }
}
