package com.fomo.rest.unit.controller;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import com.fomo.persistence.dao.AccountRepository;
import com.fomo.persistence.model.SocialMedia;
import com.fomo.persistence.service.IAccountService;
import com.fomo.rest.controller.AccountRestController;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootConfiguration
@ContextConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class AccountControllerTest {
	@InjectMocks
	AccountRestController accountRestController;

	@Mock
	private AccountRepository accountRepository;

	@Mock
	private IAccountService accountService;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = standaloneSetup(accountRestController).build();

	}

	@Test
	public void whenFindPointById_ThenReturnOK() throws Exception {
		when(accountRepository.getPointsById(1)).thenReturn(new Integer(100));
		when(accountService.getPointsById(1)).thenReturn(new Integer(100));
		mockMvc.perform(get("/rest/v1/accounts/{id}", 1))
				.andExpect(status().isOk());

	}

 
	
	@Test
	public void givenConsumingJson_whenReadingSocialMedia_thenCorrect() {
		String URI = "https://localhost:9000/rest/v1/socialMedia";

		ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(
				HttpClients.createDefault());

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(getMessageConverters());

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		ParameterizedTypeReference<List<SocialMedia>> responseType = new ParameterizedTypeReference<List<SocialMedia>>() {
		};
		ResponseEntity<List<SocialMedia>> list = restTemplate.exchange("https://localhost:9000/rest/v1/socialMedia",
				HttpMethod.GET, entity, responseType);
		List<SocialMedia> socialMediaList = list.getBody();
		for (SocialMedia socialMedia : socialMediaList) {
			System.err.println(socialMedia.getSocialMediaName());
		}
		assertThat(list, notNullValue());
	}

	private List<HttpMessageConverter<?>> getMessageConverters() {
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		final MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		jsonConverter
				.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));
		converters.add(jsonConverter);
		return converters;
	}

	@Test
	public final void givenAcceptingAllCertificatesUsing4_4_whenHttpsUrlIsConsumedUsingRestTemplate_thenCorrect()
			throws ClientProtocolException, IOException {

		final String urlOverHttps = "https://localhost:9000/rest/v1/socialMedia";

		final CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier())
				.build();
		final HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		ParameterizedTypeReference<List<SocialMedia>> responseType = new ParameterizedTypeReference<List<SocialMedia>>() {
		};
		final ResponseEntity<List<SocialMedia>> response = new RestTemplate(requestFactory)
				.exchange("https://localhost:9000/rest/v1/socialMedia",
				HttpMethod.GET, entity, responseType);
		List<SocialMedia> socialMediaList = response.getBody();
		for (SocialMedia socialMedia : socialMediaList) {
			System.err.println(socialMedia.getSocialMediaName());
		}
		assertThat(response.getStatusCode().value(), equalTo(200));
	}
}
