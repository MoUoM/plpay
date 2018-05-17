package com.javen.http;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.javen.util.Config;

@Component
@Lazy
public class SyncHttpClient {
	private static int REQUEST_TIMEOUT = 30000;
	private static int CONNECT_TIMEOUT = 23000;
	@Autowired
	private HttpClientFactory httpClients;
	@Autowired
	private Config config;

	private boolean isDebug;

	@PostConstruct
	private void init() {
		isDebug = "true".equals(config.getProperty("http.debug"));
	}

	private static URI buildURI(String host, String path, boolean isSecured, Map<String, String> parameters) throws URISyntaxException {
		URIBuilder ub = new URIBuilder();
		if (isSecured) {
			ub.setScheme("https");
		} else {
			ub.setScheme("http");
		}
		ub.setHost(host);
		ub.setPath(path);
		if (null != parameters) {
			for (String key : parameters.keySet()) {
				ub.addParameter(key, parameters.get(key));
			}
		}
		return ub.build();
	}

	public void post(String host, String path, boolean isSecured, Map<String, String> parameters, IResponseHandler handler) {
		CloseableHttpResponse response = null;
		try {
			URI uri = buildURI(host, path, isSecured, null);

//			if (isDebug) {
//				System.out.println(uri.toASCIIString());
//				for (String key : parameters.keySet()) {
//					System.out.println("key:" + key + " = " + parameters.get(key));
//				}
//			}
			HttpPost httppost = new HttpPost(uri);
			RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(REQUEST_TIMEOUT).setConnectTimeout(CONNECT_TIMEOUT).build();
			httppost.setConfig(requestConfig);
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			if (null != parameters) {
				for (String key : parameters.keySet()) {
					params.add(new BasicNameValuePair(key, parameters.get(key)));
				}
			}
			httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

			response = httpClients.buildClient().execute(httppost, new BasicHttpContext());
			handleResponse(response, handler);
		} catch (RuntimeException ex2) {
			throw ex2;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			if (null != response) {
				try {
					response.close();
				} catch (Exception ex2) {

				}
			}
		}
	}
	
	/**
	 * 自带超时时间的tiemout
	 * @param host
	 * @param path
	 * @param isSecured
	 * @param parameters
	 * @param request_timeout
	 * @param connect_timeout
	 */
	public void post(String host, String path, boolean isSecured, Map<String, String> parameters, IResponseHandler handler, int request_timeout, int connect_timeout) {
		CloseableHttpResponse response = null;
		try {
			URI uri = buildURI(host, path, isSecured, null);

//			if (isDebug) {
//				System.out.println(uri.toASCIIString());
//				for (String key : parameters.keySet()) {
//					System.out.println("key:" + key + " = " + parameters.get(key));
//				}
//			}
			HttpPost httppost = new HttpPost(uri);
			RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(request_timeout).setConnectTimeout(connect_timeout).build();
			httppost.setConfig(requestConfig);
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			if (null != parameters) {
				for (String key : parameters.keySet()) {
					params.add(new BasicNameValuePair(key, parameters.get(key)));
				}
			}
			httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			response = httpClients.buildClient().execute(httppost, new BasicHttpContext());
			handleResponse(response, handler);
		} catch (RuntimeException ex2) {
//			throw ex2;
		} catch (Exception ex) {
//			throw new RuntimeException(ex);
		} finally {
			if (null != response) {
				try {
					response.close();
				} catch (Exception ex2) {

				}
			}
		}
	}
	
