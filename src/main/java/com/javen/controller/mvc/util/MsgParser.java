package com.javen.controller.mvc.util;

import org.springframework.context.MessageSource;


public class MsgParser {

	private MessageSource messageSource;

	public String parse(String key)
	{
		return messageSource.getMessage(key, null, SessionUtil.getLocale());
	}
	
	public String parse(String key, Object... params)
	{
		return messageSource.getMessage(key, params, SessionUtil.getLocale());
	}

	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
}
