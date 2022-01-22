package com.lucasrodrigues.api.animes.repositorys;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lucasrodrigues.api.animes.domains.Anime;
import com.lucasrodrigues.api.animes.domains.Author;
import com.lucasrodrigues.api.animes.repositorys.main.MainRepository;

@Repository
public interface AnimeRepository extends MainRepository<Anime>{

	List<Anime> findByName(String name);
}
