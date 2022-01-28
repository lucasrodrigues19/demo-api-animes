package com.lucasrodrigues.api.animes.integration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lucasrodrigues.api.animes.domains.Anime;
import com.lucasrodrigues.api.animes.domains.Author;
import com.lucasrodrigues.api.animes.dto.requests.AnimePostRequestBody;
import com.lucasrodrigues.api.animes.repositorys.AnimeRepository;
import com.lucasrodrigues.api.animes.util.AnimeCreator;
import com.lucasrodrigues.api.animes.util.AnimePostRequestBodyCreator;
import com.lucasrodrigues.api.animes.wrapper.PageableResponse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //quando é intrgração preciso que inicie toda a aplicação, executando em uma porta aleatoria
@AutoConfigureTestDatabase //Configuração de banco de dados ultilizara o valor que tem em memoria
public class AnimeControllerIT {

	@Autowired
	private TestRestTemplate testRestTemplate; //Aqui pego a porta que vai ser ultilizada no teste
	
	@LocalServerPort //posso pegar a porta por aqui também
	private int port;
	
	
	@MockBean
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

	}

	
	
	@Test
	@DisplayName("findAll Return list of anime inside page object when success")
	void findAll_ReturnListOfAnimeInsidePageObject_WhenSuccessFull() {
		
		
		PageableResponse<Anime> animePage = testRestTemplate.exchange("/anime/findAll/pegeable", HttpMethod.GET, null, 
		new ParameterizedTypeReference<PageableResponse<Anime>>() {}).getBody();
				
		
		Assertions.assertThat(animePage).isNotNull();
		
		Assertions.assertThat(animePage.toList()).isNotEmpty();
	}
	
	
	@Test
	@DisplayName("findAll Return list of anime when success")
	void findAll_ReturnListOfAnime_WhenSuccessFull() {
		
		List<Anime> listAnime = testRestTemplate.exchange("/anime/findAll", HttpMethod.GET, null, 
		new ParameterizedTypeReference<List<Anime>>() {}).getBody();
				
		
		Assertions.assertThat(listAnime).isNotNull();
		
		Assertions.assertThat(listAnime).isNotEmpty();
	}
	
	@Test
	@DisplayName("findById Return anime when success")
	void findById_ReturnAnime_WhenSuccessFull() {
		
		Author author = AnimeCreator.createValidAuthor(UUID.fromString("e6da1e37-8d25-4d87-9b7c-19c24a0bc81f"), "Kishmoto");

		Anime animeSaved = animeRepository.save(AnimeCreator.createAnimeToSave(author, "Boruto"));

		UUID expectedId = animeSaved.getId();
		
		Anime anime = testRestTemplate.exchange("/anime/"+expectedId, HttpMethod.GET, null, 
				new ParameterizedTypeReference<Anime>() {}).getBody();		
		
		Assertions.assertThat(anime).isNotNull();
		
		Assertions.assertThat(anime.getId()).isNotNull();
		
		Assertions.assertThat(anime.getId()).isEqualTo(expectedId);		
	}
	
	@Test
	@DisplayName("findByName Return list anime when success")
	void findByName_ReturnListAnime_WhenSuccessFull() {
		
		Author author = AnimeCreator.createValidAuthor(UUID.fromString("e6da1e37-8d25-4d87-9b7c-19c24a0bc81f"), "Kishmoto");

		Anime animeSaved = animeRepository.save(AnimeCreator.createAnimeToSave(author, "Boruto"));

		String expectedName = animeSaved.getName();
		
		List<Anime> anime = testRestTemplate.exchange("/anime/name/"+expectedName, HttpMethod.GET, null, 
				new ParameterizedTypeReference<List<Anime>>() {}).getBody();		
		
		Assertions.assertThat(anime).isNotNull();
		
		Assertions.assertThat(anime).isNotEmpty();
	}
	
	@Test
	@DisplayName("findByName Return list empty anime when is not found")
	void findByName_ReturnListEmptyOfAnime_WhenIsNotFound() {
		
		BDDMockito.when(animeRepository.findByName(ArgumentMatchers.any(String.class))).thenReturn(Collections.emptyList());

		List<Anime> anime = testRestTemplate.exchange("/anime/name/naruto", HttpMethod.GET, null, 
				new ParameterizedTypeReference<List<Anime>>() {}).getBody();		
		
		Assertions.assertThat(anime).isNotNull().isEmpty();;
		
	}
	
	@Test
	@DisplayName("save Return anime when Success")
	void save_ReturnAnime_WhenSuccess() {
		
		Author author = AnimeCreator.createValidAuthor(UUID.fromString("e6da1e37-8d25-4d87-9b7c-19c24a0bc81f"), "Kishmoto");

		AnimePostRequestBody animePost = AnimePostRequestBodyCreator.create(author, "Dbz");
		
		ResponseEntity<Anime> response = testRestTemplate.postForEntity("/anime/save", animePost , Anime.class);
		
		Assertions.assertThat(response).isNotNull();
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		Assertions.assertThat(response.getBody()).isNotNull();
		
	}
}
