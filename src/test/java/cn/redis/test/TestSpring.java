package cn.redis.test;

import cn.LinuxShell.CheckCDRNoService;
import cn.hws.bo.ZoneConfig;
import cn.taskScheduled.SendEmail;
import cn.util.httpUtil.HttpRestImpl;
import cn.util.httpUtil.dao.HttpRest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试  
@ContextConfiguration("classpath:applicationContext.xml")
public class TestSpring {

    @Resource
    private CheckCDRNoService checkCDRNoService;

    @Test
    public void testHost() {
        /*{
            try {
                HttpRest httpRest = new HttpRestImpl();
                String s = httpRest.getParamAsMap("http://10.144.242.126/hwservice/getZones",
                        null, null);
                JSONObject jsonObject = JSON.parseObject(s);
                List zoneConfigs = JSON.parseObject(jsonObject.getString("returnObj"), java.util.List.class);
                System.out.println(JSON.toJSONString(zoneConfigs));
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, -1);
                String yesterday = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
                StringBuffer stringBuffer = new StringBuffer();
                StringBuffer pdString = new StringBuffer();
                for (Object o:zoneConfigs) {
                    JSONObject jsonObject1 = JSON.parseObject(o.toString());
//                    logger.info(jsonObject1.getString("regionId"));
                    stringBuffer.append(checkCDRNoService.checkByDateAndRegion(jsonObject1.getString("regionId"),yesterday)+"|");
                    pdString.append("|");
                }
//                logger.info(pdString.toString());
                if (!stringBuffer.toString().equals(pdString.toString())){
                    List<String> lostFile = Arrays.asList(stringBuffer.toString().split("\\|"));
                    stringBuffer.delete(0,stringBuffer.length()).append("检查话单异常：下面是缺失的压缩文件开头的字符串\n");
                    for(int i=0; i<lostFile.size(); i++) {
                        if("".equals(lostFile.get(i))) {
                            continue;
                        }
                        stringBuffer.append(lostFile.get(i)).append("\n");
                    }

                    SendEmail.sendMail(stringBuffer);
                } else {
                    stringBuffer.delete(0,stringBuffer.length()).append("检查话单正常!");
                    SendEmail.sendMail(stringBuffer);
                }
            } catch (Exception e) {
//                logger.error(e.getMessage(), e);
            }
        }*/
    }
    
}
