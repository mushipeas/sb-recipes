package com.mushipeas.springbootrecipes.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class WebApplicationIndexPageTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void baseUrl_shouldReturnSearchPage() throws Exception {
		mockMvc.perform(get("/"))
				.andExpectAll(status().isOk(), content().string(containsString("Search for a recipe..")));
	}

}
