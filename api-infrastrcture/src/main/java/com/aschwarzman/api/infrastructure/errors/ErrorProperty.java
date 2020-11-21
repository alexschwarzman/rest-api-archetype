package com.aschwarzman.api.infrastructure.errors;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * 
 * @author aschwarzman
 * @since Jul 29, 2020
 */
public class ErrorProperty implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	private String property;
	private Object value;

	public ErrorProperty() {}

	public ErrorProperty(@NotNull String property, Object value) {
		this.property = property;
		this.value = value;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(@NotNull String property) {
		this.property = property;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
