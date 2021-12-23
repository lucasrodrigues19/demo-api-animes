package com.lucasrodrigues.api.animes.repositorys;

import org.springframework.stereotype.Repository;

import com.lucasrodrigues.api.animes.domains.Anime;
import com.lucasrodrigues.api.animes.repositorys.main.MainRepository;

@Repository
public interface AnimeRepository extends MainRepository<Anime>{

}
