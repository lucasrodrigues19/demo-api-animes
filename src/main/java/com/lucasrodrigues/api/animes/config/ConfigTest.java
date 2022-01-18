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
import com.lucasrodrigues.api.animes.dto.requests.AnimePostRequestBody;
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
		
		Author au1 = this.authorService.save(createAuthor("Kishmoto"));
		Anime animeSaved1 = this.animeService.save(createAnimePostRequestBody(au1, "Boruto"));

		System.out.println(animeSaved1.toString());
		
	}
	
	private AnimePostRequestBody createAnimePostRequestBody(Author author, String name) {
		return AnimePostRequestBody.builder().name(name).authorId(author.getId()).build();
	}
	
	private Author createAuthor(String name) {
		return Author.builder().name(name).build();
	}

}
