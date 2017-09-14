package com.fomo.rest.hateos.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class ResourceCreatedEvent extends ApplicationEvent {
 
	private final HttpServletResponse response;
	private final long idOfNewResource;
	/**
	 * @return the response
	 */
	public final HttpServletResponse getResponse() {
		return response;
	}
	/**
	 * @return the idOfNewResource
	 */
	public final long getIdOfNewResource() {
		return idOfNewResource;
	}
	public ResourceCreatedEvent(Object source, HttpServletResponse response, long idOfNewResource) {
		super(source);
		this.response = response;
		this.idOfNewResource = idOfNewResource;
	}
	
	
}
