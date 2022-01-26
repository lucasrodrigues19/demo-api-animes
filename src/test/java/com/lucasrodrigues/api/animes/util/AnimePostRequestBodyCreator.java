package com.lucasrodrigues.api.animes.util;

import com.lucasrodrigues.api.animes.domains.Author;
import com.lucasrodrigues.api.animes.dto.requests.AnimePostRequestBody;

public class AnimePostRequestBodyCreator {

	public static AnimePostRequestBody create(Author author, String name) {
		return AnimePostRequestBody.builder().authorId(author.getId()).name(name).build();
	}
}
