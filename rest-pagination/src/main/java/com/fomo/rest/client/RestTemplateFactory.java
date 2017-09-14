package com.fomo.rest.client;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpHost;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateFactory implements FactoryBean<RestTemplate>, InitializingBean {
	private RestTemplate restTemplate;

	@Override
	public void afterPropertiesSet() throws Exception {
		HttpHost host = new HttpHost("localhost", 9000, "https");
		final ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactoryBasicAuth(host);
		restTemplate = new RestTemplate(requestFactory);
		 
		restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("aatika", "secret"));

	}

	@Override
	public RestTemplate getObject() throws Exception {
		// TODO Auto-generated method stub
		return restTemplate;
	}

	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return RestTemplate.class;
	}

	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return true;
	}
	
 

}
