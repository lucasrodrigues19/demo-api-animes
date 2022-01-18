package com.lucasrodrigues.api.animes.dto.requests;

import java.util.UUID;

import com.lucasrodrigues.api.animes.dto.requests.main.RequestsMain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AnimePostRequestBody extends RequestsMain {

	private String name;
	
	private UUID authorId;
}
