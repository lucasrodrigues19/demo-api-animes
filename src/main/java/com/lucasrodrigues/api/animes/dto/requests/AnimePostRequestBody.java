package com.lucasrodrigues.api.animes.dto.requests;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.lucasrodrigues.api.animes.dto.requests.main.RequestsMain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnimePostRequestBody extends RequestsMain {

	@NotBlank
	private String name;
	
	@NotNull
	private UUID authorId;
}
