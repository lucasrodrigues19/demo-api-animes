package com.lucasrodrigues.api.animes;


import java.time.LocalDateTime;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.lucasrodrigues.api.animes.domains.Anime;
import com.lucasrodrigues.api.animes.domains.Author;
import com.lucasrodrigues.api.animes.repositorys.AnimeRepository;
import com.lucasrodrigues.api.animes.repositorys.AuthorRepository;

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
		Author au1 = this.authorRepository.save(createAuthor("Kishmoto"));
		Anime animeSaved1 = this.animeRepository.save(createAnime(au1, "Boruto"));
		
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
	
	
	private Anime createAnime(Author author, String name) {
		return Anime.builder().name(name).authorId(author.getId()).dtInsert(LocalDateTime.now()).dtUpdate(LocalDateTime.now()).build();
	}
	
	private Author createAuthor(String name) {
		return Author.builder().name(name).dtInsert(LocalDateTime.now()).dtUpdate(LocalDateTime.now()).build();
	}
}
