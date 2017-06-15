package com.scmspain.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.scmspain.entities.Tweet;

@Transactional
@Repository
public abstract class AbstractTweetDAO implements IAbstractTweetDAO {

	@PersistenceContext
	EntityManager entityManager;

	/**
	 * Recover tweet from repository Parameter - id - id of the Tweet to
	 * retrieve Result - retrieved Tweet
	 */
	public Tweet getTweet(Long id) {
		return this.entityManager.find(Tweet.class, id);
	}

	/**
	 * Persist tweet on repository
	 */
	public void saveTweet(Tweet tweet) {
		this.entityManager.persist(tweet);
		this.entityManager.flush();
	}

}
