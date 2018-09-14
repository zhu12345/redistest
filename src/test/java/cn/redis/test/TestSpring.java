package cn.redis.test;

import cn.hwsservice.bo.AccountMeta;
import cn.hwsservice.dao.AccountMetaMapper;
import cn.taskScheduled.TaskScheduled;
import cn.vmsservice.bo.Host;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试  
@ContextConfiguration("classpath:applicationContext.xml")
public class TestSpring {

    @Resource
    private TaskScheduled taskScheduled;



    @Test
    public void testHost() {
//        taskScheduled.repay();
    }
    
}
