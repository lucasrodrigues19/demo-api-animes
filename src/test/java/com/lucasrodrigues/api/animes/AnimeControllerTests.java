package com.lucasrodrigues.api.animes;

import java.util.Arrays;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.lucasrodrigues.api.animes.controllers.AnimeController;
import com.lucasrodrigues.api.animes.domains.Anime;
import com.lucasrodrigues.api.animes.domains.Author;
import com.lucasrodrigues.api.animes.services.AnimeService;
import com.lucasrodrigues.api.animes.util.AnimeCreator;


//@SpringBootTest //tenta executar o contexto, vai inicializar a apliação.
@ExtendWith(SpringExtension.class) //ultiliza spring com junit, o contexto não é totalmente inicializado
public class AnimeControllerTests {

	@InjectMocks //Ultiliza quando quero testar a classe em si.
	private AnimeController animeController;
	
	@Mock //ultilizo todas as classes que estão sendo ultilizado na classe, vou criar um comportamento mocado da classe animeService.
	private AnimeService animeService;
	
	
	@BeforeEach //Será executado antes dos testes.
	void setup() {
		//Vou definir um comportamento mocado toda vez que animeService.findAll for chamado
		
		Author author = AnimeCreator.createValidAuthor(UUID.fromString("e6da1e37-8d25-4d87-9b7c-19c24a0bc81f"), "Kishmoto");
		Anime anime = AnimeCreator.createValidAnime(author,"Naruto");
		
		PageImpl<Anime> animePage = new PageImpl<Anime>(Arrays.asList(anime));
		
		//Toda vez que chamar o metodo findAll de animeService, ele retornara animePage, independente de qualquer argumento
		BDDMockito.when(animeService.findAll(ArgumentMatchers.any())).thenReturn(animePage);
	}
	
	
	@Test
	@DisplayName("findAll Return list of anime inside page object when success")
	void findAll_ReturnListOfAnimeInsidePageObject_WhenSuccessFull() {
		
		Page <Anime> animePage = animeController.findAllWithPageable(null).getBody();
		
		Assertions.assertThat(animePage).isNotNull();
		
		Assertions.assertThat(animePage.toList()).isNotEmpty();
	}
}
