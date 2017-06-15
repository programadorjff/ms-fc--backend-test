package com.scmspain.daos;

import java.util.List;

import com.scmspain.entities.Tweet;

public interface ITweetDAO extends IAbstractTweetDAO {

	/**
	 * Recover list of all tweets published and not discarded from repository
	 * Result - retrieved list of Tweets
	 */
	public List<Tweet> listAllTweets();

}
