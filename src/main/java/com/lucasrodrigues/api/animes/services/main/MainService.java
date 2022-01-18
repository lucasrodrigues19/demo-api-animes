package com.lucasrodrigues.api.animes.services.main;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


import com.lucasrodrigues.api.animes.domains.main.MainEntity;
import com.lucasrodrigues.api.animes.repositorys.main.MainRepository;

public class MainService<E extends MainEntity> {

	private MainRepository<E> repository;
	
	public List<E> findAll() {
		return repository.findAll();
	}
	
	public E findById(UUID id) {
		return repository.findById(id).orElseThrow(null);
	}
	
	public E save(E entity) {
		
		E existingEntity = null;
		
		if(entity.getId() != null)
			existingEntity = findById(entity.getId());
		
		if(existingEntity == null)
			entity.setDtInsert(LocalDateTime.now());
		
		entity.setDtUpdate(LocalDateTime.now());
		
		return repository.save(entity);
	}
	
	public List<E> saveAll(List<E> entities) {
		return repository.saveAll(entities);
	}
	
	public void setRepository(MainRepository<E> repository) {
		this.repository = repository;
	}
}
