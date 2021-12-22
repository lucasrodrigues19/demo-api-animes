package com.lucasrodrigues.api.animes.services.main;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.lucasrodrigues.api.animes.domains.main.MainEntity;
import com.lucasrodrigues.api.animes.repositorys.main.MainRepository;

public class MainService<E extends MainEntity,R extends MainRepository<E>> {

	@Autowired
	private R repository;
	
	
	public List<E> findAll() {
		return repository.findAll();
	}
	
	public E findById(UUID id) {
		return repository.findById(id).orElseThrow(null);
	}
	
	public E save(E entity) {
		return repository.save(entity);
	}
	
	public List<E> saveAll(List<E> entities) {
		return repository.saveAll(entities);
	}
}
