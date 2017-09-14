package com.fomo.rest.client;

import java.net.URI;

import org.apache.http.HttpHost;
import org.apache.http.client.AuthCache;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
 public class HttpComponentsClientHttpRequestFactoryBasicAuth extends HttpComponentsClientHttpRequestFactory {
	HttpHost httpHost;

	public HttpComponentsClientHttpRequestFactoryBasicAuth(HttpHost httpHost) {
		super();
		this.httpHost = httpHost;
	} 
	  protected HttpContext createHttpContext(HttpMethod httpMethod, URI uri) {
	        return createHttpContext();
	    }
	 private HttpContext createHttpContext() {

	        AuthCache authCache = new BasicAuthCache();

	        BasicScheme basicAuth = new BasicScheme();
	        authCache.put(httpHost, basicAuth);

	        BasicHttpContext localcontext = new BasicHttpContext();
	        localcontext.setAttribute(HttpClientContext.AUTH_CACHE, authCache);
	        return localcontext;
	    }
}
