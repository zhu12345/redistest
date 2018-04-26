package cn.redis.utils;

import java.util.concurrent.TimeUnit;


public class RedisLifeCycle {
	private long timeout;
	private TimeUnit unit;
	public RedisLifeCycle(long timeout,TimeUnit unit) {
		if (timeout != -1) {
			this.timeout=timeout;
			this.unit=unit;
		}
	}
	public long getTimeout() {
		return timeout;
	}
	public TimeUnit getUnit() {
		return unit;
	}
	
}
