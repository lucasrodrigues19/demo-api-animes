package com.lucasrodrigues.api.animes.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Sobrescrevendo Configurações globais do spring
 * @author Lucas Rodrigues
 * @since 2022/01/19
 *
 */
@Configuration
public class AnimeWebMvcConfigurer implements WebMvcConfigurer {

	/**
	 * Mudando configuração global do spring para paginação
	 */
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		PageableHandlerMethodArgumentResolver pageHandler = new PageableHandlerMethodArgumentResolver();
		
		//Pagina a ser exibida é a primeira(0) com o tamanho de 3 elementos
		pageHandler.setFallbackPageable(PageRequest.of(0, 3));
		resolvers.add(pageHandler);
	}

	
}
