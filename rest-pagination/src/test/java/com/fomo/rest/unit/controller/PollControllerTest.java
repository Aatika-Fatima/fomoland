package com.fomo.rest.unit.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.fomo.persistence.dao.AccountRepository;
import com.fomo.persistence.dao.PollRepository;
import com.fomo.persistence.model.Poll;
import com.fomo.rest.controller.AccountRestController;
import com.fomo.rest.controller.PollController;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootConfiguration
@ContextConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class PollControllerTest {
	@InjectMocks
	PollController pollController;

	@Mock
	private PollRepository pollRepository;
	
	
	private MockMvc mockMvc;
 
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = standaloneSetup(pollController).build();
	}

	@Test
	public void testGetAllPolls() throws Exception {
		when(pollRepository.findAll()).thenReturn(new ArrayList<Poll>());
		mockMvc.perform(get("/polls"))
				.andExpect(status().isOk())
				.andExpect(content().string("[]"));
		
		
	}
}
