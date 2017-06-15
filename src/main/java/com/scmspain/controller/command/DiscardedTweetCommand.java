package com.scmspain.controller.command;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DiscardedTweetCommand {

	@JsonProperty("tweet")
	private Long tweetId;

	public Long getTweetId() {
		return tweetId;
	}

	public void setTweetId(Long tweetId) {
		this.tweetId = tweetId;
	}

}
