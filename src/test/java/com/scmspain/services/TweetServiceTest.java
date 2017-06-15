package com.scmspain.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.actuate.metrics.writer.MetricWriter;

import com.scmspain.controller.command.PublishTweetCommand;
import com.scmspain.daos.ITweetDAO;
import com.scmspain.entities.Tweet;

public class TweetServiceTest {

	private ITweetDAO tweetDAO;
	private ITweetService tweetService;

	@Before
	public void setUp() {
		this.tweetDAO = mock(ITweetDAO.class);
		MetricWriter metricWriter = mock(MetricWriter.class);
		this.tweetService = new TweetService(metricWriter, this.tweetDAO);
	}

	@Test
	public void shouldInsertANewTweet() {
		PublishTweetCommand publishTweetCommand = new PublishTweetCommand();
		publishTweetCommand.setPublisher("Guybrush Threepwood");
		publishTweetCommand.setTweet("I am Guybrush Threepwood, mighty pirate.");
		this.tweetService.publishTweet(publishTweetCommand);
		verify(this.tweetDAO).saveTweet(any(Tweet.class));
	}

	@Test
	public void shouldReturnZeroListAllTweetsPublished() {
		assertThat(this.tweetService.listAllTweets().size()).isEqualTo(0);
	}

}
