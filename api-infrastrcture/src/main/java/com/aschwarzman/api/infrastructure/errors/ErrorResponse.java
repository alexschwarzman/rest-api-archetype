package com.aschwarzman.api.infrastructure.errors;

import java.util.List;

import javax.validation.constraints.NotNull;

/**
 * 
 * @author aschwarzman
 * @since Jul 29, 2020
 */
public class ErrorResponse extends BaseErrorResponse<ErrorEntry> {

	private static final long serialVersionUID = 1L;

	public ErrorResponse() {
		super();
	}

	public ErrorResponse(@NotNull List<ErrorEntry> errors) {
		super(errors);
	}

}
