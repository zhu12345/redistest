package cn.redis.utils;


public class RedisKey {
	private String key;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public boolean isValid() {
		if(key == null || key.length() == 0)
		 return false;
		return true;
	}
	
	
}
