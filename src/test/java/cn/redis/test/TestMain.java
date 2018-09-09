package cn.redis.test;

import cn.hws.bo.ZoneConfig;
import cn.temp.GetVMMsg;
import cn.util.httpUtil.HttpRestImpl;
import cn.util.httpUtil.dao.HttpRest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TestMain {
    public static void main(String[] args) {
        /*HttpRest httpRest = new HttpRestImpl();
        String s = httpRest.getParamAsMap("http://10.144.242.126/hwservice/getZones",
                null, null);
        JSONObject jsonObject = JSON.parseObject(s);
        List zoneConfigs = JSON.parseObject(jsonObject.getString("returnObj"), java.util.List.class);
        System.out.println(JSON.toJSONString(zoneConfigs));
        for (Object o:zoneConfigs) {
            JSONObject jsonObject1 = JSON.parseObject(o.toString());
            System.out.println(jsonObject1.getString("regionId"));
        }*/
        HttpRest httpRest = new HttpRestImpl();
        /*List<String> listType = new ArrayList<String>();
        JSONObject jsonObject2 = JSON.parseObject("{\n" +
                "    \"statusCode\":800,\n" +
                "    \"returnObj\":\"[{\"data\":{\"count\":1,\"type\":\"c2.4xlarge.redhat\"}},{\"data\":{\"count\":3,\"type\":\"h1.2xlarge.2.linux\"}},{\"data\":{\"count\":4,\"type\":\"g2.2xlarge.8.win\"}},{\"data\":{\"count\":46,\"type\":\"19_safecq\"}},{\"data\":{\"count\":2,\"type\":\"s1.xlarge.suse\"}},{\"data\":{\"count\":65,\"type\":\"19_bgp\"}},{\"data\":{\"count\":1,\"type\":\"s3.xlarge.2.oracle\"}},{\"data\":{\"count\":2,\"type\":\"s3.2xlarge.2.oracle\"}},{\"data\":{\"count\":1,\"type\":\"s3.2xlarge.4.oracle\"}},{\"data\":{\"count\":1,\"type\":\"c2.large.suse\"}},{\"data\":{\"count\":2,\"type\":\"c2.xlarge.suse\"}},{\"data\":{\"count\":9,\"type\":\"g1.2xlarge.win\"}},{\"data\":{\"count\":8,\"type\":\"g1.xlarge.win\"}},{\"data\":{\"count\":1,\"type\":\"g2.2xlarge.win\"}},{\"data\":{\"count\":3,\"type\":\"hl1.8xlarge.8.linux\"}},{\"data\":{\"count\":1,\"type\":\"s3.xlarge.4.suse\"}},{\"data\":{\"count\":1,\"type\":\"uh-l1\"}},{\"data\":{\"count\":4,\"type\":\"co-p1\"}},{\"data\":{\"count\":1,\"type\":\"g1.2xlarge.2.win\"}},{\"data\":{\"count\":3,\"type\":\"s3.xlarge.2.suse\"}},{\"data\":{\"count\":2,\"type\":\"s3.small.1.byol\"}},{\"data\":{\"count\":1,\"type\":\"19_szwlw\"}},{\"data\":{\"count\":28,\"type\":\"g3.4xlarge.4.win\"}},{\"data\":{\"count\":1,\"type\":\"g3.8xlarge.4.win\"}},{\"data\":{\"count\":20,\"type\":\"19_gzzh\"}},{\"data\":{\"count\":4,\"type\":\"c3.large.2.win\"}},{\"data\":{\"count\":2,\"type\":\"c2.2xlarge.suse\"}},{\"data\":{\"count\":1,\"type\":\"c3.large.2.linux\"}},{\"data\":{\"count\":1,\"type\":\"m3.large.8.linux\"}},{\"data\":{\"count\":1,\"type\":\"c1.large.byol\"}},{\"data\":{\"count\":3,\"type\":\"c1.small.byol\"}},{\"data\":{\"count\":2,\"type\":\"s3.large.2.oracle\"}},{\"data\":{\"count\":2,\"type\":\"c2.large.oracle\"}},{\"data\":{\"count\":1,\"type\":\"c1.2xlarge.oracle\"}},{\"data\":{\"count\":1,\"type\":\"c2.4xlarge.oracle\"}},{\"data\":{\"count\":1,\"type\":\"s1.8xlarge.oracle\"}},{\"data\":{\"count\":1,\"type\":\"c1.small.oracle\"}},{\"data\":{\"count\":1,\"type\":\"c1.small.suse\"}},{\"data\":{\"count\":1,\"type\":\"19_ipv6\"}},{\"data\":{\"count\":1,\"type\":\"c1.xlarge.suse\"}},{\"data\":{\"count\":5,\"type\":\"g1.2xlarge.2.linux\"}},{\"data\":{\"count\":1,\"type\":\"g1.xlarge.2.win\"}}]\"\n" +
                "}");
        if (jsonObject2.get("statusCode").equals(800)) {
            List list = JSON.parseObject(jsonObject2.getString("returnObj"), List.class);
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                Map map = (Map) iterator.next();
                JSONObject jsonObject3 = JSON.parseObject(map.get("data").toString());
                listType.add(jsonObject3.getString("type"));
            }
        }
        for (String string : listType) {
            System.out.println(string);
        }*/
       /* GetVMMsg getVMMsg = new GetVMMsg();
        getVMMsg.cleanToken(httpRest, "8fc26857a91a40ee8aa73e6fc730548f");*/
        StringBuffer jsonParam = new StringBuffer();
        jsonParam.append("aaaaaa,");
        jsonParam.delete(jsonParam.lastIndexOf(","),jsonParam.length());
        System.out.print(jsonParam.toString());

    }
}
