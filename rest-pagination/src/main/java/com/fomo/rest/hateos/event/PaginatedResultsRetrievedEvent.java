package com.fomo.rest.hateos.event;

import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;
import org.springframework.web.util.UriComponentsBuilder;

public class PaginatedResultsRetrievedEvent<T extends Serializable> extends ApplicationEvent {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5627229250228477540L;
	private final UriComponentsBuilder uriComponentsBuilder;
	private final HttpServletResponse response;
	private int page;
	private int totalPages;
	private int pageSize;

	public PaginatedResultsRetrievedEvent(Object source, UriComponentsBuilder uriComponentsBuilder,
			HttpServletResponse response, int page, int totalPages, int pageSize) {
		super(source);
		this.uriComponentsBuilder = uriComponentsBuilder;
		this.response = response;
		this.page = page;
		this.totalPages = totalPages;
		this.pageSize = pageSize;
	}

	/**
	 * @return the uriComponentsBuilder
	 */
	public final UriComponentsBuilder getUriComponentsBuilder() {
		return uriComponentsBuilder;
	}

	/**
	 * @return the response
	 */
	public final HttpServletResponse getResponse() {
		return response;
	}

	/**
	 * @return the page
	 */
	public final int getPage() {
		return page;
	}

	/**
	 * @return the totalPages
	 */
	public final int getTotalPages() {
		return totalPages;
	}

	/**
	 * @return the pageSize
	 */
	public final int getPageSize() {
		return pageSize;
	}

	/**
	 * The object on which the Event initially occurred.
	 * 
	 * @return The object on which the Event initially occurred.
	 */
	@SuppressWarnings("unchecked")
	public final Class<T> getClazz() {
		return (Class<T>) getSource();
	}
}
