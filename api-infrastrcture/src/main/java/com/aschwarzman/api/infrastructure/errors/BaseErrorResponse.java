package com.aschwarzman.api.infrastructure.errors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.validation.constraints.NotNull;

/**
 * 
 * @author aschwarzman
 * @param <E>
 *            {@link ErrorEntry} type
 * @since Jul 29, 2020
 */
public abstract class BaseErrorResponse<E extends ErrorEntry> implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	private List<E> errors = new ArrayList();

	public BaseErrorResponse() {}

	public BaseErrorResponse(@NotNull List<E> errors) {
		setErrors(errors);
	}

	public List<E> getErrors() {
		return errors;
	}

	public void setErrors(@NotNull List<E> errors) {
		this.errors = new ArrayList(errors != null ? errors : Collections.emptyList());
	}
}
