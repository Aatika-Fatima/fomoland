package com.fomo.rest.unit.controller;

import org.junit.Before;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import com.fomo.persistence.dao.AccountRepository;
import com.fomo.persistence.service.IAccountService;
import com.fomo.web.controller.UserController;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootConfiguration
@ContextConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class UserControllerTest {

	@InjectMocks
	UserController userController;

	@Mock
	private AccountRepository accountRepository;

	@Mock
	private IAccountService accountService;

	public UserControllerTest() {
		super();
	}
	
	private MockMvc mockMvc;
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		mockMvc =  MockMvcBuilders.standaloneSetup(userController).build();
	}
	
	public void whenShowContentFeed_then_ShowContentFeed(){
	}
}
