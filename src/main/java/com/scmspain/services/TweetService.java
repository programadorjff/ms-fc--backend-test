package com.scmspain.services;

import java.util.List;

import org.springframework.boot.actuate.metrics.writer.Delta;
import org.springframework.boot.actuate.metrics.writer.MetricWriter;

import com.scmspain.controller.command.PublishTweetCommand;
import com.scmspain.daos.ITweetDAO;
import com.scmspain.entities.Tweet;
import com.scmspain.services.utils.TweetServiceUtils;

public class TweetService implements ITweetService {

	private MetricWriter metricWriter;
	private ITweetDAO tweetDAO;

	public TweetService(MetricWriter metricWriter, ITweetDAO tweetDAO) {
		this.metricWriter = metricWriter;
		this.tweetDAO = tweetDAO;
	}

	/**
	 * Push tweet to repository Parameter - publishTweetCommand - object with
	 * creator content of the Tweet
	 */
	public void publishTweet(PublishTweetCommand publishTweetCommand) {
		Tweet tweet = new Tweet();
		tweet.setBodyWithoutLinks(TweetServiceUtils.getBodyWithoutLinks(publishTweetCommand.getTweet()));
		tweet.setBody(publishTweetCommand.getTweet());
		tweet.setPublisher(publishTweetCommand.getPublisher());
		this.tweetDAO.saveTweet(tweet);
		this.metricWriter.increment(new Delta<Number>("published-tweets", 1));
	}

	/**
	 * Recover list of all tweets published and not discarded from repository
	 * Result - retrieved list of Tweets
	 */
	public List<Tweet> listAllTweets() {
		this.metricWriter.increment(new Delta<Number>("times-queried-tweets", 1));
		return this.tweetDAO.listAllTweets();
	}

}
