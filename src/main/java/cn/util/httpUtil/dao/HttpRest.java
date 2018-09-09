package cn.util.httpUtil.dao;

import java.util.Map;

public interface HttpRest {
	public String postParamAsMap(String url, Map<String, String> mapBody, Map<String, String> mapHeader, Map<String, String> mapUrl);
	public String postParamAsstringValue(String url, String key, String value);
	public String getParamAsMap(String url, Map<String, String> mapParam, Map<String, String> mapHeader);
	public String getParamAsstringValue(String url, String key, String value);
}
