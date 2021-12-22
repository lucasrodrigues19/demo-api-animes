package com.lucasrodrigues.api.animes.repositorys.main;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MainRepository<T> extends JpaRepository<T, UUID>{

	
}
