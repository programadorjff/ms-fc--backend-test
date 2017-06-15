package com.scmspain.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public abstract class AbstractTweetController {

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(BAD_REQUEST)
	@ResponseBody
	public Object invalidArgumentException(IllegalArgumentException ex) {
		return new Object() {
			private String message = ex.getMessage();
			private String exceptionClass = ex.getClass().getSimpleName();

			public String getMessage() {
				return message;
			}

			public String getExceptionClass() {
				return exceptionClass;
			}
		};
	}

}
