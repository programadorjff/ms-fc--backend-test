package com.scmspain.daos;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.scmspain.entities.Tweet;

@Transactional
@Repository
public class ExtendedTweetDAO extends AbstractTweetDAO implements IExtendedTweetDAO {

	/**
	 * Recover list of all tweets discarded from repository Result - retrieved
	 * list of Tweets
	 */
	public List<Tweet> listAllDiscardedTweets() {
		return this.entityManager.createQuery(
				"SELECT tweet FROM Tweet AS tweet WHERE pre2015MigrationStatus<>99 AND discarded IS NOT NULL ORDER BY discarded DESC",
				Tweet.class).getResultList();
	}

}
