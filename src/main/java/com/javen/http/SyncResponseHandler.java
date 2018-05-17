package com.javen.http;

import java.util.List;

@SuppressWarnings("rawtypes")
public abstract class SyncResponseHandler<T> extends StringResponseHandler {
	public static final int OK = 200;

	private int statusCode;
	private T data;
	private boolean isDebug;

	public void setDebug(boolean debug) {
		this.isDebug = debug;		
	}

	public void onSuccess(String content) {
		this.statusCode = 200;

    	if (isDebug)
		{
//    		System.out.println("Return From Server: " + statusCode + ":" + content);
		}
		try
		{
			this.data = parse(content);
		}
		catch (RuntimeException ex2)
		{
			throw ex2;
		}
		catch (Exception ex)
		{
			throw new RuntimeException(ex);
		}
	}

	public void onFailure(int statusCode, byte[] content) {
		this.statusCode = statusCode;
		
		String strContent = new String(content);
		if (isDebug)
		{
//    		System.out.println("Return From Server: " + statusCode + ":" + strContent);
		}
		throw new RuntimeException("With Error " + statusCode + " " + strContent);
	}
	
	public abstract T parse(String content) throws Exception;
	
	public List parseList(String content) throws Exception{
		return null;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
