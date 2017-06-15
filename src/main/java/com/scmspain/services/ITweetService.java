package com.scmspain.services;

import java.util.List;

import com.scmspain.controller.command.PublishTweetCommand;
import com.scmspain.entities.Tweet;

public interface ITweetService {

	/**
	 * Push tweet to repository Parameter - publishTweetCommand - object with
	 * creator content of the Tweet
	 */
	public void publishTweet(PublishTweetCommand publishTweetCommand);

	/**
	 * Recover list of all tweets published and not discarded from repository
	 * Result - retrieved list of Tweets
	 */
	public List<Tweet> listAllTweets();

}
