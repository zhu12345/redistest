package cn.redis.model.redisreturn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.redis.model.http.ReturnCode;

import org.springframework.data.redis.connection.DataType;

/**
 * redis查询的结果对象构造
 * @author Administrator
 *
 */
public class RedisReturnValue {
	private String stringValue;
	private ReturnCode returnCode;
	private List<String> list = new ArrayList<String>();
	private Set<String> set = new HashSet<String>();
	private Map<String, Object> map = new HashMap<String, Object>();
	private DataType dateType;
	private long expiretime;
	public RedisReturnValue(String stringValue, List<String> list, Set<String> set, Map<String, Object> map, DataType dateType) {
		if (dateType != null) {
			if(dateType.equals(DataType.STRING)){
				this.stringValue = stringValue;
			}
			if(dateType.equals(DataType.LIST)){
				this.list = list;
			}
			if(dateType.equals(DataType.SET) || dateType.equals(DataType.ZSET)){
				this.set = set;
			}
			if(dateType.equals(DataType.HASH)){
				this.map = map;
			}
		}
		this.dateType = dateType;
	}
	public RedisReturnValue(String stringValue, List<String> list, 
			Set<String> set, Map<String, Object> map, 
			DataType dateType, long expireTime) {
		this(stringValue, list, set, map, dateType);
		this.expiretime = expireTime;
	}
	public RedisReturnValue(String stringValue, List<String> list, Set<String> set, Map<String, Object> map, DataType dateType, long expireTime, ReturnCode returnCode) {
		this(stringValue, list, set, map, dateType, expireTime);
		this.returnCode = returnCode;
	}
	
	public ReturnCode getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(ReturnCode returnCode) {
		this.returnCode = returnCode;
	}
	public String getStringValue() {
		return stringValue;
	}
	public List<String> getList() {
		return list;
	}
	public Set<String> getSet() {
		return set;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public DataType getDateType() {
		return dateType;
	}
	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	public void setSet(Set<String> set) {
		this.set = set;
	}
	public void setDateType(DataType dateType) {
		this.dateType = dateType;
	}
	
	public long getExpiretime() {
		return expiretime;
	}
	public void setExpiretime(long expiretime) {
		this.expiretime = expiretime;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	@Override
	public String toString() {
		return super.toString();
	}
}
