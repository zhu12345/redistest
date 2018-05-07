package cn.redis.controller.database;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.redis.connection.DataType;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.redis.model.http.ReturnCode;
import cn.redis.model.redisreturn.RedisReturnValue;
import cn.redis.service.RedisService;
import cn.redis.utils.RedisLifeCycle;
import cn.redis.utils.RedisUtils;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletResponse;


@Controller
public class SelectorDataBaseController {
	
    private static final Logger logger = LoggerFactory.getLogger(RedisUtils.class);
    @Resource
    private RedisService redisService;

    /**
     * 根据redis数据库id查出所有的key, 并构造成树
     * @param index 数据库id
     * @return 返回string类型
     * @throws Exception
     */
    @RequestMapping(value = "/select/Base", method = RequestMethod.POST)
    @ResponseBody
    public String selectKeybyBaseNum(@RequestParam(value = "index")int index) {
    	return redisService.getKeyByBaseNum(index);
    }
    /**
     * 根据redis数据库id查出所有的key, 并构造成树
     * @param index 数据库id
     * @return 返回string类型
     * @throws Exception
     */
    @RequestMapping(value = "/select/VagueKey", method = RequestMethod.GET)
    @ResponseBody
    public Set<String> selectKeybyVague(@RequestParam(value = "index")int index, @RequestParam(value = "ke")String ke) {
    	if (ke != null && !ke.equals(""))
    		return redisService.getKeyByVague(index, ke);
    	return null;
    }

