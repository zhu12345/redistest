package cn.redis.exception;

public class DatabaseIndexOutException extends Exception{
	public DatabaseIndexOutException() {
		super();
	}
	public DatabaseIndexOutException(int message) {
		super("数据库id传值错误:index="+message);
	}
}
