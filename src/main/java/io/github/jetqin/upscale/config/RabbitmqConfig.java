package io.github.jetqin.upscale.config;

import static io.github.jetqin.upscale.service.message.RabbitMessageContants.*;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    @Bean
    Queue personRequestQueue(){
        return QueueBuilder.nonDurable(PERSON_REQUEST_QUEUE).build();
    }

    @Bean
    Queue personResponseQueue(){
        return QueueBuilder.nonDurable(PERSON_RESPONSE_QUEUE).build();
    }

    @Bean
    Exchange rabbitmqExchange(){
       return ExchangeBuilder.directExchange(RABBITMQ_EXCHANGE).build();
    }


    @Bean
    Binding personRequestBinding(Queue personRequestQueue, Exchange rabbitmqExchange){
        return BindingBuilder.bind(personRequestQueue).to(rabbitmqExchange).with(PERSON_REQUEST_ROUTER).noargs();
    }

    @Bean
    Binding personResponseBinding(Queue personResponseQueue, Exchange rabbitmqExchange){
        return BindingBuilder.bind(personResponseQueue).to(rabbitmqExchange).with(PERSON_RESPONSE_ROUTER).noargs();
    }


    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


}
