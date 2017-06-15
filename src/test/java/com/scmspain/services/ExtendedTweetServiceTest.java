package com.scmspain.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.actuate.metrics.writer.MetricWriter;

import com.scmspain.controller.command.DiscardedTweetCommand;
import com.scmspain.daos.IExtendedTweetDAO;

public class ExtendedTweetServiceTest {

	private IExtendedTweetService extendedTweetService;

	@Before
	public void setUp() {
		IExtendedTweetDAO extendedTweetDAO = mock(IExtendedTweetDAO.class);
		MetricWriter metricWriter = mock(MetricWriter.class);
		this.extendedTweetService = new ExtendedTweetService(metricWriter, extendedTweetDAO);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowAnExceptionWhenTweetIdIsInvalid() {
		DiscardedTweetCommand discardedTweetCommand = new DiscardedTweetCommand();
		discardedTweetCommand.setTweetId(1L);
		this.extendedTweetService.discardedTweet(discardedTweetCommand);
	}

	@Test
	public void shouldReturnZeroListAllTweetsDiscarded() {
		assertThat(this.extendedTweetService.listAllDiscardedTweets().size()).isEqualTo(0);
	}

}
