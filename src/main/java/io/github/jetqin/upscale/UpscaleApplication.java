package io.github.jetqin.upscale;

import static io.github.jetqin.upscale.service.message.RabbitMessageContants.*;

import io.github.jetqin.upscale.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Collections;

@Slf4j
@EnableJpaRepositories("io.github.jetqin.upscale.repository")
@SpringBootApplication
public class UpscaleApplication implements CommandLineRunner,ApplicationListener<ApplicationStartedEvent> {

	@Autowired
	RabbitTemplate rabbitTemplate;

	public static void main(String[] args) {
		SpringApplication.run(UpscaleApplication.class, args);
	}


	@Override
	public void onApplicationEvent(ApplicationStartedEvent event) {
		log.info("Application {} start", "Upscale");
//		Flyway flyway = Flyway.configure().dataSource("jdbc:postgresql://localhost:5432/postdb?currentSchema=upscale","dbusesr","dbuser").load();

		// Start the migration
//		flyway.migrate();

	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Start send message");
		rabbitTemplate.convertAndSend(RABBITMQ_EXCHANGE, PERSON_REQUEST_ROUTER, new Person(1L,"Jet Qin", Collections.emptyList()));
		rabbitTemplate.convertAndSend(RABBITMQ_EXCHANGE, PERSON_RESPONSE_ROUTER, new Person(2L,"Bruce Qin", Collections.emptyList()));
	}
}
