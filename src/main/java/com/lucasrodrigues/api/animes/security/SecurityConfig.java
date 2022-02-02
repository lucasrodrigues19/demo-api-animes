package com.lucasrodrigues.api.animes.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.lucasrodrigues.api.animes.services.UserDetailsServiceImpl;
import com.lucasrodrigues.api.animes.utils.PasswordEncoderUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@EnableWebSecurity
@Log4j2
@EnableGlobalMethodSecurity(prePostEnabled = true) //enable postAuthorized
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	private final UserDetailsServiceImpl userDetailsServiceImpl;
	
	
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
		PasswordEncoder passwordEncoder = PasswordEncoderUtil.getInstance();
		log.info("passowrd encoder: "+passwordEncoder.encode("test"));
		
		//Posso usar os dois tipos de usuarios, em memoria e no banco de dados.
		
		//os usuários vao ficar no ciclo de vida na aplicação
		auth.inMemoryAuthentication()
		.withUser("_lucasMemory")
		.password(passwordEncoder.encode("test"))
		.roles("USER","ADMIN")
		.and()
		.withUser("_padraoMemory")
		.password(passwordEncoder.encode("test"))
		.roles("USER");
		
		//Usuarios em banco de dados
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder);
	}

	private String[] publicMatchers () {
		String[] matchers = {"/h2-console/**"};
		return matchers;
	}
	
}
