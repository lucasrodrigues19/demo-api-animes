package com.lucasrodrigues.api.animes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories({"com.lucasrodrigues.api.animes.repositorys","com.lucasrodrigues.api.animes.repositorys.main"})
public class ApiAnimesApplication{

	public static void main(String[] args) {
		SpringApplication.run(ApiAnimesApplication.class, args);
	}

}
