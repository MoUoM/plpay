package com.javen.controller.mvc.util;

import java.util.Locale;

import org.springframework.web.context.WebApplicationContext;

import com.javen.util.Config;


public class ApplicationUtil {
	public static WebApplicationContext WEBAPP_CONTEXT;
	private static Locale DEFAULT_LOCALE;
	public static MsgParser getMsgParser()
	{
		return WEBAPP_CONTEXT.getBean(MsgParser.class);
	}
	
	public static Locale getDefaultLocale()
	{
		if (null == DEFAULT_LOCALE)
		{
			Config config = WEBAPP_CONTEXT.getBean(Config.class);
			DEFAULT_LOCALE = new Locale(config.getProperty("locale.default", "zh"));
		}
		return DEFAULT_LOCALE;
	}
}
