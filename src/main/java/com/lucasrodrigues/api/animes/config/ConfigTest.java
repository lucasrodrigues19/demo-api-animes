package com.lucasrodrigues.api.animes.config;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.lucasrodrigues.api.animes.domains.Anime;
import com.lucasrodrigues.api.animes.domains.Author;
import com.lucasrodrigues.api.animes.dto.requests.AnimePostRequestBody;
import com.lucasrodrigues.api.animes.services.AnimeService;
import com.lucasrodrigues.api.animes.services.AuthorService;

import lombok.extern.log4j.Log4j2;

@Log4j2
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
		
		Anime animeSaved1 = this.animeService.save(createAnime(au1,UUID.fromString("32be9b2b-94f7-45d5-884d-e5c08c00e3cd"), "Boruto"));
		Anime animeSaved2 = this.animeService.save(createAnimePostRequestBody(au1, "Naruto clássico"));
		Anime animeSaved3 = this.animeService.save(createAnimePostRequestBody(au1, "Dbz classico"));
		Anime animeSaved4 = this.animeService.save(createAnimePostRequestBody(au1, "One Piece"));
		Anime animeSaved5 = this.animeService.save(createAnimePostRequestBody(au1, "Death Note"));
		Anime animeSaved6 = this.animeService.save(createAnimePostRequestBody(au1, "Dbz super"));
		Anime animeSaved7 = this.animeService.save(createAnimePostRequestBody(au1, "Naruto Shippuden"));
		Anime animeSaved8 = this.animeService.save(createAnimePostRequestBody(au1, "Nanatsu tazai"));
//		
//
		System.out.println(animeSaved1.getId() + " - " + animeSaved1.toString());
//		
//		getForEntityAndGetForObject(animeSaved8);
//		
//		System.out.println("Post for Oject and Exchange");
//		
//		postForObjectAndExchange(createAnime(au1, null, "Kingdom"));
	}

	private void getForEntityAndGetForObject(Anime animeSaved8) {
		try {
			ResponseEntity<Anime> entity = new RestTemplate().getForEntity("http://localhost/anime/find/"+animeSaved8.getId().toString(), Anime.class);
			log.info(entity);
			
			Anime object = new RestTemplate().getForObject("http://localhost/anime/find/"+animeSaved8.getId().toString(), Anime.class);
			log.info(object);
			
			//Me retorna uma lista convertida
			ResponseEntity<List<Anime>> exchange = new RestTemplate().exchange("http://localhost/anime/findAll", HttpMethod.GET,null, new ParameterizedTypeReference<List<Anime>>() {});
			log.info(exchange.getBody());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void postForObjectAndExchange(Anime anime) {
		try {
			
			Anime object =  new RestTemplate().postForObject("http://localhost/anime/save", anime, Anime.class);
			log.info(object);
			
			anime.setName("Samurai champloo");
			ResponseEntity<Anime> exchange =  new RestTemplate().exchange("http://localhost/anime/save", HttpMethod.POST, new HttpEntity<>(anime), Anime.class);
			log.info(exchange.getBody());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private AnimePostRequestBody createAnimePostRequestBody(Author author, String name) {
		return AnimePostRequestBody.builder().name(name).authorId(author.getId()).build();
	}
	
	private Anime createAnime(Author author, UUID id, String name) {
		return Anime.builder().id(id).name(name).authorId(author.getId()).build();
	}
	
	private Author createAuthor(String name) {
		return Author.builder().name(name).build();
	}

}
