package cn.redis.exception;

public class RedisKeyNUllException extends Exception {
	public RedisKeyNUllException() {
		super("redis key is null!");
	}
}
