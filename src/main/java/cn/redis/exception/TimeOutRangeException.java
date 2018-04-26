package cn.redis.exception;

public class TimeOutRangeException extends Exception {
	
	public TimeOutRangeException() {
		super("redis的到期数值的设置出错");
	}
	public TimeOutRangeException(long message) {
		super("redis的到期数值的设置出错"+message);
	}
}
