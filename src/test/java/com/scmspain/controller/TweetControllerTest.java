package com.scmspain.controller;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scmspain.configuration.TestConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
public class TweetControllerTest {

	@Autowired
	private WebApplicationContext context;
	private MockMvc mockMvc;

	@Before
	public void setUp() {
		this.mockMvc = webAppContextSetup(this.context).build();
	}

	@Test
	public void shouldReturn201WhenInsertingAValidTweet() throws URLCRUDOperationException {
		try {
			mockMvc.perform(newTweet("Prospect", "Breaking the law")).andExpect(status().is(201));
		} catch (Exception error) {
			throw new URLCRUDOperationException(error.getMessage(), error);
		}
	}

	@Test
	public void shouldReturn400WhenInsertingAnInvalidTweet() throws URLCRUDOperationException {
		try {
			mockMvc.perform(newTweet("Schibsted Spain",
					"We are Schibsted Spain (look at our home page http://www.schibsted.es/), we own Vibbo, InfoJobs, fotocasa, coches.net and milanuncios. Welcome to one our famous pages in the world! Don't hesitate to contact with us"))
					.andExpect(status().is(400));
		} catch (Exception error) {
			throw new URLCRUDOperationException(error.getMessage(), error);
		}
	}

	@Test
	public void shouldReturnAllPublishedTweets() throws URLCRUDOperationException {
		try {
			mockMvc.perform(newTweet("Yo", "How are you?")).andExpect(status().is(201));

			MvcResult getResult = mockMvc.perform(get("/tweet")).andExpect(status().is(200)).andReturn();

			String content = getResult.getResponse().getContentAsString();
			assertThat(new ObjectMapper().readValue(content, List.class).size()).isEqualTo(1);
		} catch (Exception error) {
			throw new URLCRUDOperationException(error.getMessage(), error);
		}

	}

	public static MockHttpServletRequestBuilder newTweet(String publisher, String tweet) {
		return post("/tweet").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(format("{\"publisher\": \"%s\", \"tweet\": \"%s\"}", publisher, tweet));
	}

	public class URLCRUDOperationException extends Exception {

		static final String DEFAULT_MESSAGE = "CRUD operation failed. ";

		public URLCRUDOperationException(String message) {
			super(new StringBuilder(DEFAULT_MESSAGE).append(message).toString());
		}

		public URLCRUDOperationException(String message, Throwable throwable) {
			super(new StringBuilder(DEFAULT_MESSAGE).append(message).toString(), throwable);
		}

	}

}
