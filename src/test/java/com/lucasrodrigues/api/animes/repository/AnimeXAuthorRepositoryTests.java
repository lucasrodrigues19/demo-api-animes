package com.lucasrodrigues.api.animes.repository;


import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.lucasrodrigues.api.animes.domains.Anime;
import com.lucasrodrigues.api.animes.domains.Author;
import com.lucasrodrigues.api.animes.repositorys.AnimeRepository;
import com.lucasrodrigues.api.animes.repositorys.AuthorRepository;
import com.lucasrodrigues.api.animes.util.AnimeCreator;

import lombok.extern.log4j.Log4j2;

@Log4j2
@DataJpaTest
@DisplayName(" Tests for anime and author repository")
class AnimeXAuthorRepositoryTests {

	@Autowired //Não é indicado para testes
	private AnimeRepository animeRepository;
	
	@Autowired 
	private AuthorRepository authorRepository;
	
	@Test
	@DisplayName("Save creates author and anime when success")
	void save_PersistAuthorAndAnime_WhenSuccess() {
		Author au1 = this.authorRepository.save(AnimeCreator.createAuthor("Kishmoto"));
		Anime animeSaved1 = this.animeRepository.save(AnimeCreator.createAnimeToSave(au1, "Boruto"));
		
		//Saber se o anime foi salvo
		
		//Valido se o anime é diferente de null
		Assertions.assertThat(animeSaved1).isNotNull();
		
		//Valido se o id do anime é diferente de null
		Assertions.assertThat(animeSaved1.getId()).isNotNull();
		
		//Valido se o nome do anime é diferente de null
		Assertions.assertThat(animeSaved1.getName()).isNotNull();
		
		//Valido se o author do anime é diferente de null
		Assertions.assertThat(animeSaved1.getAuthorId()).isNotNull();
	}
	
	
	@Test
	@DisplayName("save updates anime when success")
	void save_UpdateAnime_WhenSuccess() {
		
		Author au1 = this.authorRepository.save(AnimeCreator.createAuthor("Kishmoto"));
		Anime animeSaved1 = this.animeRepository.save(AnimeCreator.createAnimeToSave(au1, "Boruto"));
		
		
		animeSaved1.setName("Dragon ball super");
		
		
		Anime animeUpdated = this.animeRepository.save(animeSaved1);
		
		log.info(animeSaved1);
		log.info(animeUpdated);
		
		//Valido se o anime é diferente de null
		Assertions.assertThat(animeUpdated).isNotNull();
		
		//Valido se o id do anime é diferente de null
		Assertions.assertThat(animeUpdated.getId()).isNotNull();
		
		//Valido se o nome do anime é diferente de null
		Assertions.assertThat(animeUpdated.getName()).isNotNull();
		
		//Valido se o author do anime é diferente de null
		Assertions.assertThat(animeUpdated.getAuthorId()).isNotNull();
		
		
		Assertions.assertThat(animeUpdated.getName()).isEqualTo(animeSaved1.getName());
	}
	
	
	@Test
	@DisplayName("delete remove anime when success")
	void delete_RemoveAnime_WhenSuccess() {
		
		Author au1 = this.authorRepository.save(AnimeCreator.createAuthor("Kishmoto"));
		Anime animeSaved1 = this.animeRepository.save(AnimeCreator.createAnimeToSave(au1, "Boruto deletado"));
		
		this.animeRepository.delete(animeSaved1);
		
		Optional<Anime> animeDeleted = this.animeRepository.findById(animeSaved1.getId());
		
		Assertions.assertThat(animeDeleted.isPresent()).isFalse();
		
	}
	
	
	@Test
	@DisplayName("find by name returns list when anime is found")
	void findByName_ReturnList_WhenAnimeIsFound() {
		
		Author au1 = this.authorRepository.save(AnimeCreator.createAuthor("Kishmoto"));
		Anime animeSaved1 = this.animeRepository.save(AnimeCreator.createAnimeToSave(au1, "Boruto"));
		
		List<Anime> listAnime = this.animeRepository.findByName(animeSaved1.getName());
		
		log.info(listAnime);
		
		Assertions.assertThat(listAnime).isNotEmpty();
		
	}
	
	
	@Test
	@DisplayName("find by name returns empty list when no anime is found")
	void findByName_ReturnEmptyList_WhenAnimeIsNotFound() {
		
		List<Anime> listAnime = this.animeRepository.findByName("sdsdsde");
		
		Assertions.assertThat(listAnime).isEmpty();
		
	}
	
}
