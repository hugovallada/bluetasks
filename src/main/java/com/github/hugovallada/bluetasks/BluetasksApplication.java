package com.github.hugovallada.bluetasks;

import com.github.hugovallada.bluetasks.domain.task.Task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@SpringBootApplication
public class BluetasksApplication implements RepositoryRestConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(BluetasksApplication.class, args);
	}

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Task.class);
	}

}
