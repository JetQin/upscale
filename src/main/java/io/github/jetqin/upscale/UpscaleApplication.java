package io.github.jetqin.upscale;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Slf4j
@EnableJpaRepositories("io.github.jetqin.upscale.repository")
@SpringBootApplication
public class UpscaleApplication implements ApplicationListener<ApplicationStartedEvent> {

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
}
