package dev.code.test.levenshtein.rest.web.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import dev.code.test.levenshtein.rest.web.config.service.JsonObjectMapper;
import dev.code.test.levenshtein.rest.web.dto.InputDto;

public class APIControllerTest  extends BaseMockedSpringMVCTest {

	@Autowired
	private JsonObjectMapper objectMapper;
	
	@Test
	public final void integrationTest() throws JsonProcessingException, Exception {
		InputDto request = new InputDto();
		request.firstWord = "kitten";
		request.secondWord = "sitting";
		request.caseSensitive = false;
		
		mockMvc.perform(
				post("/api/distance/calculate")
				.content(objectMapper.writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$",equalTo(3)));
	}
	
	@Test
	public final void ifAWordIsNullShouldGive422() throws JsonProcessingException, Exception {
		InputDto request = new InputDto();
		request.firstWord = null;
		request.secondWord = "sitting";
		request.caseSensitive = false;
		
		mockMvc.perform(
				post("/api/distance/calculate")
				.content(objectMapper.writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(status().isUnprocessableEntity());
	}

}
