package com.github.hugovallada.bluetasks;

import com.github.hugovallada.bluetasks.domain.task.Task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@SpringBootApplication
public class BluetasksApplication implements RepositoryRestConfigurer {

	private static final Logger logger = LoggerFactory.getLogger(BluetasksApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BluetasksApplication.class, args);
		logger.info("Bluetasks is ready for work");
	}

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		config.exposeIdsFor(Task.class);

		// CORS
		cors.addMapping("/**").allowedOrigins("*").allowedMethods("*");

		logger.info("Repository CORS setup... OK");
	}

	@Bean
	public Validator validator() {
		return new LocalValidatorFactoryBean();
	}

	@Override
	public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener vrel) {
		var validator = validator();
		vrel.addValidator("beforeCreate", validator);
		vrel.addValidator("beforeSave", validator);

		logger.info("Configure validator... OK!");
	}

}
