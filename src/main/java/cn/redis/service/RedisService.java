package cn.redis.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.redis.connection.DataType;

import cn.redis.model.redisreturn.RedisReturnValue;
import cn.redis.utils.RedisLifeCycle;

	/**
	 * ZXQ
	 * @author Administrator
	 *
	 */

public interface RedisService {
	
	/**
	 * 设置所选数据库index
	 * @param index
	 */
	void setDabaseIndex(int index);
	/**
	 * 获取所有redis的key
	 * @param index
	 * @return
	 */
	String getKeyByBaseNum(int index);
	/**
	 * 
	 * @param index
	 * @param ke 模糊
	 * @return
	 */
	Set<String> getKeyByVague(int index,String ke);
	/**
	 * 获取list数据
	 * @param key redis的key
	 * @return
	 */
	List<String> getListValue(String key);
	/**
	 * 获取String类型的数据
	 * @param key redis的key
	 * @return
	 */
	String getStringValue(String key);
	/**
	 * 获取map类型数据
	 * @param key redis的key
	 * @return
	 */
	Map<String,Object> getHashValue(String key);
	/**
	 * 获取set类型数据
	 * @param key redis的key
	 * @return
	 */
	Set<String> getSetValue(String key);
	/**
	 * 获取key值的数据类型
	 * @param key redis的key
	 * @return
	 */
	DataType getTypeByKey(int index, String key);
	/**
	 * 获取redis的value
	 * @param key redis的key
	 * @return
	 */
	RedisReturnValue getValueByKey(int index, String key)  throws Exception;
	/**
	 * 删除redis映射关系
	 * @param key redis的key
	 */
	void deleteValueByKey(int index, String key);
	/**
	 * 获取保留时间
	 * @param key redis的key
	 * @return
	 */
	long getExpireTime(String key);
	/**
	 * 
	 * @param key redis的key
	 * @param value
	 * @param start
	 * @param end
	 */
	public void setZSetValue(String key,String value, long start, RedisLifeCycle redisLifeCycle);
	/**
	 * 
	 * @param key redis的key
	 * @param listValue list的值
	 * @return
	 */
	public void setListValue(int index, String key, List listValue, RedisLifeCycle redisLifeCycle);
	/**
	 * 
	 * @param key redis的key
	 * @param stringListValue 右连存储string行的list数据
	 * @param redisLifeCycle 生命周期
	 */
	public void setListValue(String key, String stringListValue, RedisLifeCycle redisLifeCycle);
	/** 
	 * 
	 * @param key redis的key
	 * @param stringValue 存储string的值
	 * @return
	 */
	public void setStringValue(int index, String key, String stringValue, RedisLifeCycle redisLifeCycle);
	/**
	 * 
	 * @param key redis的key
	 * @param mapValue 存储map的值
	 * @return
	 */
	public void setHashMap(String key, Map mapValue, RedisLifeCycle redisLifeCycle);
	/**
	 * string类型放入指定key的map中
	 * @param key redis的key
	 * @param mapStringValue
	 * @param redisLifeCycle 生命周期
	 */
	public void setHashMapString(int index, String key, String mapkey, String mapStringValue, RedisLifeCycle redisLifeCycle);
	
	
	
}
