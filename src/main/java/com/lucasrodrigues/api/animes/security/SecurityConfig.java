package com.lucasrodrigues.api.animes.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import lombok.extern.log4j.Log4j2;

@EnableWebSecurity
@Log4j2
@EnableGlobalMethodSecurity(prePostEnabled = true) //enable postAuthorized
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	/**
	 * Aqui vou configura o que estou protegendo com o protocolo http
	 * Filtros:
	 * 	BasicAuthenticationFilter
	 *  UsernamePasswordAuthenticationFilter -> Verifica se na minha requisição tem usuario e senha
	 *  DefaultLoginPageGeneratingFilter -> gera a pagina de login
	 *  DefaultLogoutPageGeneratingFilter -> gera a pagina de login
	 *  FilterSecurityInterceptor -> checa se está autorizado
	 *  
	 *  1 -> Authentication
	 *  2 -> Authorization
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		 * CSRF -> Evita que Alguem execute alguma ação em seu nome
		 * withHttpOnlyTrue -> aplicações no front não poderão acessar o cookie
		 */
		
		
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
		.authorizeRequests() //quaquer url  precisa estar autenticada
		.antMatchers(publicMatchers()).permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.formLogin()
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

	private String[] publicMatchers () {
		String[] matchers = {"/h2-console/**"};
		return matchers;
	}
	
}
