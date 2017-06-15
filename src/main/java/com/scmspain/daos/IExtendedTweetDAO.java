package com.scmspain.daos;

import java.util.List;

import com.scmspain.entities.Tweet;

public interface IExtendedTweetDAO extends IAbstractTweetDAO {

	/**
	 * Recover list of all tweets discarded from repository Result - retrieved
	 * list of Tweets
	 */
	public List<Tweet> listAllDiscardedTweets();

}
