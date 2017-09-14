package com.fomo.persistence.model;

public enum ShareType {
	QUESTION("QUESTION"), POLL("POLL"),CONTENT_FEED("CONTENT_FEED");
	private String description;

	private ShareType(String description) {
		this.description = description;
	}

	public final String getDescription() {
		return description;
	}

	public final void setDescription(String description) {
		this.description = description;
	}
	
}
