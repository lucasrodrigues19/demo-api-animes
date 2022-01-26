package com.lucasrodrigues.api.animes.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.lucasrodrigues.api.animes.controllers.AnimeController;
import com.lucasrodrigues.api.animes.domains.Anime;
import com.lucasrodrigues.api.animes.domains.Author;
import com.lucasrodrigues.api.animes.dto.requests.AnimePostRequestBody;
import com.lucasrodrigues.api.animes.exception.BadRequestException;
import com.lucasrodrigues.api.animes.repositorys.AnimeRepository;
import com.lucasrodrigues.api.animes.services.AnimeService;
import com.lucasrodrigues.api.animes.util.AnimeCreator;
import com.lucasrodrigues.api.animes.util.AnimePostRequestBodyCreator;


@ExtendWith(SpringExtension.class) 
public class AnimeServiceTests {
	
	@InjectMocks 
	private AnimeService animeService;
	
	@Mock 
	private AnimeRepository animeRepository;
	
	
	
	@BeforeEach 
	void setup() {
		
		Author author = AnimeCreator.createValidAuthor(UUID.fromString("e6da1e37-8d25-4d87-9b7c-19c24a0bc81f"), "Kishmoto");
		Anime anime = AnimeCreator.createValidAnime(author,"Naruto");
		
		PageImpl<Anime> animePage = new PageImpl<Anime>(Arrays.asList(anime));
		
		BDDMockito.when(animeRepository.findAll(ArgumentMatchers.any(PageRequest.class))).thenReturn(animePage);
		
		
		BDDMockito.when(animeRepository.findAll()).thenReturn(Arrays.asList(anime));
		
		BDDMockito.when(animeRepository.findById(ArgumentMatchers.any(UUID.class))).thenReturn(Optional.of(anime));
		
		BDDMockito.when(animeRepository.findByName(ArgumentMatchers.any(String.class))).thenReturn(Arrays.asList(anime));
		
		BDDMockito.when(animeRepository.save(ArgumentMatchers.any(Anime.class))).thenReturn(anime);

		//Sintaxe para metodos que retornam voids
		BDDMockito.doNothing().when(animeRepository).delete(ArgumentMatchers.any(Anime.class));

	}
	
	
	@Test
	@DisplayName("findAll Return list of anime inside page object when success")
	void findAll_ReturnListOfAnimeInsidePageObject_WhenSuccessFull() {
		
		Page <Anime> animePage = animeService.findAll(Pageable.ofSize(1));
		
		Assertions.assertThat(animePage).isNotNull();
		
		Assertions.assertThat(animePage.toList()).isNotEmpty();
	}
	
	
	@Test
	@DisplayName("findAll Return list of anime when success")
	void findAll_ReturnListOfAnime_WhenSuccessFull() {
		
		List <Anime> listAnime = animeService.findAll();
		
		Assertions.assertThat(listAnime).isNotNull().isNotEmpty();
	}
	
	
	@Test
	@DisplayName("findById Return anime when success")
	void findById_ReturnAnime_WhenSuccessFull() {
		
		Anime anime = animeService.findById(UUID.fromString("bcb94f10-656c-48d0-90a9-d538712cc02f"));
		
		Assertions.assertThat(anime).isNotNull();
		
		Assertions.assertThat(anime.getId()).isNotNull();
	}
	
	@Test
	@DisplayName("findByIdOrThrowBadRequestException Return anime when success")
	void findByIdOrThrowBadRequestException_ReturnAnime_WhenSuccessFull() {
		
		Anime anime = animeService.findByIdOrThrowBadRequestException(UUID.fromString("bcb94f10-656c-48d0-90a9-d538712cc02f"));
		
		Assertions.assertThat(anime).isNotNull();
		
		Assertions.assertThat(anime.getId()).isNotNull();
	}
	
	@Test
	@DisplayName("findByIdOrThrowBadRequestException throws BadRequestException when amime is not found")
	void findByIdOrThrowBadRequestException_ThrowsBadRequestException_WhenAnimeNotFound() {
		
		BDDMockito.when(animeRepository.findById(ArgumentMatchers.any(UUID.class)))
			.thenReturn(Optional.empty());

		Assertions.assertThatExceptionOfType(BadRequestException.class)
			.isThrownBy(() -> this.animeService.findByIdOrThrowBadRequestException(UUID.fromString("bcb94f10-656c-48d0-90a9-d538712cc02f")));
	}
	
	@Test
	@DisplayName("findByName Return list anime when success")
	void findByName_ReturnListAnime_WhenSuccessFull() {
		
		
		List<Anime> listAnime = animeService.findByName("");
		
		Assertions.assertThat(listAnime).isNotNull().isNotEmpty();
		
	}
	
	@Test
	@DisplayName("findByName Return list empty anime when is not found")
	void findByName_ReturnListEmptyOfAnime_WhenIsNotFound() {
		
		BDDMockito.when(animeRepository.findByName(ArgumentMatchers.any(String.class))).thenReturn(Collections.emptyList());
		
		List<Anime> listAnime = animeService.findByName("");
		
		Assertions.assertThat(listAnime).isNotNull().isEmpty();
		
		
	}
	
	@Test
	@DisplayName("save Return anime when Success")
	void save_ReturnAnime_WhenSuccess() {
		Author author = AnimeCreator.createValidAuthor(UUID.fromString("e6da1e37-8d25-4d87-9b7c-19c24a0bc81f"), "Kishmoto");

		Anime anime = animeService.save(AnimePostRequestBodyCreator.create(author, "Boruto"));
		
		Assertions.assertThat(anime).isNotNull();
		
		Assertions.assertThat(anime.getId()).isNotNull();
		
		
	}
	
	@Test
	@DisplayName("delete not remove anime when Success")
	void delete_RemoveAnime_WhenSuccess() {

		Author author = AnimeCreator.createValidAuthor(UUID.fromString("e6da1e37-8d25-4d87-9b7c-19c24a0bc81f"), "Kishmoto");
		Anime anime = AnimeCreator.createValidAnime(author,"Naruto");

		//quando deletar não tera nemhuma excessão, caso for success
		Assertions.assertThatCode(() -> animeService.delete(anime)).doesNotThrowAnyException();
	}
}
