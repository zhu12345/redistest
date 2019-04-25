package cn.taskScheduled;

import cn.util.httpUtil.HttpRestImpl;
import cn.util.httpUtil.dao.HttpRest;

import java.util.HashMap;
import java.util.Map;

public class SendEmail {
    private static String url = "http://10.144.242.126/mail/sendDetailsMail";
    private static String toMail = "zhuxinquan@ctyun.cn;chentaih@chinatelecom.cn;zhouxb@ctyun.cn;yangcheng@ctyun.cn";
    public static boolean sendMail(Object o) {
        HttpRest httpRest = new HttpRestImpl();
        Map<String, String> mapBody = new HashMap<String, String>();
        mapBody.put("content", o.toString());
        mapBody.put("sender", toMail);
        httpRest.postParamAsMap(url,mapBody,null,null);
        return false;
    }
}
