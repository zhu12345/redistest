package cn.redis.utils;


import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Service
public class RedisUtils {
	private static final Logger logger = LoggerFactory.getLogger(RedisUtils.class);
	@Resource(name="stringRedisTemplate")
	private RedisTemplate stringRedisTemplate;
	@Resource
	private JedisConnectionFactory jedisConnectionFactory;
	public RedisUtils() {}
	/**
	 * 设置所选数据库
	 * @param dataIndex
	 */
	public void setDatabase(int dbIndex) {
		logger.info("select database:"+dbIndex);
		jedisConnectionFactory.setDatabase(dbIndex);
		stringRedisTemplate.setConnectionFactory(jedisConnectionFactory);
		
	}
	/**
	 * 存储string类型value
	 * @param key
	 * @param redisvalue
	 */
	public void setStringValue(RedisKey key, String redisvalue) {
		if(key != null &&  key.isValid()) {
			try {
				this.stringRedisTemplate.opsForValue().set(key.getKey(), redisvalue);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
	/**
	 * 存储string类型
	 * @param key
	 * @param redisvalue
	 * @param redisLifeCycle
	 */
	public void setStringValue(RedisKey key, String redisvalue, RedisLifeCycle redisLifeCycle) {
		if(key != null && key.isValid()) {
			try {
				this.stringRedisTemplate.opsForValue().set(key.getKey(), redisvalue, redisLifeCycle.getTimeout(), redisLifeCycle.getUnit());
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
	/**
	 * 根据key确定string的key
	 * @param key
	 * @return
	 */
	public String getStringValue(RedisKey key) {
		if(key != null && key.isValid()) {
			try {
				return (String)this.stringRedisTemplate.opsForValue().get(key.getKey());
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				return null;
			}
		}
		return null;
	}
	
	public void setHashValue(RedisKey key, Map<String, Object> map) {
		if(key != null && key.isValid()) {
			try {
				this.setHashValue(key, map, (RedisLifeCycle)null);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
	public void setHashValue(RedisKey key, String mapKey, String mapValue) {
		try {
			this.stringRedisTemplate.opsForHash().put(key.getKey(), mapKey, mapValue);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	public void setHashValue(RedisKey key, Map<String, Object> map, RedisLifeCycle redisLifeCycle) {
		if(key != null && key.isValid() && !map.isEmpty()) {
			try {
				this.stringRedisTemplate.opsForHash().putAll(key.getKey(), map);
				if(!redisLifeCycle.equals(null))
					this.stringRedisTemplate.expire(key.getKey(), redisLifeCycle.getTimeout(), redisLifeCycle.getUnit());
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
	public Map<String, Object> getHashValue(RedisKey key) {
		if(key != null && key.isValid()) {
			try {
				return this.stringRedisTemplate.opsForHash().entries(key.getKey());
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		return null;
	}
	/**
	 * 存储list类型的key-l值
	 * @param key
	 * @param l
	 */
	public void setListValue(RedisKey key, List<String> l) {
		if (key != null && key.isValid() && !l.isEmpty()) {
			this.setListValue(key, l, (RedisLifeCycle)null);
		}
	}
	/**
	 * 设置key的stringValue值
	 * @param key
	 * @param stringValue
	 */
	public void setListValue(RedisKey key, String stringValue) {
		if(key != null && key.isValid()) {
			this.stringRedisTemplate.opsForList().rightPush(key.getKey(), stringValue);
		}
	}
	/**
	 * 设置list类型的value值
	 * @param key
	 * @param list
	 * @param redisLifeCycle
	 */
	public void setListValue(RedisKey key, List<String> list, RedisLifeCycle redisLifeCycle) {
		if(key != null && key.isValid()) {
			try {
				 for(String s:list){
					this.setListValue(key, s);
				 }
				 if( !(redisLifeCycle == null) )
					 this.expire(key, redisLifeCycle.getTimeout(), redisLifeCycle.getUnit());
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
	/**
	 * 获取key的值
	 * @param key
	 * @return
	 */
	public List<String> getListValue(RedisKey key) {
		if(key != null && key.isValid()) {
			try {
				return this.stringRedisTemplate.opsForList().range(key.getKey(), 0, -1);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}		
		return null;		
	}
	/**
	 * 设置key为set类型的value值
	 * @param key
	 * @param value
	 */
	public void setSetValue(RedisKey key, String value) {
		this.setSetValue(key, value, (RedisLifeCycle)null);
	}
	/**
	 * 设置某key的值，生命周期
	 * @param key
	 * @param value
	 * @param redisLifeCycle
	 */
	public void setSetValue(RedisKey key, String value, RedisLifeCycle redisLifeCycle) {
		if(key != null && key.isValid()) {
			try {
				this.stringRedisTemplate.opsForSet().add(key.getKey(), value);
				if(!redisLifeCycle.equals(null))
					this.stringRedisTemplate.expire(key.getKey(), redisLifeCycle.getTimeout(), redisLifeCycle.getUnit());
			}catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
	/**
	 * 
	 * @param key
	 * @param value
	 * @param redisLifeCycle
	 */
	public void setZSetValue(RedisKey key, String value,double score, RedisLifeCycle redisLifeCycle) {
		if(key != null && key.isValid()) {
			try {
				this.stringRedisTemplate.opsForZSet().add(key, value, score);
				if(!redisLifeCycle.equals(null))
					this.stringRedisTemplate.expire(key.getKey(), redisLifeCycle.getTimeout(), redisLifeCycle.getUnit());
			}catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
	
	public  Set getZSet(RedisKey key, long start, long end) {
		if(key != null && key.isValid()) {
			try {
				return this.stringRedisTemplate.opsForZSet().rangeWithScores(key, start, end);
			}catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		return null;
	}
	
	/**
	 * 设置某key的值
	 * @param key
	 * @param set
	 */
	public void setSetValue(RedisKey key, Set<String> set) {
		Iterator it = set.iterator();
		while (it.hasNext()) {
			String setValue = (String)it.next();
			this.setSetValue(key, setValue);
		}
	}
	/**
	 * 获取set的值
	 * @param key
	 * @return
	 */
	public Set<String> getSetValue(RedisKey key) {
		if(key != null && key.isValid()) {
			try {
				return this.stringRedisTemplate.opsForSet().members(key.getKey());
			}catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		return null;		
	}
	/**
	 * 删除key-value
	 * @param key
	 */
	public void deletevalue(RedisKey key) {
		if(key != null && key.isValid()) {
			try {
				this.stringRedisTemplate.delete(key.getKey());
			}catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
	/**
	 * 设置key生存时间
	 * @param key
	 * @param timeout
	 * @param unit
	 * @return
	 */
	public boolean expire(RedisKey key, long timeout, TimeUnit unit) {
		if(key != null && key.isValid()) {
			try {
				return this.stringRedisTemplate.expire(key.getKey(), timeout, unit);
			}catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		return false;	
	}
	/**
	 * 获取保留时间
	 * @param key
	 * @return
	 */
	public long getExpire(RedisKey key){
		if(key != null && key.isValid()) {
			try {
				return this.stringRedisTemplate.getExpire(key.getKey(), TimeUnit.MILLISECONDS);
			}catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		return 0;
	}
	/**
	 * 获取所有的key
	 * @return
	 */
	public Set<String> getKeys() {
		try {
			return stringRedisTemplate.keys("*");
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	/**
	 * 
	 * @param ke
	 * @return
	 */
	public Set<String> vagueQueryKeys(String ke) {
		try {
			return stringRedisTemplate.keys("*"+ke+"*");
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	/**
	 * 根据key确定value存储类型
	 * @param key
	 * @return
	 */
	public DataType type(RedisKey key) {		
		if(key != null && key.isValid()) {
			try {
				return stringRedisTemplate.type(key.getKey());
			}catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		return null;
	}
	
}
