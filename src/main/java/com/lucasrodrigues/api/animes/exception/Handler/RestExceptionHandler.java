package com.lucasrodrigues.api.animes.exception.Handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lucasrodrigues.api.animes.exception.BadRequestException;
import com.lucasrodrigues.api.animes.exception.BadRequestExceptionDetails;

@ControllerAdvice
public class RestExceptionHandler {

	
	@ExceptionHandler(BadRequestException.class)
	private ResponseEntity<BadRequestExceptionDetails> handleBadRequestException(BadRequestException ex){
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(BadRequestExceptionDetails
						.builder()
						.title("Bad Request Exception")
						.details(ex.getMessage())
						.developerMessage(ex.getClass().getName())
						.timestamp(LocalDateTime.now())
						.status(HttpStatus.BAD_REQUEST.value())
						.build()
				);
	}
}
