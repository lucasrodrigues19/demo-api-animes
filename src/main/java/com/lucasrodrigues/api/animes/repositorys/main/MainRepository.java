package com.lucasrodrigues.api.animes.repositorys.main;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MainRepository<T> extends JpaRepository<T, UUID>{

	
}
