package com.lucasrodrigues.api.animes.util;

import java.time.LocalDateTime;
import java.util.UUID;

import com.lucasrodrigues.api.animes.domains.Anime;
import com.lucasrodrigues.api.animes.domains.Author;

public class AnimeCreator {

	
	public static Author createAuthor(String name) {
		return Author.builder().name(name).dtInsert(LocalDateTime.now()).dtUpdate(LocalDateTime.now()).build();
	}
	public static Author createValidAuthor(UUID id, String name) {
		return Author.builder().id(id).name(name).dtInsert(LocalDateTime.now()).dtUpdate(LocalDateTime.now()).build();
	}
	
	
	public static Anime createAnimeToSave(Author author, String name) {
		return Anime.builder().name(name).authorId(author.getId()).dtInsert(LocalDateTime.now()).dtUpdate(LocalDateTime.now()).build();
	}
	
	public static Anime createValidAnime(Author author, String name) {
		return Anime.builder().id(UUID.fromString("d840cb41-8360-4819-bfd3-86c4cfbd8e59")).name(name).authorId(author.getId()).dtInsert(LocalDateTime.now()).dtUpdate(LocalDateTime.now()).build();
	}
}