    /**
     * 根据redis数据库id和key查询value
     * @param index 数据库id
     * @param key redis的key
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/select/value", method = RequestMethod.GET)
    @ResponseBody
    public RedisReturnValue selectValue(@RequestParam(value = "index")int index, 
    		@RequestParam(value = "key")String key) {
		if (key != null) {
			try {
				RedisReturnValue redisReturnValue = redisService.getValueByKey(index, key);
				if(redisReturnValue != null) {
					redisReturnValue.setReturnCode(ReturnCode.IS_SUCCESS);
				} else {
					redisReturnValue = new RedisReturnValue(null, null, null, null, null, 0, ReturnCode.IS_NULL);
				}
	            return redisReturnValue;
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		logger.debug("key is null or key length is zero!");
        return new RedisReturnValue(null, null, null, null, DataType.NONE, index,
                ReturnCode.IS_FAIL);
    }

    /**
     * 根据redis数据库id和key删除数据
     * @param index 数据库id
     * @param key redis的key
     * @return ReturnCode返回对象
     * @throws Exception
     */
    @RequestMapping(value = "/delete/value", method = RequestMethod.GET)
    @ResponseBody
    public ReturnCode deleteValuebyKey(@RequestParam(value = "index")int index, 
    		@RequestParam(value = "key")String key) {
        try {
            redisService.deleteValueByKey(index, key);
            return ReturnCode.IS_SUCCESS;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ReturnCode.IS_FAIL;
    }

    @RequestMapping(value = "/delete/valuesbykeys", method = RequestMethod.GET)
    @ResponseBody
    public ReturnCode deleteValuebyKeys(@RequestParam(value = "index")int index,
                                       @RequestParam(value = "keyVague")String keyVague) {
        try {
            redisService.deleteValueByKeys(index, keyVague);
            return ReturnCode.IS_SUCCESS;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ReturnCode.IS_FAIL;
    }
    /**
     * 根据redis数据库id和key和stringValue保存数据
     * @param index 数据库id
     * @param key redis的key
     * @param stringValue 存储的string类型数据
     * @param timeout 设定存储时间
     * @return ReturnCode返回对象
     * @throws Exception
     */
    @RequestMapping(value = "/save/stringValue", method = RequestMethod.POST)
    @ResponseBody
    public ReturnCode savestringValuebyKey(@RequestParam(value = "index")int index,
    		@RequestParam(value = "key")String key,
    		@RequestParam("stringValue")String stringValue,
    		@RequestParam(value = "timeout")long timeout) {
    	if ((key != null) && (stringValue != null)) {
	        try {
	            RedisLifeCycle redisLifeCycle = new RedisLifeCycle(timeout,
	                    TimeUnit.MILLISECONDS);
	            redisService.setStringValue(index, key, stringValue, redisLifeCycle);
	            return ReturnCode.IS_SUCCESS;
	        } catch (Exception e) {
	            logger.error(e.getMessage(), e);
	        }
    	}
        return ReturnCode.IS_FAIL;
    }

    /**
     * 根据redis数据库id和key和stringValue保存数据
     * @param index 数据库id
     * @param key redis的key
     * @param listValue 存储的list类型数据
     * @param timeout 设定存储时间 
     * @return ReturnCode返回对象
     * @throws Exception
     */
    @RequestMapping(value = "/save/listValue", method = RequestMethod.POST)
    @ResponseBody
    public ReturnCode saveListValuebyKey(@RequestParam(value = "index")int index, 
    @RequestParam(value = "key")String key, 
    @RequestParam("listValue")List listValue, 
    @RequestParam(value = "timeout")long timeout, 
    HttpServletResponse response) {
    	if ((key != null) && (listValue != null)) {
    		try {
                RedisLifeCycle redisLifeCycle = new RedisLifeCycle(timeout,
                        TimeUnit.MILLISECONDS);
                redisService.setListValue(index, key, listValue, redisLifeCycle);
                return ReturnCode.IS_SUCCESS;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
    	}
         return ReturnCode.IS_FAIL;
    }

    /**
     * 保存map数据类型
     * @param index 数据库id
     * @param key redis的key
     * @param mapKey 存储map的key键
     * @param mapValue 存储map的key值
     * @param timeout 设定存储时间
     * @return ReturnCode返回对象
     * @throws Exception
     */
    @RequestMapping(value = "/save/mapValue", method = RequestMethod.POST)
    @ResponseBody
    public ReturnCode saveMapValuebyKey(@RequestParam(value = "index", required = true)int index, 
    		@RequestParam(value = "key")String key, 
    		@RequestParam("mapKey")String mapKey, 
    		@RequestParam("mapValue")String mapValue, 
    		@RequestParam(value = "timeout")long timeout) 
    {
    	if ((key != null) && (mapKey != null) && (mapValue != null)) {
    		try {
                RedisLifeCycle redisLifeCycle = new RedisLifeCycle(timeout,
                        TimeUnit.MILLISECONDS);
                redisService.setHashMapString(index, key, mapKey, mapValue, redisLifeCycle);
                return ReturnCode.IS_SUCCESS;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
    	}
        return ReturnCode.IS_FAIL;
    }

    /**
     *查询key的数据存储的结构类型
     * @param index 数据库id
     * @param key redis的key
     * @return
     */
    @RequestMapping(value = "/queryValueType", method = RequestMethod.POST)
    @ResponseBody
    public DataType queryValueType(@RequestParam(value = "index") int index, 
    		@RequestParam(value = "key") String key) {
    	if (key != null) {
    		try {
                return redisService.getTypeByKey(index, key);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
    	}
        return null;
    }

    /**
     * 测试JSON-easyUI数据
     * @return
     */
    @RequestMapping(value = "/createJson", method = RequestMethod.POST)
    @ResponseBody
    public String createJson() {
        return "{\"total\":28,\"rows\":[ 	{\"productid\":\"FI-SW-01\",\"productname\":\"Koi\",\"unitcost\":10.00,\"status\":\"P\",\"listprice\":36.50,\"attr1\":\"Large\",\"itemid\":\"EST-1\"}, 	{\"productid\":\"K9-DL-01\",\"productname\":\"Dalmation\",\"unitcost\":12.00,\"status\":\"P\",\"listprice\":18.50,\"attr1\":\"Spotted Adult Female\",\"itemid\":\"EST-10\"}, 	{\"productid\":\"RP-SN-01\",\"productname\":\"Rattlesnake\",\"unitcost\":12.00,\"status\":\"P\",\"listprice\":38.50,\"attr1\":\"Venomless\",\"itemid\":\"EST-11\"}, 	{\"productid\":\"RP-SN-01\",\"productname\":\"Rattlesnake\",\"unitcost\":12.00,\"status\":\"P\",\"listprice\":26.50,\"attr1\":\"Rattleless\",\"itemid\":\"EST-12\"}, 	{\"productid\":\"RP-LI-02\",\"productname\":\"Iguana\",\"unitcost\":12.00,\"status\":\"P\",\"listprice\":35.50,\"attr1\":\"Green Adult\",\"itemid\":\"EST-13\"}, 	{\"productid\":\"FL-DSH-01\",\"productname\":\"Manx\",\"unitcost\":12.00,\"status\":\"P\",\"listprice\":158.50,\"attr1\":\"Tailless\",\"itemid\":\"EST-14\"}, 	{\"productid\":\"FL-DSH-01\",\"productname\":\"Manx\",\"unitcost\":12.00,\"status\":\"P\",\"listprice\":83.50,\"attr1\":\"With tail\",\"itemid\":\"EST-15\"}, 	{\"productid\":\"FL-DLH-02\",\"productname\":\"Persian\",\"unitcost\":12.00,\"status\":\"P\",\"listprice\":23.50,\"attr1\":\"Adult Female\",\"itemid\":\"EST-16\"}, 	{\"productid\":\"FL-DLH-02\",\"productname\":\"Persian\",\"unitcost\":12.00,\"status\":\"P\",\"listprice\":89.50,\"attr1\":\"Adult Male\",\"itemid\":\"EST-17\"}, 	{\"productid\":\"AV-CB-01\",\"productname\":\"Amazon Parrot\",\"unitcost\":92.00,\"status\":\"P\",\"listprice\":63.50,\"attr1\":\"Adult Male\",\"itemid\":\"EST-18\"} ]}";
    }
}
