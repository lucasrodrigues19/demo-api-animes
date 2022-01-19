package com.lucasrodrigues.api.animes.exception;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class MainExceptionDetails {

	protected String title;
	
	protected int status;
	
	protected String details;
	
	protected String developerMessage;
	
	protected LocalDateTime timestamp;
}
