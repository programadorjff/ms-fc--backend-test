package com.scmspain.services.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class TweetServiceUtils {

	private static final String URL_LINK = "\\b(https?)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
	private static final Pattern URL_PATTERN = Pattern.compile(URL_LINK);

	private TweetServiceUtils() {
	}

	/**
	 * Clean url links from body of the tweet Parameter - body Result -
	 * recovered Tweet body without links
	 */
	public static String getBodyWithoutLinks(String body) {
		final Matcher matcher = URL_PATTERN.matcher(body);
		final StringBuilder stringBuilder = new StringBuilder(body);

		while (matcher.find()) {
			String url = matcher.group();
			stringBuilder.delete(stringBuilder.indexOf(url), (stringBuilder.indexOf(url) + url.length()));
		}

		return stringBuilder.toString();
	}

}
