package cn.redis.test;

import ch.ethz.ssh2.Session;
import cn.constant.ConstantKey;
import cn.hws.bo.ZoneConfig;
import cn.temp.GetVMMsg;
import cn.util.httpUtil.HttpRestImpl;
import cn.util.httpUtil.dao.HttpRest;
import cn.util.shell.ShellOperateUtils;
import cn.util.string.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestMain {
    public static void main(String[] args) {/*
        InputStream stdout = null;
        Session session = null;
        StringBuffer stringBuffer = null;
        try {
            session = ShellOperateUtils.getSession(ConstantKey.userName, ConstantKey.password,
                    ConstantKey.host, ConstantKey.port);
            String cmd = "cd /cdr_file_backup/udr/rawdata;stat CTC_cn-ahwh1_20180912000350.zip";
            stdout = ShellOperateUtils.getInputStream(session, cmd);
            String split = "\\|";
            stringBuffer = ShellOperateUtils.doShell(stdout, "|");
            String[] strings = stringBuffer.toString().split("\\|");
            Map<String, String> map = new HashMap<String, String>();
            for (String s:strings) {
//                System.out.println(s);
                String[] a = s.replace(" ","").split("\\t");
                for (String s1:a) {
                    if (!StringUtils.pdNULL(s1) && s1.contains(":")) {
                        String[] strings1 = s1.split("\\:");
//                        System.out.println(s1);
                        String key = "";
                        String value = "";
                        for (int i = 0; i < strings1.length; i++) {
                                if (i==0) {
                                    key=strings1[i];
                                }else {
                                    value=value+strings1[i];
                                }
                        }
                        map.put(strings1[0], value);
                    }
                }
            }
            *//*Iterator<Map.Entry<String, String>> iterator =  map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> m = iterator.next();
                System.out.println(m.getKey()+":"+m.getValue());

            }*//*
            String size = map.get("Size");
            String accessDate = map.get("Access").substring(0,16);
            DateFormat dateFormat =  new SimpleDateFormat("yyyy-MM-ddHHmmss");
            Date date = dateFormat.parse(accessDate);
            System.out.print(date);
            System.out.print(Integer.parseInt(size));
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            ShellOperateUtils.closeStream(stdout);
            ShellOperateUtils.closeSession(session);
        }*/
        HttpRest httpRest = new HttpRestImpl();
        Map<String, String> mapHeader = new HashMap<String, String>();
        mapHeader.put("name", "18956674880");
        httpRest.getParamAsMap("http://10.144.242.126:80/hwservice/queryDomainByName", null,mapHeader);
    }
}
