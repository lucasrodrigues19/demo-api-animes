package com.lucasrodrigues.api.animes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasrodrigues.api.animes.domains.Anime;
import com.lucasrodrigues.api.animes.repositorys.AnimeRepository;
import com.lucasrodrigues.api.animes.services.main.MainService;

@Service
public class AnimeService extends MainService<Anime>{
	
	private AnimeRepository repository;
	
	@Autowired
	public AnimeService(AnimeRepository repository) {
		setRepository(repository);
		this.repository = repository;
	}
	

	

	
}
