package com.scmspain.services;

import java.util.Date;
import java.util.List;

import org.springframework.boot.actuate.metrics.writer.Delta;
import org.springframework.boot.actuate.metrics.writer.MetricWriter;

import com.scmspain.controller.command.DiscardedTweetCommand;
import com.scmspain.daos.IExtendedTweetDAO;
import com.scmspain.entities.Tweet;

public class ExtendedTweetService implements IExtendedTweetService {

	private MetricWriter metricWriter;
	private IExtendedTweetDAO extendedTweetDAO;

	public ExtendedTweetService(MetricWriter metricWriter, IExtendedTweetDAO extendedTweetDAO) {
		this.metricWriter = metricWriter;
		this.extendedTweetDAO = extendedTweetDAO;
	}

	/**
	 * Update tweet to repository Parameter - DiscardedTweetCommand - object
	 * with id of the Tweet
	 */
	public void discardedTweet(DiscardedTweetCommand discardedTweetCommand) {
		Tweet tweet = this.extendedTweetDAO.getTweet(discardedTweetCommand.getTweetId());
		if (tweet != null) {
			tweet.setDiscarded(new Date());
			this.extendedTweetDAO.saveTweet(tweet);
			this.metricWriter.increment(new Delta<Number>("discarded-tweets", 1));
			this.metricWriter.increment(new Delta<Number>("published-tweets", -1));
		} else {
			throw new IllegalArgumentException("Tweet id sent not found");
		}
	}

	/**
	 * Recover list of all tweets discarded from repository Result - retrieved
	 * list of Tweets
	 */
	public List<Tweet> listAllDiscardedTweets() {
		this.metricWriter.increment(new Delta<Number>("times-queried-tweets-discarded", 1));
		return this.extendedTweetDAO.listAllDiscardedTweets();
	}

}
