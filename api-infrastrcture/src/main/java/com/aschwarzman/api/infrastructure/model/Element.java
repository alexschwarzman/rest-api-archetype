package com.aschwarzman.api.infrastructure.model;

import java.io.Serializable;

public abstract class Element<I extends Serializable> {

	private I id;

	public I getId() {
		return id;
	}

	public void setId(I id) {
		this.id = id;
	}
	
}
