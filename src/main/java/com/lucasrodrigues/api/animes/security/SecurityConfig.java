package com.lucasrodrigues.api.animes.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.log4j.Log4j2;

@EnableWebSecurity
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	/**
	 * Aqui vou configura o que estou protegendo com o protocolo http
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests() //quaquer url  precisa estar autenticada
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic(); //forma de autenticação http basic, que é Usuario e senha codificado
	}
	
	
	/**
	 * Configura a autenticação
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder passwordEncode = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		log.info("passowrd encoder: "+passwordEncode.encode("test"));
		
		//os usuários vao ficar no ciclo de vida na aplicação
		
		auth.inMemoryAuthentication()
		.withUser("lucas")
		.password(passwordEncode.encode("test"))
		.roles("USER","ADMIN")
		.and()
		.withUser("animes")
		.password(passwordEncode.encode("test"))
		.roles("USER");
	}


	
}
