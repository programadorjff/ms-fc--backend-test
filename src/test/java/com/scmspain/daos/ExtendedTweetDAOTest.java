package com.scmspain.daos;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.scmspain.configuration.TestConfiguration;
import com.scmspain.entities.Tweet;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
public class ExtendedTweetDAOTest {

	@Autowired
	private IExtendedTweetDAO extendedTweetDAO;

	@Test
	public void shouldDiscardATweet() {
		Tweet tweet = new Tweet();
		tweet.setPublisher("Guybrush Threepwood");
		tweet.setBody("I am Guybrush Threepwood, mighty pirate.");
		tweet.setBodyWithoutLinks("I am Guybrush Threepwood, mighty pirate.");
		tweet.setDiscarded(new Date());
		extendedTweetDAO.saveTweet(tweet);
	}

	@Test(expected = NullPointerException.class)
	public void shouldReturnErrornWhenDiscardingAnInvalidTweet() throws NullPointerException {
		Tweet tweet = extendedTweetDAO.getTweet(99L);
		tweet.setDiscarded(new Date());
		extendedTweetDAO.saveTweet(tweet);
	}

	@Test
	public void shouldReturnAllDiscardedTweets() {
		Tweet tweet = new Tweet();
		tweet.setPublisher("Guybrush Threepwood");
		tweet.setBody("I am Guybrush Threepwood, mighty pirate.");
		tweet.setBodyWithoutLinks("I am Guybrush Threepwood, mighty pirate.");
		tweet.setDiscarded(new Date());
		extendedTweetDAO.saveTweet(tweet);
		assertThat(extendedTweetDAO.listAllDiscardedTweets().size()).isGreaterThan(0);
	}

}
