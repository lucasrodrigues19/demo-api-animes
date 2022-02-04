package com.lucasrodrigues.api.animes.controllers;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucasrodrigues.api.animes.domains.Anime;
import com.lucasrodrigues.api.animes.dto.requests.AnimePostRequestBody;
import com.lucasrodrigues.api.animes.services.AnimeService;

import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/anime")
public class AnimeController {

	@Autowired
	private AnimeService animeService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Anime> findById(@PathVariable("id") UUID id) {
		return ResponseEntity.ok(animeService.findByIdOrThrowBadRequestException(id));
	}
	
	@GetMapping(value = "/name/{name}")
	public ResponseEntity<List<Anime>> findByName(@PathVariable("name") String name) {
		return ResponseEntity.ok(animeService.findByName(name));
	}
	
	/**
	 * Parametros
	 * size = 5 -> retorna apenas 5 valores
	 * page = 2 -> retorna a pagina 2
	 * @param pageable
	 * @return
	 */
	@GetMapping("/findAll/pegeable")
	public ResponseEntity<Page<Anime>> findAllWithPageable(@ParameterObject Pageable pageable) {
		return ResponseEntity.ok(animeService.findAll(pageable));
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Anime>> findAll() {
		return ResponseEntity.ok(animeService.findAll());
	}
	
	@PostMapping(value = "/admin/save")
	public ResponseEntity<Anime> save(@RequestBody @Valid AnimePostRequestBody animePostRequestBody) {
		return ResponseEntity.status(HttpStatus.CREATED).body(animeService.save(animePostRequestBody));
	}
}
