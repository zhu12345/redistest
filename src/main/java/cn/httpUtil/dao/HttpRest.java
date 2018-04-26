package cn.httpUtil.dao;

import java.util.Map;

public interface HttpRest {
	public void postParamAsMap(String url, Map<String,String> mapBody, Map<String,String> mapHeader, Map<String,String> mapUrl);
	public void postParamAsstringValue(String url, String key, String value);
	public void getParamAsMap(String url, Map<String,String> mapParam , Map<String, String> mapHeader);
	public void getParamAsstringValue(String url, String key, String value);
}
