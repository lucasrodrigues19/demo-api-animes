package com.lucasrodrigues.api.animes.integration;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import com.lucasrodrigues.api.animes.domains.Anime;
import com.lucasrodrigues.api.animes.wrapper.PageableResponse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //quando é intrgração preciso que inicie toda a aplicação, executando em uma porta aleatoria
@AutoConfigureTestDatabase //Configuração de banco de dados ultilizara o valor que tem em memoria
public class AnimeControllerIT {

	@Autowired
	private TestRestTemplate testRestTemplate; //Aqui pego a porta que vai ser ultilizada no teste
	
	@LocalServerPort //posso pegar a porta por aqui também
	private int port;
	
	
	@Test
	@DisplayName("findAll Return list of anime inside page object when success")
	void findAll_ReturnListOfAnimeInsidePageObject_WhenSuccessFull() {
		
		PageableResponse<Anime> animePage = testRestTemplate.exchange("/anime/findAll/pegeable", HttpMethod.GET, null, 
		new ParameterizedTypeReference<PageableResponse<Anime>>() {}).getBody();
				
		
		Assertions.assertThat(animePage).isNotNull();
		
		Assertions.assertThat(animePage.toList()).isNotEmpty();
	}
}
