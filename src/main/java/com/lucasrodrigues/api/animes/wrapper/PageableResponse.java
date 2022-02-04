package com.lucasrodrigues.api.animes.wrapper;

import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.Getter;
import lombok.Setter;

/**
 * Aqui vou desserealizar um objeto imutavel, ou seja, objetos que mantem seu estado
 * até o final, uma vez instanciado.
 * Vou fazer um wrapper daquilo que estamos retornando, no PAGE
 * @author Lucas Rodrigues
 * @since 2022/01/27
 */
@Getter
@Setter
public class PageableResponse<T> extends PageImpl<T> {
	private static final long serialVersionUID = 1L;
	
	private boolean first;
    private boolean last;
    private int totalPages;
    private int numberOfElements;

    // @JsonCreator diz ao Jackson desserializer para usar o construtor designado para desserialização.
    // PROPERTIES é o mais adequado quando declaramos um construtor de todos os argumentos
    @JsonCreator(mode = Mode.PROPERTIES )
    public PageableResponse(@JsonProperty("content") List<T> content,
        @JsonProperty("number") int number,
        @JsonProperty("size") int size,
        @JsonProperty("totalElements") int totalElements,
        @JsonProperty("last") boolean last,
        @JsonProperty("first") boolean first,
        @JsonProperty("totalPages") int totalPages,
        @JsonProperty("numberOfElements") int numberOfElements,
        @JsonProperty("pageable") JsonNode pageable,
        @JsonProperty("sort") JsonNode sort) {
        super(content, PageRequest.of(number, size), totalElements);

        this.last = last;
        this.first = first;
        this.totalPages = totalPages;
        this.numberOfElements = numberOfElements;

    }
}
