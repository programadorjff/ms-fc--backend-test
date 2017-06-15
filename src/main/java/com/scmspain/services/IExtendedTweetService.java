package com.scmspain.services;

import java.util.List;

import com.scmspain.controller.command.DiscardedTweetCommand;
import com.scmspain.entities.Tweet;

public interface IExtendedTweetService {

	/**
	 * Update tweet to repository Parameter - DiscardedTweetCommand - object
	 * with id of the Tweet
	 */
	public void discardedTweet(DiscardedTweetCommand discardedTweetCommand);

	/**
	 * Recover list of all tweets discarded from repository Result - retrieved
	 * list of Tweets
	 */
	public List<Tweet> listAllDiscardedTweets();

}
