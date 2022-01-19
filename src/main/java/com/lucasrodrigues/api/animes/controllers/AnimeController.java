package com.lucasrodrigues.api.animes.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucasrodrigues.api.animes.domains.Anime;
import com.lucasrodrigues.api.animes.services.AnimeService;

@RestController
@RequestMapping("/anime")
public class AnimeController {

	@Autowired
	private AnimeService animeService;
	
	@GetMapping(value = "/find/{id}")
	public ResponseEntity<Anime> findById(@PathVariable("id") UUID id) {
		return ResponseEntity.ok(animeService.findByIdOrThrowBadRequestException(id));
	}
}
