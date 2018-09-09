package cn.taskScheduled;

import cn.LinuxShell.CheckCDRNoService;
import cn.hws.bo.ZoneConfig;
import cn.redis.utils.RedisUtils;
import cn.util.httpUtil.HttpRestImpl;
import cn.util.httpUtil.dao.HttpRest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TaskScheduled {
    private static final Logger logger = LoggerFactory.getLogger(RedisUtils.class);

    @Resource
    private CheckCDRNoService checkCDRNoService;
// /hwservice/getZones
    public void repay() {
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
                logger.info(jsonObject1.getString("regionId"));
                stringBuffer.append(checkCDRNoService.checkByDateAndRegion(jsonObject1.getString("regionId"),yesterday)+"|");
                pdString.append("|");
            }
            logger.info(pdString.toString());
            List<String> listType = new ArrayList<String>();
            if (!stringBuffer.toString().equals(pdString.toString())){
                List<String> lostFile = Arrays.asList(stringBuffer.toString().split("\\|"));
                stringBuffer.delete(0,stringBuffer.length()).append("检查话单异常：下面是缺失的压缩文件开头的字符串\n");
                for(int i=0; i<lostFile.size(); i++) {
                    if("".equals(lostFile.get(i))) {
                        continue;
                    }
                    stringBuffer.append(lostFile.get(i)).append("\n");
                }
                getMappingType(httpRest, listType);
                stringBuffer.append("\n华为mapping映射不对应的：");
                for (String s1:listType) {
                    stringBuffer.append(s1+"\n");
                }
                /*if (listType.size() == 0) {
                    stringBuffer.append("/n华为mapping接口返回异常");
                }*/
                SendEmail.sendMail(stringBuffer);
            } else {
                stringBuffer.delete(0,stringBuffer.length()).append("检查话单正常!");
                getMappingType(httpRest, listType);
                stringBuffer.append("\n华为mapping映射不对应的：");
                for (String s1:listType) {
                    stringBuffer.append(s1+"\n");
                }
                SendEmail.sendMail(stringBuffer);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    private void getMappingType(HttpRest httpRest, List<String> listType) {
        String result = httpRest.getParamAsMap("http://10.144.242.110:28080/hwservice/getErrorMappingInfo",
                null, null);
        JSONObject jsonObject2 = JSON.parseObject(result);
        if (jsonObject2.get("statusCode").equals(800)) {
            List list = JSON.parseObject(jsonObject2.getString("returnObj"), List.class);
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                Map map = (Map) iterator.next();
                JSONObject jsonObject3 = JSON.parseObject(map.get("data").toString());
                listType.add(jsonObject3.getString("type"));
            }
        }
    }
}
