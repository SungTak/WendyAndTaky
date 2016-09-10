package com.taky.and.wendy.home.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.taky.and.wendy.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class HomeControllerTest {
	private HomeController sut;
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		sut = new HomeController();
		this.mockMvc = MockMvcBuilders.standaloneSetup(sut).build();
	}

	@Test
	public void testGoHome() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(
			MockMvcResultMatchers.status().isOk()).andExpect(
				MockMvcResultMatchers.view().name("home"));
	}
}
