package com.aschwarzman.api.archetype.exceptions;

import org.springframework.http.HttpStatus;

public class HttpStatusException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final HttpStatus status;

	public HttpStatusException(HttpStatus status) {
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}
}
