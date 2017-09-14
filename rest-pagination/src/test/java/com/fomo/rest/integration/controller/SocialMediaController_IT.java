package com.fomo.rest.integration.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.http.client.methods.HttpGet;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fomo.Fomo;
import com.fomo.persistence.model.SocialMedia;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Fomo.class)
@WebAppConfiguration
public class SocialMediaController_IT {
	@Autowired
	WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testGetAllSocialMedia() throws Exception {
		mockMvc.perform(get("/rest/v1/socialMedia"))
				.andExpect(status().isOk());
	}
	
	@Test
	public void whenSocialMediaById_thenReturnSocialMedia(){
		TestRestTemplate testRestTemplate
		 = new TestRestTemplate("aatika", "secret");
		ResponseEntity<SocialMedia> response = testRestTemplate.getRestTemplate().
		  getForEntity("https://localhost:9000/rest/v1/socialMedia/1", SocialMedia.class);
		System.out.println(response.getBody().getPoints() + " "+ response.getBody().getSocialMediaName());
		 
		 
	}
}
