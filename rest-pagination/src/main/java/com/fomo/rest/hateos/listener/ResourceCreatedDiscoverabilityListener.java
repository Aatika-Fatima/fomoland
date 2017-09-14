package com.fomo.rest.hateos.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpHeaders;
import org.assertj.core.util.Preconditions;
import org.springframework.context.ApplicationListener;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fomo.rest.hateos.event.ResourceCreatedEvent;

public class ResourceCreatedDiscoverabilityListener implements ApplicationListener<ResourceCreatedEvent> {

	@Override
	public void onApplicationEvent(final ResourceCreatedEvent resourceCreatedEvent) {
		Preconditions.checkNotNull(resourceCreatedEvent);
		final HttpServletResponse response = resourceCreatedEvent.getResponse();
		final long idOfResource = resourceCreatedEvent.getIdOfNewResource();

	}

	void addLinkHeaderOnResourceCreation(final HttpServletResponse response, final long idOfNewResource) {
		final URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
				.path("/{idOfNewResource}").buildAndExpand(idOfNewResource)
				.toUri();
		response.setHeader(HttpHeaders.LOCATION, uri.toASCIIString());
	}

}
