package com.scmspain.controller;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
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
import com.scmspain.entities.Tweet;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
public class ExtendedTweetControllerTest {

	@Autowired
	private WebApplicationContext context;
	private MockMvc mockMvc;

	@Before
	public void setUp() {
		this.mockMvc = webAppContextSetup(this.context).build();
	}

	@Test
	public void shouldReturn200WhenDiscardAValidTweet() throws ExtendedURLCRUDOperationException {
		try {
			mockMvc.perform(TweetControllerTest.newTweet("Yo", "How are you?")).andExpect(status().is(201));
			MvcResult getResult = mockMvc.perform(get("/tweet")).andExpect(status().is(200)).andReturn();
			String content = getResult.getResponse().getContentAsString();
			Tweet[] tweets = jsonStringListToArrayObject(content);
			mockMvc.perform(discardTweet(tweets[0].getId())).andExpect(status().is(200));
		} catch (Exception error) {
			throw new ExtendedURLCRUDOperationException(error.getMessage(), error);
		}
	}

	@Test
	public void shouldReturn400WhenDiscardAnInvalidTweet() throws ExtendedURLCRUDOperationException {
		try {
			mockMvc.perform(discardTweet(99L)).andExpect(status().is(400));
		} catch (Exception error) {
			throw new ExtendedURLCRUDOperationException(error.getMessage(), error);
		}
	}

	@Test
	public void shouldReturnAllDiscardedTweets() throws ExtendedURLCRUDOperationException {
		try {
			mockMvc.perform(TweetControllerTest.newTweet("Yo", "How are you?")).andExpect(status().is(201));
			MvcResult getResult = mockMvc.perform(get("/tweet")).andExpect(status().is(200)).andReturn();
			String content = getResult.getResponse().getContentAsString();
			Tweet[] tweets = jsonStringListToArrayObject(content);
			mockMvc.perform(discardTweet(tweets[0].getId())).andExpect(status().is(200));
			MvcResult getResultDiscarded = mockMvc.perform(get("/discarded")).andExpect(status().is(200)).andReturn();
			String contentDiscarded = getResultDiscarded.getResponse().getContentAsString();
			assertThat(new ObjectMapper().readValue(contentDiscarded, List.class).size()).isGreaterThan(0);
		} catch (Exception error) {
			throw new ExtendedURLCRUDOperationException(error.getMessage(), error);
		}
	}

	private MockHttpServletRequestBuilder discardTweet(Long tweetId) {
		return post("/discarded").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(format("{\"tweet\": \"%s\"}", tweetId));
	}

	private static Tweet[] jsonStringListToArrayObject(String jsonStringList) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(jsonStringList, Tweet[].class);
	}

	public class ExtendedURLCRUDOperationException extends Exception {

		static final String DEFAULT_MESSAGE = "Extended CRUD operation failed. ";

		public ExtendedURLCRUDOperationException(String message) {
			super(new StringBuilder(DEFAULT_MESSAGE).append(message).toString());
		}

		public ExtendedURLCRUDOperationException(String message, Throwable throwable) {
			super(new StringBuilder(DEFAULT_MESSAGE).append(message).toString(), throwable);
		}

	}

}
