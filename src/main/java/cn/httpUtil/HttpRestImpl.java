package cn.httpUtil;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.httpUtil.dao.HttpRest;



public class HttpRestImpl implements HttpRest
{
	private static final Logger logger = LoggerFactory.getLogger(HttpRestImpl.class);
	public void postParamAsMap(String url, Map<String,String> mapBody, Map<String,String> mapHeader, Map<String,String> mapUrl) {
		 List<NameValuePair> pair = new ArrayList<NameValuePair>();
		 CloseableHttpClient client = HttpClients.createDefault();
		if (mapUrl != null && !mapUrl.isEmpty()) {
			int i=0;
			for (Map.Entry<String,String> entry : mapUrl.entrySet()) {
				if( i==0 ) {
					url = url + "?"+entry.getKey()+"="+entry.getValue();
				} else {
					url = url + "&&"+entry.getKey()+"="+entry.getValue();
				}
				logger.info("URL param {}:{}={}", i, entry.getKey(), entry.getValue());
				i++;
			}
		}
		 HttpPost httppost = new HttpPost(url);
		 if (mapBody != null && !mapBody.isEmpty()) {
			 for (Map.Entry<String,String> entry : mapBody.entrySet()) {  
				 pair.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				 logger.info("Body param :{}={}",entry.getKey(), entry.getValue());
			 } 
		 } 
		 if (mapHeader != null && !mapHeader.isEmpty()) {
			 for (Map.Entry<String,String> entry : mapHeader.entrySet()) {  
				 httppost.setHeader(entry.getKey(), entry.getValue());
				 logger.info("Header param :{}={}",entry.getKey(), entry.getValue());
			 }
		 }
		 try {
			httppost.setEntity(new UrlEncodedFormEntity(pair, Charset.forName("UTF-8")));
			HttpResponse response = client.execute(httppost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK && statusCode != 800) {
				logger.info("请求出错");
	        }else{
	        	logger.info("请求成功");
	        }
			HttpEntity entity = response.getEntity();
			String boty = EntityUtils.toString(entity);
			logger.info(boty);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}

	public void postParamAsstringValue(String url, String key, String value) {
		 List<NameValuePair> pair = new ArrayList<NameValuePair>();
		 CloseableHttpClient client = HttpClients.createDefault();
		 HttpPost httppost = new HttpPost(url);
		 NameValuePair nameValuePair = new BasicNameValuePair(key, value);
		 pair.add(nameValuePair);
		 try {
			httppost.setHeader("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
			httppost.setHeader("Connection", "Close");
			httppost.setEntity(new UrlEncodedFormEntity(pair, Charset.forName("UTF-8")));
			HttpResponse response = client.execute(httppost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
	            System.out.println("请求出错");
	        }else{
	        	System.out.println("请求成功");
	        }
			HttpEntity entity = response.getEntity();
			String boty = EntityUtils.toString(entity);
			System.out.println(boty);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getParamAsMap(String url, Map<String, String> mapUrl, Map<String, String> mapHeader) {
		CloseableHttpClient client = HttpClients.createDefault();
		if (mapUrl != null && !mapUrl.isEmpty()) {
			 int i = 0;
			 for (Map.Entry<String,String> entry : mapUrl.entrySet()) {
				 if( i == 0 ) {
					 url = url + "?"+entry.getKey()+"="+entry.getValue();
				 } else {
					 url = url + "&&"+entry.getKey()+"="+entry.getValue();
				 }
				 logger.info("URL param {}:{}={}", i, entry.getKey(), entry.getValue());
				 i++;
			 }
		 }
		HttpGet httpGet = new HttpGet(url);
		if (mapHeader != null && !mapHeader.isEmpty()) {
			 for (Map.Entry<String,String> entry : mapHeader.entrySet()) {  
				 httpGet.setHeader(entry.getKey(), entry.getValue());
			 }
		 }
		try {
			HttpResponse httpResponse = client.execute(httpGet);
			HttpEntity entity = httpResponse.getEntity();
			String boty = EntityUtils.toString(entity);
			logger.info(boty);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}

	public void getParamAsstringValue(String url, String key, String value) {
		CloseableHttpClient client = HttpClients.createDefault();
		url = url + "?" + key + "=" + value;
		HttpGet httpGet = new HttpGet(url);
		try {
			HttpResponse httpResponse = client.execute(httpGet);
			HttpEntity entity = httpResponse.getEntity();
			String boty = EntityUtils.toString(entity);
			System.out.println(boty);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
