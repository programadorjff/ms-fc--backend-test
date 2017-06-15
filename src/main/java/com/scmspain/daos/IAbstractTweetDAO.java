package com.scmspain.daos;

import com.scmspain.entities.Tweet;

public interface IAbstractTweetDAO {

	/**
	 * Recover tweet from repository Parameter - id - id of the Tweet to
	 * retrieve Result - retrieved Tweet
	 */
	public Tweet getTweet(Long id);

	/**
	 * Persist tweet on repository
	 */
	public void saveTweet(Tweet tweet);

}
