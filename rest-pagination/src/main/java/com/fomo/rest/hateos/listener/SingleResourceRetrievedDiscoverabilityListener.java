package com.fomo.rest.hateos.listener;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fomo.rest.hateos.event.SingleResourceRetrievedEvent;
import com.fomo.rest.hateos.web.util.LinkUtil;
import com.google.common.base.Preconditions;
import com.google.common.net.HttpHeaders;

public class SingleResourceRetrievedDiscoverabilityListener
		implements ApplicationListener<SingleResourceRetrievedEvent> {

	@Override
	public void onApplicationEvent(SingleResourceRetrievedEvent resourceRetrievedEvent) {
		Preconditions.checkNotNull(resourceRetrievedEvent);
		final HttpServletResponse response = resourceRetrievedEvent.getResponse();

	}

	void addLinkHeaderOnSingleResourceRetrieval(final HttpServletResponse response) {
		final String url = ServletUriComponentsBuilder.fromCurrentRequestUri().build()
				.toUri().toASCIIString();
		final int positionOfLastSlash = url.lastIndexOf("/");
		final String uriForResourceCreation = url.substring(0, positionOfLastSlash);
		final String linkHeaderValue = LinkUtil.createLinkHeader(uriForResourceCreation, LinkUtil.REL_COLLECTIONS);
		response.addHeader(HttpHeaders.LINK, linkHeaderValue);
	}

}
