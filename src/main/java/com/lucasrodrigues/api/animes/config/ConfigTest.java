package com.lucasrodrigues.api.animes.config;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.lucasrodrigues.api.animes.domains.Anime;
import com.lucasrodrigues.api.animes.domains.Author;
import com.lucasrodrigues.api.animes.services.AnimeService;
import com.lucasrodrigues.api.animes.services.AuthorService;

@Profile("test")
@Configuration
public class ConfigTest implements CommandLineRunner{

	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private AnimeService animeService;
	
	@Override
	public void run(String... args) throws Exception {
		
		
	}

}
