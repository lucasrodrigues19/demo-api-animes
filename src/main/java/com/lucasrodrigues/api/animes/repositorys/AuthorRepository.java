package com.lucasrodrigues.api.animes.repositorys;

import org.springframework.stereotype.Repository;

import com.lucasrodrigues.api.animes.domains.Author;
import com.lucasrodrigues.api.animes.repositorys.main.MainRepository;

@Repository
public interface AuthorRepository extends MainRepository<Author>{

}
