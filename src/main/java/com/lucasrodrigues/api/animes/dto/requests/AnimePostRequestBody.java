package com.lucasrodrigues.api.animes.dto.requests;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.lucasrodrigues.api.animes.dto.requests.main.RequestsMain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AnimePostRequestBody extends RequestsMain {

	@NotBlank
	private String name;
	
	@NotNull
	private UUID authorId;
}
