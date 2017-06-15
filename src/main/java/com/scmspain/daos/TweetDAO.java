package com.scmspain.daos;

import java.util.List;

import com.scmspain.entities.Tweet;

public class TweetDAO extends AbstractTweetDAO implements ITweetDAO {

	/**
	 * Recover list of all tweets published and not discarded from repository
	 * Result - retrieved list of Tweets
	 */
	public List<Tweet> listAllTweets() {
		return this.entityManager.createQuery(
				"SELECT tweet FROM Tweet AS tweet WHERE pre2015MigrationStatus<>99 AND discarded IS NULL ORDER BY published DESC",
				Tweet.class).getResultList();
	}

}
