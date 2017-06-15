package com.scmspain.controller.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.scmspain.controller.command.PublishTweetCommand;
import com.scmspain.services.utils.TweetServiceUtils;

public class PublishTweetValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return PublishTweetCommand.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errorList) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errorList, "publisher", "publisher.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errorList, "tweet", "tweet.empty");
		PublishTweetCommand publishTweet = (PublishTweetCommand) target;
		if (TweetServiceUtils.getBodyWithoutLinks(publishTweet.getTweet()).length() > 140) {
			errorList.rejectValue("tweet", "tweet.exceeded.length");
		}
	}

}