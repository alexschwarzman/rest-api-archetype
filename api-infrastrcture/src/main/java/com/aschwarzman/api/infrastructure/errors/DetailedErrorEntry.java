package com.aschwarzman.api.infrastructure.errors;

import java.util.List;

import javax.validation.constraints.NotNull;

/**
 * 
 * @author aschwarzman
 * @since Jul 29, 2020
 */
public class DetailedErrorEntry extends ErrorEntry {

	private static final long serialVersionUID = 1L;

	private String code;
	private String message;

	@NotNull
	private List<ErrorProperty> properties;

	public DetailedErrorEntry() {}

	public DetailedErrorEntry(String code, String message) {
		this.code = code;
		this.message = message;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public void setMessage(String message) {
		this.message = message;
	}

	public List<ErrorProperty> getProperties() {
		return properties;
	}

	public void setProperties(List<ErrorProperty> properties) {
		this.properties = properties;
	}

}
