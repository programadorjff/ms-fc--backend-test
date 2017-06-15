package com.scmspain.configuration;

import org.springframework.boot.actuate.metrics.writer.MetricWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.scmspain.controller.ExtendedTweetController;
import com.scmspain.controller.TweetController;
import com.scmspain.daos.ExtendedTweetDAO;
import com.scmspain.daos.IExtendedTweetDAO;
import com.scmspain.daos.ITweetDAO;
import com.scmspain.daos.TweetDAO;
import com.scmspain.services.ExtendedTweetService;
import com.scmspain.services.IExtendedTweetService;
import com.scmspain.services.ITweetService;
import com.scmspain.services.TweetService;

@Configuration
public class TweetConfiguration {

	@Bean
	public ITweetDAO getTweetDAO() {
		return new TweetDAO();
	}

	@Bean
	public IExtendedTweetDAO getExtendedTweetDAO() {
		return new ExtendedTweetDAO();
	}

	@Bean
	public ITweetService getTweetService(MetricWriter metricWriter, ITweetDAO tweetDAO) {
		return new TweetService(metricWriter, tweetDAO);
	}

	@Bean
	public IExtendedTweetService getExtendedTweetService(MetricWriter metricWriter,
			IExtendedTweetDAO extendedTweetDAO) {
		return new ExtendedTweetService(metricWriter, extendedTweetDAO);
	}

	@Bean
	public TweetController getTweetConfiguration() {
		return new TweetController();
	}

	@Bean
	public ExtendedTweetController getExtendedTweetConfiguration() {
		return new ExtendedTweetController();
	}

}
