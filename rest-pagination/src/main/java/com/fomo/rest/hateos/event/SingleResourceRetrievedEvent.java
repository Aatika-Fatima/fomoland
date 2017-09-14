package com.fomo.rest.hateos.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class SingleResourceRetrievedEvent extends ApplicationEvent{
 
	private static final long serialVersionUID = 5227080614102446928L;
	private final HttpServletResponse response;

	public SingleResourceRetrievedEvent(Object source, HttpServletResponse response) {
		super(source);
		this.response = response;
	}

	/**
	 * @return the response
	 */
	public final HttpServletResponse getResponse() {
		return response;
	}

 

}