	public static void main(String[] args) throws URISyntaxException, ClientProtocolException, IOException {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("car_no", "粤A99999");
		parameters.put("card_no", "106535105");
		parameters.put("card_type", "1");
		parameters.put("create_ip", "127.0.0.1");
		parameters.put("discount_amount", "8");
		parameters.put("nonce_str", "abc");
		parameters.put("order_no", "106535105_170920L1");
		parameters.put("park_code", "201600013");
		parameters.put("time_expire", "20170920235959");
		parameters.put("key", "d1a2e272ed252ca4459073a12abaabf6");
		parameters.put("sign", "7E02B1BBAF692B2FB5E6C138D1745FF7");
		
		CloseableHttpResponse response = null;
		String respContent = "";
		try {
			URI uri = buildURI("test.askparking.com", "/v1/memberdis/writeoff.api", false, null);

			HttpPost httppost = new HttpPost(uri);
			RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(REQUEST_TIMEOUT).setConnectTimeout(CONNECT_TIMEOUT).build();
			httppost.setConfig(requestConfig);
			httppost.setHeader("Content-Type","application/x-www-form-urlencoded");
			List<NameValuePair> params=new ArrayList<NameValuePair>();
			if(null!=parameters){
				for(String key:parameters.keySet()){
					params.add(new BasicNameValuePair(key, parameters.get(key)));
				}
			}
			httppost.setEntity(new UrlEncodedFormEntity(params,"utf-8"));
			HttpClientFactory httpClient=new HttpClientFactory();
			response=httpClient.buildClient().execute(httppost);
			if(response.getStatusLine().getStatusCode()==200){
				HttpEntity entity=response.getEntity();
				respContent =EntityUtils.toString(entity,"utf-8");
			}
			System.out.println(respContent);
		} catch (RuntimeException ex2) {
			throw ex2;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			if (null != response) {
				try {
					response.close();			
				} catch (Exception ex2) {

				}
			}
		}
	}
	
	/**
	 * HTTPS 信任所有
	 * @return
	 */
	public static CloseableHttpClient createSSLClientDefault(){
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(
					null, new TrustStrategy() {
						// 信任所有
						public boolean isTrusted(X509Certificate[] chain,
								String authType) throws CertificateException {
							return true;
						}
					}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
					sslContext);
			return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		return  HttpClients.createDefault();
	}
	
	public String postWx(String host, String path, Map<String, String> parameters, boolean isSecured) {
		CloseableHttpResponse response = null;
		CloseableHttpClient httpClient = createSSLClientDefault();
		String result = "";
		try {
			URIBuilder ub = new URIBuilder();
			if(isSecured){
				ub.setScheme("https");
			} else{
				ub.setScheme("http");
			}
			ub.setHost(host);
			ub.setPath(path);
			
			URI uri = ub.build();

			HttpPost httppost = new HttpPost(uri);
			RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(REQUEST_TIMEOUT).setConnectTimeout(CONNECT_TIMEOUT).build();
			httppost.setConfig(requestConfig);
			httppost.addHeader("Content-Type", "text/xml");
			StringBuilder params = new StringBuilder("<xml>");
			if (null != parameters) {
				for (String key : parameters.keySet()) {
					params.append("<").append(key).append(">").append(parameters.get(key)).append("</").append(key).append(">");
				}
			}
			params.append("</xml>");
//			System.out.println("发送的参数: " + params.toString());
			StringEntity stringEntity = new StringEntity(params.toString(), "UTF-8");
			httppost.setEntity(stringEntity);
			response = httpClient.execute(httppost);
			
			HttpEntity entity = response.getEntity();
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == 200) {
				result = EntityUtils.toString(entity, "UTF-8");
//				System.out.println("Post request return: " + result);
			}
		} catch (RuntimeException ex2) {
			throw ex2;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			if (null != response) {
				try {
					response.close();
				} catch (Exception ex2) {

				}
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @param host
	 * @param path
	 * @param parameters   Map<String, String> or JSONObejct
	 * @param isSecured
	 * @param type
	 * @return
	 */
	public String post(String host, String path, Object parameters, boolean isSecured,String type) {
		CloseableHttpResponse response = null;
		CloseableHttpClient httpClient = createSSLClientDefault();
		String result = "";
		try {
			URIBuilder ub = new URIBuilder();
			ub.setScheme("http");
			ub.setHost(host);
			ub.setPath(path);
			URI uri = ub.build();
			
			System.out.println("输出Uri: " + uri.toString());
			
			HttpPost httppost = new HttpPost(uri);
			RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(REQUEST_TIMEOUT).setConnectTimeout(CONNECT_TIMEOUT).build();
			httppost.setConfig(requestConfig);
			httppost.addHeader("Content-Type", "application/json");
			StringEntity stringEntity = new StringEntity(parameters.toString(), "UTF-8");
			httppost.setEntity(stringEntity);
			response = httpClient.execute(httppost);
			
			HttpEntity entity = response.getEntity();
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == 200) {
				result = EntityUtils.toString(entity, "UTF-8");
//				System.out.println("Post request return: " + result);
			}
		} catch (RuntimeException ex2) {
			throw ex2;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			if (null != response) {
				try {
					response.close();
				} catch (Exception ex2) {

				}
			}
		}
		return result;
	}
	
	public String postBestPay(String host, String path, Map<String, String> parameters, boolean isSecured) {
		CloseableHttpResponse response = null;
		CloseableHttpClient httpClient = createSSLClientDefault();
		String result = "";
		try {
			URIBuilder ub = new URIBuilder();
			ub.setScheme("https");
			ub.setHost(host);
			ub.setPath(path);
			
			URI uri = ub.build();

			HttpPost httppost = new HttpPost(uri);
			RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(REQUEST_TIMEOUT).setConnectTimeout(CONNECT_TIMEOUT).build();
			httppost.setConfig(requestConfig);
			httppost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			StringEntity stringEntity = new StringEntity(parameters.toString(), "UTF-8");
			httppost.setEntity(stringEntity);
			response = httpClient.execute(httppost);
			
			HttpEntity entity = response.getEntity();
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == 200) {
				result = EntityUtils.toString(entity, "UTF-8");
			}
		} catch (RuntimeException ex2) {
			throw ex2;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			if (null != response) {
				try {
					response.close();
				} catch (Exception ex2) {

				}
			}
		}
		return result;
	}

