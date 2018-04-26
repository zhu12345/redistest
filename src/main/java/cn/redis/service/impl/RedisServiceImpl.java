package cn.redis.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.DataType;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.redis.service.RedisService;
import cn.redis.exception.DatabaseIndexOutException;
import cn.redis.exception.RedisKeyNUllException;
import cn.redis.exception.TimeOutRangeException;
import cn.redis.model.redisreturn.RedisReturnValue;
import cn.redis.utils.RedisKey;
import cn.redis.utils.RedisLifeCycle;
import cn.redis.utils.RedisUtils;
import cn.redis.utils.tree.Tree;
import cn.redis.utils.tree.TreeBulid;
@Service("redisService")
public class RedisServiceImpl implements RedisService {
	
	private static final Logger logger = LoggerFactory.getLogger(RedisUtils.class);
	
	@Resource
	private RedisUtils redisUtils;
	private RedisKey keyUtils = new RedisKey();
	@Override
	public String getKeyByBaseNum(int index) {	
		try {
			this.checkDatabaseIndex(index);
			redisUtils.setDatabase(index);
			List<String> listTree = new ArrayList<String>();
			listTree.addAll(redisUtils.getKeys());
			List<Tree> listTreeBuilded = new ArrayList<Tree>();
			listTreeBuilded = TreeBulid.createTree(listTree);
			return JSON.toJSONString(listTreeBuilded).replace("lTree",
					"children");
		} catch (DatabaseIndexOutException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
		
	}
	
	@Override
	public List<String> getListValue(String key) {		
		keyUtils.setKey(key);
		return redisUtils.getListValue(keyUtils);
	}

	@Override
	public String getStringValue(String key) {
		keyUtils.setKey(key);
		return redisUtils.getStringValue(keyUtils);
	}

	@Override
	public Map<String, Object> getHashValue(String key) {
		keyUtils.setKey(key);
		return redisUtils.getHashValue(keyUtils);
	}

	@Override
	public DataType getTypeByKey(int index, String key) {
		try {
			this.checkDatabaseIndex(index);
			redisUtils.setDatabase(index);
			this.checkRedisKey(key);
			keyUtils.setKey(key);
			return redisUtils.type(keyUtils);
		} catch (DatabaseIndexOutException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	@Override
	public Set<String> getSetValue(String key) {
		keyUtils.setKey(key);
		return redisUtils.getSetValue(keyUtils);
	}
	public Set getZSetValue(String key, long start, long end) {
		keyUtils.setKey(key);
		return redisUtils.getZSet(keyUtils, start, end);
	}
	@Override
	public RedisReturnValue getValueByKey(int index, String key)  {
		try {
			this.checkDatabaseIndex(index);
			redisUtils.setDatabase(index);
			this.checkRedisKey(key);
			keyUtils.setKey(key);
			RedisReturnValue redisReturnValue = null;
			DataType dateType= this.getTypeByKey(index, key);
			long expireTime = this.getExpireTime(key);
			if (dateType != null) {
				if(dateType.equals(DataType.STRING)) {
					redisReturnValue = new RedisReturnValue(this.getStringValue(key), null, null, null, dateType, expireTime);
				}else
				if(dateType.equals(DataType.LIST)) {
					redisReturnValue = new RedisReturnValue(null, this.getListValue(key), null, null, dateType, expireTime);
				}else
				if(dateType.equals(DataType.SET)) {
					redisReturnValue = new RedisReturnValue(null, null, this.getSetValue(key), null, dateType, expireTime);
				}else
				if(dateType.equals(DataType.ZSET)) {
					redisReturnValue = new RedisReturnValue(null, null, this.getZSetValue(key, 0, 0), null, dateType, expireTime);
				}else
				if(dateType.equals(DataType.HASH)) {
					redisReturnValue = new RedisReturnValue(null, null, null, this.getHashValue(key), dateType, expireTime);
				} else {	
					try {
						redisReturnValue = new RedisReturnValue(null, null, null, null, dateType, 0);
						throw new Exception("none");
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				}
			}
			return redisReturnValue;
		} catch (DatabaseIndexOutException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	@Override
	public void deleteValueByKey(int index, String key) {
		try {
			this.checkDatabaseIndex(index);
			redisUtils.setDatabase(index);
			this.checkRedisKey(key);
			keyUtils.setKey(key);
			redisUtils.deletevalue(keyUtils);
		} catch (DatabaseIndexOutException e) {
			logger.error(e.getMessage(), e);
		} catch (RedisKeyNUllException e) {
			logger.error(e.getMessage(), e);
		}
	}
	@Override
	public void setDabaseIndex(int index) {
		redisUtils.setDatabase(index);		
	}

	@Override
	public long getExpireTime(String key) {
		keyUtils.setKey(key);
		return redisUtils.getExpire(keyUtils);		
	}

	@Override
	public void setZSetValue(String key, String value, long start,RedisLifeCycle redisLifeCycle ) {
		keyUtils.setKey(key);
		redisUtils.setZSetValue(keyUtils, value, start, redisLifeCycle);
	}

	@Override
	public void setListValue(int index, String key, List listValue, RedisLifeCycle redisLifeCycle) {
		try {
			this.checkDatabaseIndex(index);
			redisUtils.setDatabase(index);
			this.checkRedisKey(key);
			keyUtils.setKey(key);
			this.checkTimeOut(redisLifeCycle.getTimeout());
			if(redisLifeCycle!=null){
				redisUtils.setListValue(keyUtils, listValue, redisLifeCycle);
			}else{
				redisUtils.setListValue(keyUtils, listValue);
			}
		} catch (DatabaseIndexOutException e) {
			logger.error(e.getMessage(), e);
		} catch (RedisKeyNUllException e) {
			logger.error(e.getMessage(), e);
		} catch (TimeOutRangeException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
	}
	
	@Override
	public void setListValue(String key, String stringListValue,
			RedisLifeCycle redisLifeCycle) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setStringValue(int index, String key, String stringValue, RedisLifeCycle redisLifeCycle) {
		try {
			this.checkDatabaseIndex(index);
			redisUtils.setDatabase(index);
			this.checkRedisKey(key);
			keyUtils.setKey(key);
			this.checkTimeOut(redisLifeCycle.getTimeout());
			if(redisLifeCycle!=null){
				redisUtils.setStringValue(keyUtils, stringValue, redisLifeCycle);
			}else{
				redisUtils.setStringValue(keyUtils, stringValue);
			}
		} catch (DatabaseIndexOutException e) {
			logger.error(e.getMessage(), e);
		} catch (RedisKeyNUllException e) {
			logger.error(e.getMessage(), e);
		} catch (TimeOutRangeException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public void setHashMap(String key, Map mapValue, RedisLifeCycle redisLifeCycle) {
		keyUtils.setKey(key);
		redisUtils.setHashValue(keyUtils, mapValue, redisLifeCycle);
	}

	@Override
	public void setHashMapString(int index, String key, String mapkey,
			String mapStringValue, RedisLifeCycle redisLifeCycle) {
		try {
			this.checkDatabaseIndex(index);
			redisUtils.setDatabase(index);
			this.checkRedisKey(key);
			keyUtils.setKey(key);
			this.checkTimeOut(redisLifeCycle.getTimeout());
			Map map = new HashMap<String, String>();
			map.put(mapkey, mapStringValue);
			if(redisLifeCycle!=null){
				redisUtils.setHashValue(keyUtils, map, redisLifeCycle);
			}else{
				redisUtils.setHashValue(keyUtils, map);
			}
		} catch (DatabaseIndexOutException e) {
			logger.error(e.getMessage(), e);
		} catch (RedisKeyNUllException e) {
			logger.error(e.getMessage(), e);
		} catch (TimeOutRangeException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
	}
	
	/**
	 * 核查数据库索引值
	 * @param index
	 * @throws DatabaseIndexOutException
	 */
	private void checkDatabaseIndex(int index) throws DatabaseIndexOutException {
		if (!((index >= 0) && (index <= 15))) {
            throw new DatabaseIndexOutException(index);
        }
	}
	
	/**
	 * 核查redis某键到期值
	 * @param timeout
	 * @throws TimeOutRangeException
	 */
	private void checkTimeOut(long timeout) throws TimeOutRangeException {
		if (timeout != -1 && timeout < 0) {
			throw new TimeOutRangeException(timeout);
		}
	}
	
	/**
	 * 核查键是否为空
	 * @param key
	 * @throws RedisKeyNUllException
	 */
	private void checkRedisKey(String key) throws RedisKeyNUllException{
		if(key == null) {
			throw new RedisKeyNUllException();
		}
	}

	public Set<String> getKeyByVague(int index, String ke) {
			try {
				this.checkDatabaseIndex(index);
				redisUtils.setDatabase(index);
				return redisUtils.vagueQueryKeys(ke);
			} catch (DatabaseIndexOutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return null;
	}
}
