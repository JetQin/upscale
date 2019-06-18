package io.github.jetqin.upscale.service.events;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class PersonCreateEvent extends ApplicationEvent {

    String message;

    public PersonCreateEvent(Object source, String message) {
        super(source);
        this.message = message;
    }
}
