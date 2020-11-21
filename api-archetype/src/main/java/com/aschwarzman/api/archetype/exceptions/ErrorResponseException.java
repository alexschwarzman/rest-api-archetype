package com.aschwarzman.api.archetype.exceptions;

import com.aschwarzman.api.infrastructure.errors.ErrorResponse;

import org.springframework.http.HttpStatus;

public class ErrorResponseException extends HttpStatusException {

	private static final long serialVersionUID = 1L;

	private final ErrorResponse response;

	public ErrorResponseException(HttpStatus status, ErrorResponse response) {
		super(status);
		this.response = response;
	}

	public ErrorResponse getResponse() {
		return response;
	}

}
