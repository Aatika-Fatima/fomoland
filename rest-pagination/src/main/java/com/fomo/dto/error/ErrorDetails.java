package com.fomo.dto.error;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ErrorDetails {
	private String title;
	private int status;
	private String detail;
	private long timestamp;
	private String developerMessage;

	private Map<String, List<ValidationError>> errors = new HashMap<String, List<ValidationError>>();

	/**
	 * @return the errors
	 */
	public final Map<String, List<ValidationError>> getErrors() {
		return errors;
	}

	/**
	 * @param errors
	 *            the errors to set
	 */
	public final void setErrors(Map<String, List<ValidationError>> errors) {
		this.errors = errors;
	}

	/**
	 * 
	 */
	public ErrorDetails() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the title
	 */
	public final String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public final void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the status
	 */
	public final int getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public final void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the detail
	 */
	public final String getDetail() {
		return detail;
	}

	/**
	 * @param detail
	 *            the detail to set
	 */
	public final void setDetail(String detail) {
		this.detail = detail;
	}

	/**
	 * @return the timestamp
	 */
	public final long getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp
	 *            the timestamp to set
	 */
	public final void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the developerMessage
	 */
	public final String getDeveloperMessage() {
		return developerMessage;
	}

	/**
	 * @param developerMessage
	 *            the developerMessage to set
	 */
	public final void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

}
