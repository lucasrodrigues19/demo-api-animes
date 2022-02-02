package com.lucasrodrigues.api.animes.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lucasrodrigues.api.animes.domains.User;
import com.lucasrodrigues.api.animes.repositorys.UserRepository;
import com.lucasrodrigues.api.animes.services.main.MainService;
import com.lucasrodrigues.api.animes.utils.PasswordEncoderUtil;

@Service
public class UserDetailsServiceImpl extends MainService<User> implements UserDetailsService{
	
	private UserRepository repository;
	
	@Autowired
	public UserDetailsServiceImpl(UserRepository repository) {
		setRepository(repository);
		this.repository = repository;
	}

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return Optional.ofNullable(repository.findByUsername(username))
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}


	@Override
	public User save(User entity) {
		if(entity != null)
			entity.setPassowrd(PasswordEncoderUtil.getInstance().encode(entity.getPassowrd()));
		return super.save(entity);
	}
	
	
	
	
	

	

	
}
