package com.lucasrodrigues.api.animes.dto.requests;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.lucasrodrigues.api.animes.dto.requests.main.RequestsMain;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "This is the Anime's name", example = "Death Note", required = true)
	private String name;
	
	@NotNull
	@Schema(description = "This is the Author id", example = "178e2806-8108-497c-aa01-5185117830c7", required = true)
	private UUID authorId;
}
