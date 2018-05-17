package com.javen.controller.mvc.util;

import java.util.Locale;

public class SessionUtil {
	public static final String KEY_LOCALE = "LOCALE";
	private static ThreadLocal<Locale> locale = new ThreadLocal<Locale>();

	public static void setLocale(Locale loc)
	{
		Locale loc2 = new Locale(loc.getLanguage());
		locale.set(loc2);
	}
	
	public static Locale getLocale()
	{
		Locale loc = locale.get();
		if (null == loc)
		{
			return ApplicationUtil.getDefaultLocale();
		}
		else
		{
			return loc;
		}
	}
}