	public CloseableHttpResponse get(String host, String path, boolean isSecured, Map<String, String> parameters) {
		CloseableHttpResponse response = null;
		try {
			URI uri = buildURI(host, path, isSecured, parameters);

			HttpGet httpget = new HttpGet(uri);
			RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(REQUEST_TIMEOUT).setConnectTimeout(CONNECT_TIMEOUT).build();
			httpget.setConfig(requestConfig);
			response = httpClients.buildClient().execute(httpget, new BasicHttpContext());
			return response;
		} catch (RuntimeException ex2) {
			throw ex2;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			if (null != response) {
				try {
					response.close();
				} catch (Exception ex2) {

				}
			}
		}
	}

	public void get(String host, String path, boolean isSecured, Map<String, String> parameters, IResponseHandler handler) {
		CloseableHttpResponse response = null;
		try {
			URI uri = buildURI(host, path, isSecured, parameters);
//			if (isDebug) {
//				System.out.println(uri.toASCIIString());
//			}
			HttpGet httpget = new HttpGet(uri);

			RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(REQUEST_TIMEOUT).setConnectTimeout(CONNECT_TIMEOUT).build();
			httpget.setConfig(requestConfig);
			response = httpClients.buildClient().execute(httpget, new BasicHttpContext());
			handleResponse(response, handler);
		} catch (RuntimeException ex2) {
			throw ex2;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			if (null != response) {
				try {
					response.close();
				} catch (Exception ex2) {

				}
			}
		}
	}

