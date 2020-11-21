package com.aschwarzman.api.archetype.exceptions;

import java.io.Serializable;

public class NotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	private final Serializable id;

	public NotFoundException(Serializable id) {
		this.id = id;
	}

	public <K extends Serializable> K getId() {
		return (K)id;
	}

}
