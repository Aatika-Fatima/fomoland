package com.fomo.rest.unit.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import com.fomo.persistence.dao.SocialMediaRepository;
import com.fomo.persistence.model.SocialMedia;
import com.fomo.rest.controller.SocialMediaController;

public class SocialMediaControllerTest {
	@InjectMocks
	SocialMediaController socialMediaController;

	@Mock
	private SocialMediaRepository socialMediaRepository;
	private MockMvc mockMvc;
 
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = standaloneSetup(socialMediaController).build();
	}

	@Test
	public void testGetAllPolls() throws Exception {
		when(socialMediaRepository.findAll()).thenReturn(new ArrayList<SocialMedia>());
		mockMvc.perform(get("/rest/v1/socialMedia"))
				.andExpect(status().isOk());
			
	}
		
		
}
