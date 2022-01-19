package com.lucasrodrigues.api.animes.exception;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class ValidationExceptionDetails extends MainExceptionDetails {

	private final String fields;
	
	private final String fieldMessages;
}
