package com.javen.http;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

public abstract class StringResponseHandler implements IResponseHandler<String> {

	public String handle(HttpEntity entity) throws IOException {
		
		return EntityUtils.toString(entity);
	}
}
