package com.fomo.rest.hateos.listener;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.util.UriComponentsBuilder;

import com.fomo.rest.hateos.event.PaginatedResultsRetrievedEvent;
import com.fomo.rest.hateos.web.util.LinkUtil;
import com.google.common.base.Preconditions;
import com.google.common.net.HttpHeaders;

public class PaginatedResultsRetrievedDiscoverabilityListener {
	private static final String PAGE = "page";

	public PaginatedResultsRetrievedDiscoverabilityListener() {
		// TODO Auto-generated constructor stub
	}

	private void onApplicationEvent(final PaginatedResultsRetrievedEvent ev) {
		Preconditions.checkNotNull(ev);
        addLinkHeaderOnPagedResourceRetrieval(ev.getUriComponentsBuilder(), ev.getResponse(), ev.getClazz(), ev.getPage(), ev.getTotalPages(), ev.getPageSize());

	}

	private final void addLinkHeaderOnPagedResourceRetrieval(final UriComponentsBuilder uriBuilder,
			final HttpServletResponse response, final Class clazz, final int page, final int totalPages,
			final int pageSize) {
		final StringBuilder linkHeader = new StringBuilder();

		if (hasNextPage(page, totalPages)) {
			final String uriToNextPage = constructNextPageUri(uriBuilder, totalPages, pageSize);
			linkHeader.append(uriToNextPage);
			linkHeader.append(LinkUtil.createLinkHeader(uriToNextPage, LinkUtil.REL_NEXT));
		}

		if (hasPreviousPage(page)) {
			final String uriForPrevPage = constructPrevPageUri(uriBuilder, page, pageSize);
			appendCommandIfNecessary(linkHeader);
			linkHeader.append(LinkUtil.createLinkHeader(uriForPrevPage, LinkUtil.REL_PREV));

		}
		if(hasFirstPage(page)){
			final String uriForFirstPage = constructFirstPageUri(uriBuilder, pageSize);
			appendCommandIfNecessary(linkHeader);
			linkHeader.append(LinkUtil.createLinkHeader(uriForFirstPage, LinkUtil.REL_FIRST));
		}
		if(hasLastPage(page, totalPages)){
			final String uriForLastPage = constructLastPageUri(uriBuilder, totalPages, pageSize);
			appendCommandIfNecessary(linkHeader);
			linkHeader.append(LinkUtil.createLinkHeader(uriForLastPage, LinkUtil.REL_LAST));
		}
		
		if( linkHeader.length() > 0 ){
			response.addHeader(HttpHeaders.LINK, linkHeader.toString());
		}

	}

	final void appendCommandIfNecessary(final StringBuilder linkHeader) {
		if (linkHeader.length() > 0) {
			linkHeader.append(",");
		}
	}

	final String constructNextPageUri(final UriComponentsBuilder uriBuilder, final int page, final int size) {
		return uriBuilder.replaceQueryParam(PAGE, page + 1).replaceQueryParam("size", size).build().encode()
				.toUriString();
	}

	final String constructPrevPageUri(final UriComponentsBuilder uriBuilder, final int page, final int size) {
		return uriBuilder.replaceQueryParam(PAGE, page - 1).replaceQueryParam("size", size).build().encode().toString();
	}

	final String constructFirstPageUri(final UriComponentsBuilder uriBuilder, final int size) {
		return uriBuilder.replaceQueryParam(PAGE, 0).replaceQueryParam("size", size).build().encode().toUriString();
	}

	final String constructLastPageUri(final UriComponentsBuilder uriBuilder, final int totalPages, final int size) {
		return uriBuilder.replaceQueryParam(PAGE, totalPages).replaceQueryParam("size", size).build().encode()
				.toString();
	}

	final boolean hasNextPage(final int page, final int totalPages) {
		return page < (totalPages - 1);
	}

	final boolean hasLastPage(final int page, final int totalPages) {
		return (totalPages > 1) && hasNextPage(page, totalPages);
	}

	final boolean hasPreviousPage(final int page) {
		return page > 0;
	}

	final boolean hasFirstPage(final int page) {
		return hasPreviousPage(page);
	}

	protected void plural(UriComponentsBuilder uriBuilder, Class classz) {
		final String resourceName = classz.getSimpleName().toString() + "s";
		uriBuilder.path("/web/" + resourceName);
	}

}
