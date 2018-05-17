package com.javen.http;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.nio.charset.CodingErrorAction;

import javax.net.ssl.SSLException;

import org.apache.http.Consts;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.MessageConstraints;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class HttpClientFactory {
	private PoolingHttpClientConnectionManager cm;

	public HttpClientFactory()
	{
		cm = new PoolingHttpClientConnectionManager();
	    MessageConstraints messageConstraints = MessageConstraints.custom()
	            .setMaxHeaderCount(500)
	            .setMaxLineLength(2000)
	            .build();
	     
	    ConnectionConfig connectionConfig = ConnectionConfig.custom()
	            .setMalformedInputAction(CodingErrorAction.IGNORE)
	            .setUnmappableInputAction(CodingErrorAction.IGNORE)
	            .setCharset(Consts.UTF_8)
	            .setMessageConstraints(messageConstraints)
	            .build();
	    cm.setDefaultConnectionConfig(connectionConfig);
	    cm.setMaxTotal(1024);
	    cm.setDefaultMaxPerRoute(512);
	}
	
	public CloseableHttpClient buildClient()
	{
		return HttpClients.custom().setConnectionManager(cm).setRetryHandler(retryHandler).build();
	}

	HttpRequestRetryHandler retryHandler = new HttpRequestRetryHandler() {

	    public boolean retryRequest(
	            IOException exception,
	            int executionCount,
	            HttpContext context) {
	        if (executionCount >= 2) {
	            // Do not retry if over max retry count
	            return false;
	        }
	        if (exception instanceof InterruptedIOException) {
	            // Timeout
	            return false;
	        }
	        if (exception instanceof UnknownHostException) {
	            // Unknown host
	            return false;
	        }
	        if (exception instanceof ConnectTimeoutException) {
	            // Connection refused
	            return false;
	        }
	        if (exception instanceof SSLException) {
	            // SSL handshake exception
	            return false;
	        }
	        HttpClientContext clientContext = HttpClientContext.adapt(context);
	        HttpRequest request = clientContext.getRequest();
	        boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
	        if (idempotent) {
	            return true;
	        }
	        return false;
	    }

	};
}
