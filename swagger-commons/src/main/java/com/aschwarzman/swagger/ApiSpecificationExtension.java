package com.aschwarzman.swagger;

import javax.validation.constraints.NotNull;

/**
 * 
 * @author aschwarzman
 * @since Jul 29, 2020
 */
public class ApiSpecificationExtension {

	@NotNull
	private String name;

	@NotNull
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
