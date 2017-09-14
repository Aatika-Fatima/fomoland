package com.fomo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.client.RestTemplate;

public class RestClient {
	
	 
	@Autowired
	@Qualifier("restTemplate")
	static OAuth2RestTemplate restTemplate;
	
	private static void facebookTemplate(){

	}
	public static void main(String[] args) {
	 
  	  //	RestTemplate restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(getMessageConverters());
		String uri="http://localhost:9000/rest/v1/interests";
		List<HttpMessageConverter<?>> messageConverterList = restTemplate
				.getMessageConverters(); 
		
		// Add MappingJackson2HttpMessageConverter and MarshallingHttpMessageConverter to the messageConverterList
		MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
		XStreamMarshaller marshaller = new XStreamMarshaller();
	    MarshallingHttpMessageConverter marshallingConverter = new MarshallingHttpMessageConverter(marshaller);
		messageConverterList.add(jsonMessageConverter);
		messageConverterList.add(marshallingConverter);
		restTemplate.setMessageConverters(messageConverterList);
         
		// Prepare HTTPHeaders
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		/* headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML)); */
		
		// Prepare HttpEntity
		HttpEntity<String> entity = new HttpEntity<String>(headers);
	 
		ResponseEntity<List> result = restTemplate.exchange(uri,
				HttpMethod.GET, entity, List.class);
		System.out.println(result.getBody().get(0));
	}
	
	private static List<HttpMessageConverter<?>> getMessageConverters() {
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		final MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();

		jsonConverter.setSupportedMediaTypes(
				Arrays.asList(MediaType.APPLICATION_JSON_UTF8));

		converters.add(jsonConverter);
		return converters;
	}
}
