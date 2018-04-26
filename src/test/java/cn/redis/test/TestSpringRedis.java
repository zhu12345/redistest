package cn.redis.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.redis.service.RedisService;
import cn.vmsservice.bo.Host;

@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试  
@ContextConfiguration("classpath:applicationContext.xml")
public class TestSpringRedis {
	/*@Resource(name="stringRedisTemplate")
	private RedisTemplate redisTemplate;*/
	/*@Resource
	private RedisService redisService;
    @Test  
    public void testSpringRedis() {
    	 
        //stringRedisTemplate的操作  
        // String读写  
        redisTemplate.delete("mykey");  
        redisTemplate.opsForValue().set("mykey", "skyLine");  
        System.out.println(redisTemplate.opsForValue().get("mykey"));  
        System.out.println("---------------");  
  
        // List读写  
        redisTemplate.delete("myList");  
        redisTemplate.opsForList().rightPush("myList", "T");  
        redisTemplate.opsForList().rightPush("myList", "L");  
        redisTemplate.opsForList().leftPush("myList", "A");  
        List<String> listCache = redisTemplate.opsForList().range(  
                "myList", 0, -1);  
        for (String s : listCache) {  
            System.out.println(s);  
        }  
        System.out.println("---------------");  
  
        // Set读写  
        redisTemplate.delete("mySet");  
        redisTemplate.opsForSet().add("mySet", "A");  
        redisTemplate.opsForSet().add("mySet", "B");  
        redisTemplate.opsForSet().add("mySet", "C");  
        Set<String> setCache = redisTemplate.opsForSet().members(  
                "mySet");  
        for (String s : setCache) {  
            System.out.println(s);  
        }  
        System.out.println("---------------");  
  
        // Hash读写  
        redisTemplate.delete("myHash");  
        redisTemplate.opsForHash().put("myHash", "BJ", "北京");  
        redisTemplate.opsForHash().put("myHash", "SH", "上海");  
        redisTemplate.opsForHash().put("myHash", "HN", "河南");  
        Map<String, String> hashCache = redisTemplate.opsForHash()  
                .entries("myHash");  
        for (Map.Entry entry : hashCache.entrySet()) {  
            System.out.println(entry.getKey() + " - " + entry.getValue());  
        }  
        System.out.println("---------------");  
    	
    	
    	Set s = redisTemplate.keys("*");
		Iterator it = s.iterator();
		Set<String> keygreps = new HashSet<String>();
		long startTime = System.currentTimeMillis();
		while (it.hasNext()) {
			String key = (String) it.next();
			String[] keyarray = key.split(":");
			StringBuilder sb = new StringBuilder();
			for(int i = 0;i < keyarray.length-1;i++){				
					sb.append(keyarray[i]+":");					
			}
			
			keygreps.add(sb.toString());			
		}
		//System.out.println(keygreps.size());
		Iterator itkeygrep = keygreps.iterator();
		while (itkeygrep.hasNext()) {
			String keygrep = (String)itkeygrep.next();
			//System.out.println(keygrep);
		}
		long endTime = System.currentTimeMillis();
		System.out.println(endTime-startTime);
		
		
		while (it.hasNext()) {
			String key = (String) it.next();
			String value = (String)redisTemplate.opsForValue().get(key);
			System.out.println(key+"----value:" + value);
			List<String> value1 = redisTemplate.opsForList().range(key, 0, -1);
			for(String a:value1){
				System.out.println(key+"----value:"+a);
			}
			Set<String> setCache = redisTemplate.opsForSet().members(key);  
	        for (String sc : setCache) {  
	            System.out.println(key+"----value:"+sc);  
	        } 
	        Map<String, String> hashCache = redisTemplate.opsForHash()  
	                .entries(key);  
	        for (Map.Entry entry : hashCache.entrySet()) {  
	            System.out.println(entry.getKey() + " ----value: " + entry.getValue());  
	        }
		}
		
    	
    	
    	String s = redisService.getKeyByBaseNum(0);
		Iterator it = s.iterator();
		Set<String> keygreps = new HashSet<String>();
		long startTime = System.currentTimeMillis();
		while (it.hasNext()) {
			String key = (String) it.next();
			String[] keyarray = key.split(":");
			StringBuilder sb = new StringBuilder();
			for(int i = 0;i < keyarray.length-1;i++){				
					sb.append(keyarray[i]+":");					
			}
			
			keygreps.add(sb.toString());			
		}
		//System.out.println(keygreps.size());
		Iterator itkeygrep = keygreps.iterator();
		while (itkeygrep.hasNext()) {
			String keygrep = (String)itkeygrep.next();
			//System.out.println(keygrep);
		}
		long endTime = System.currentTimeMillis();
		System.out.println(endTime-startTime);
		
    	List<String> lists = new ArrayList<String>();
    	lists.add("0060dfa73dfa49ef817987d93e49be4d");

    	for (String listString : lists) {
    		Set<String> s = redisService.getKeyByVague(2, listString);
    		for (String key:s) {
    			System.out.println(key);
    		}
//    		redisService.deleteValueByKey(2, listString);
    		System.out.println("finsh............");
    		try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    }  */
	@Resource
	private RedisService redisService;
    @Resource
    private cn.vmsservice.dao.HostMapper hostMapper;
    @Test
    public void testHost(){
    	redisService.getKeyByVague(2, "aaaa");
    	/*Host host = hostMapper.selectByPrimaryKey("0490f00af95f4343854a1a309440698d");
    	System.out.println(host);
    	host.setVmPassword("nihaoa");
    	hostMapper.updateByPrimaryKey(host);*/
    	Host host2 = new Host();
    	host2.setId("0490f00af95f4343854a1a309440698d");
    	host2.setVmPassword("updateByPrimaryKey123");
    	hostMapper.updateByPrimaryKeySelective(host2);
    }
    
}
