package com.aschwarzman.api.infrastructure.errors;

import java.io.Serializable;

/**
 * 
 * @author aschwarzman
 * @since Jul 29, 2020
 */
public class ErrorEntry implements Serializable {

	private static final long serialVersionUID = 1L;

	private String code;
	private String message;

	public ErrorEntry() {}

	public ErrorEntry(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
