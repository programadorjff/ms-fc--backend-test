package com.scmspain.daos;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import com.scmspain.configuration.TestConfiguration;
import com.scmspain.entities.Tweet;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
public class TweetDAOTest {

	@Autowired
	private ITweetDAO tweetDAO;

	@Test
	public void shouldInsertANewTweet() {
		Tweet tweet = new Tweet();
		tweet.setPublisher("Guybrush Threepwood");
		tweet.setBody("I am Guybrush Threepwood, mighty pirate.");
		tweet.setBodyWithoutLinks("I am Guybrush Threepwood, mighty pirate.");
		tweetDAO.saveTweet(tweet);
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void shouldReturnErrornWhenInsertingAnInvalidTweet() throws DataIntegrityViolationException {
		Tweet tweet = new Tweet();
		tweet.setPublisher("Guybrush Threepwood");
		tweet.setBody("I am Guybrush Threepwood, mighty pirate.");
		tweetDAO.saveTweet(tweet);
	}

	@Test
	public void shouldReturnAllPublishedTweets() {
		Tweet tweet = new Tweet();
		tweet.setPublisher("Guybrush Threepwood");
		tweet.setBody("I am Guybrush Threepwood, mighty pirate.");
		tweet.setBodyWithoutLinks("I am Guybrush Threepwood, mighty pirate.");
		tweetDAO.saveTweet(tweet);
		assertThat(tweetDAO.listAllTweets().size()).isGreaterThan(0);
	}

}
