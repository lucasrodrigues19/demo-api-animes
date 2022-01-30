package com.lucasrodrigues.api.animes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucasrodrigues.api.animes.services.AnimeService;


@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private AnimeService animeService;
	
	@PostMapping("/auth/{id}")
	public ResponseEntity<String> doAutentication(@PathVariable String id, @AuthenticationPrincipal UserDetails userDetails) {
		StringBuilder result = new StringBuilder();
		
		result.append("{\"usernameUserDetails\":\""+userDetails.getUsername()+"\",");
		result.append("{\"passwordUserDetails\":\""+userDetails.getUsername()+"\"}");
		result.append("{\"userDetails\":\""+userDetails.toString()+"\",");
		return ResponseEntity.ok(result.toString());
	}
}
