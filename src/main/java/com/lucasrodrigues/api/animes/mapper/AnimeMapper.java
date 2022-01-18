package com.lucasrodrigues.api.animes.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.lucasrodrigues.api.animes.domains.Anime;
import com.lucasrodrigues.api.animes.dto.requests.AnimePostRequestBody;

@Mapper
public interface AnimeMapper {

	AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);
	
	public Anime toAnime(AnimePostRequestBody animePostRequestBody);
	
}
