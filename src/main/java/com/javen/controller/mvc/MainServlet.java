package com.javen.controller.mvc;

import java.util.Locale;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.SimpleLocaleContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.javen.controller.mvc.util.ApplicationUtil;
import com.javen.controller.mvc.util.SessionUtil;


public class MainServlet extends DispatcherServlet {
	private static final long serialVersionUID = 1L;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		WebApplicationContext appContext = (WebApplicationContext)this.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);  
		
		ServletContext context = this.getServletContext();
		context.setAttribute("ctx", context.getContextPath());
		ApplicationUtil.WEBAPP_CONTEXT = appContext;
	}

	@Override
	protected LocaleContext buildLocaleContext(HttpServletRequest request) {
		Locale loc = (Locale)request.getSession().getAttribute(SessionUtil.KEY_LOCALE);
		if (null == loc)
		{
			loc = request.getLocale();
			//if (!"en".equals(loc.getLanguage()) && !"zh".equals(loc.getLanguage()))
			{
				loc = ApplicationUtil.getDefaultLocale();
			}
			request.getSession().setAttribute(SessionUtil.KEY_LOCALE, loc);
		}
		SessionUtil.setLocale(loc);
		return new SimpleLocaleContext(loc);
	}
}
