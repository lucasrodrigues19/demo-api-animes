package com.lucasrodrigues.api.animes.repositorys;

import org.springframework.stereotype.Repository;

import com.lucasrodrigues.api.animes.domains.User;
import com.lucasrodrigues.api.animes.repositorys.main.MainRepository;

@Repository
public interface UserRepository extends MainRepository<User>{

	User findByUsername(String username);
}
