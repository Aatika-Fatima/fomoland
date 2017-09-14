package com.fomo.dto.error;

public class ValidationError {
	private String code; 
	private String message;
	/**
	 * 
	 */
	public ValidationError() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the code
	 */
	public final String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public final void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the message
	 */
	public final String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public final void setMessage(String message) {
		this.message = message;
	}
	
}
