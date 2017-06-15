package com.scmspain.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.scmspain.controller.command.PublishTweetCommand;
import com.scmspain.controller.validators.PublishTweetValidator;
import com.scmspain.entities.Tweet;
import com.scmspain.services.ITweetService;

public class TweetController extends AbstractTweetController {

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new PublishTweetValidator());
	}

	@Autowired
	private ITweetService tweetService;

	@GetMapping("/tweet")
	public List<Tweet> listAllTweets() {
		return this.tweetService.listAllTweets();
	}

	@PostMapping("/tweet")
	@ResponseStatus(CREATED)
	public void publishTweet(@Valid @RequestBody PublishTweetCommand publishTweetCommand) {
		this.tweetService.publishTweet(publishTweetCommand);
	}

}
