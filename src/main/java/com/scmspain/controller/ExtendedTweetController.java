package com.scmspain.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.scmspain.controller.command.DiscardedTweetCommand;
import com.scmspain.entities.Tweet;
import com.scmspain.services.IExtendedTweetService;

public class ExtendedTweetController extends AbstractTweetController {

	@Autowired
	private IExtendedTweetService extendedTweetService;

	@GetMapping("/discarded")
	public List<Tweet> listAllDiscardedTweets() {
		return this.extendedTweetService.listAllDiscardedTweets();
	}

	@PostMapping("/discarded")
	@ResponseStatus(OK)
	public void discardTweet(@RequestBody DiscardedTweetCommand discardedTweetCommand) {
		this.extendedTweetService.discardedTweet(discardedTweetCommand);
	}

}