	private void handleResponse(HttpResponse response, IResponseHandler handler) throws Exception {
		handler.setDebug(isDebug);
		HttpEntity entity = response.getEntity();
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode == 200) {
			handler.onSuccess(handler.handle(entity));
		} else {
			handler.onFailure(statusCode, EntityUtils.toByteArray(entity));
		}
	}
	
	public void postJson(String host, String path, boolean isSecured, String parameters, IResponseHandler handler) {
		CloseableHttpResponse response = null;
		try {
			URI uri = buildURI(host, path, isSecured, null);

			HttpPost httppost = new HttpPost(uri);
			RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(REQUEST_TIMEOUT).setConnectTimeout(CONNECT_TIMEOUT).build();
			httppost.setConfig(requestConfig);
			
			StringEntity entity = new StringEntity(parameters,"utf-8");
			httppost.setEntity(entity);

			response = httpClients.buildClient().execute(httppost, new BasicHttpContext());
			handleResponse(response, handler);
		} catch (RuntimeException ex2) {
			throw ex2;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			if (null != response) {
				try {
					response.close();
				} catch (Exception ex2) {

				}
			}
		}
	}
	
	public String postJson(String host, String path, boolean isSecured, String parameters) {
		CloseableHttpResponse response = null;
		String respContent = "";
		try {
			URI uri = buildURI(host, path, isSecured, null);

			HttpPost httppost = new HttpPost(uri);
			RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(REQUEST_TIMEOUT).setConnectTimeout(CONNECT_TIMEOUT).build();
			httppost.setConfig(requestConfig);
			
			StringEntity entity = new StringEntity(parameters,"utf-8");
			
			httppost.setEntity(entity);

			response = httpClients.buildClient().execute(httppost, new BasicHttpContext());
			if(response.getStatusLine().getStatusCode() == 200) {
				HttpEntity he = response.getEntity();
				respContent = EntityUtils.toString(he, "UTF-8");
			}
		} catch (RuntimeException ex2) {
			throw ex2;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			if (null != response) {
				try {
					response.close();			
				} catch (Exception ex2) {

				}
			}
		}			
		return respContent;
	}
	public String postUnion(String host, String path, boolean isSecured, Map<String ,String> parameters) {
		CloseableHttpResponse response = null;
		String respContent = "";
		try {
			URI uri = buildURI(host, path, isSecured, null);

			HttpPost httppost = new HttpPost(uri);
			RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(REQUEST_TIMEOUT).setConnectTimeout(CONNECT_TIMEOUT).build();
			httppost.setConfig(requestConfig);
			httppost.setHeader("Content-Type","application/x-www-form-urlencoded");
			List<NameValuePair> params=new ArrayList<NameValuePair>();
			if(null!=parameters){
				for(String key:parameters.keySet()){
					params.add(new BasicNameValuePair(key, parameters.get(key)));
				}
			}
			httppost.setEntity(new UrlEncodedFormEntity(params,"utf-8"));
			HttpClientFactory httpClient=new HttpClientFactory();
			response=httpClient.buildClient().execute(httppost);
			if(response.getStatusLine().getStatusCode()==200){
				HttpEntity entity=response.getEntity();
				respContent =EntityUtils.toString(entity,"utf-8");
			}
		} catch (RuntimeException ex2) {
			throw ex2;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			if (null != response) {
				try {
					response.close();			
				} catch (Exception ex2) {

				}
			}
		}			
		return respContent;
	}
	
	public String postOil(String host, String path, boolean isSecured, Map<String, String> parameters) {
		CloseableHttpResponse response = null;
		String result = "";
		try {
			URI uri = buildURI(host, path, isSecured, null);

//			if (isDebug) {
//				System.out.println(uri.toASCIIString());
//				for (String key : parameters.keySet()) {
//					System.out.println("key:" + key + " = " + parameters.get(key));
//				}
//			}
			HttpPost httppost = new HttpPost(uri);
			RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(REQUEST_TIMEOUT).setConnectTimeout(CONNECT_TIMEOUT).build();
			httppost.setConfig(requestConfig);
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			if (null != parameters) {
				for (String key : parameters.keySet()) {
					params.add(new BasicNameValuePair(key, parameters.get(key)));
				}
			}
			httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

			response = httpClients.buildClient().execute(httppost, new BasicHttpContext());
			
			
			HttpEntity entity = response.getEntity();
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == 200) {
				result = EntityUtils.toString(entity, "UTF-8");
			}
		} catch (RuntimeException ex2) {
			throw ex2;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			if (null != response) {
				try {
					response.close();
				} catch (Exception ex2) {

				}
			}
		}
		return result;
	}
}
