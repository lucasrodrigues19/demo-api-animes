package com.lucasrodrigues.api.animes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasrodrigues.api.animes.domains.Author;
import com.lucasrodrigues.api.animes.repositorys.AuthorRepository;
import com.lucasrodrigues.api.animes.services.main.MainService;

@Service
public class AuthorService extends MainService<Author> {

	
	private AuthorRepository repository;
	
	@Autowired
	public AuthorService(AuthorRepository repository) {
		setRepository(repository);
		this.repository = repository;
	}

}
