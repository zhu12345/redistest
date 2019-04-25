package cn.temp;

import cn.file.xlsFile.XLSBo;
import cn.file.xlsFile.XLSFileUtils;
import cn.util.httpUtil.HttpRestImpl;
import cn.util.httpUtil.dao.HttpRest;
import cn.util.string.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import jxl.write.Label;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Service
public class SyncResouceService1 {
    public static void getHost(String readxlsFileString) {
        XLSFileUtils xlsFileUtils = new XLSFileUtils();
        File file = new File(readxlsFileString);
        XLSBo xlsBo = xlsFileUtils.readExcel(file);
        Map<Integer, Map<String, String>> map = new HashMap<Integer, Map<String, String>>();
        for (Label label : xlsBo.getLabelList()) {
            Map<String, String> mapPara = new HashMap<String, String>();
            System.out.println(label.getRow());
            if (label.getRow() == 0) {
                if (!StringUtils.pdNULL(label.getContents().toString())) {
                    mapPara.put("resEbsId", label.getContents().toString());
                }
            } else if (label.getRow() == 1) {
                if (!StringUtils.pdNULL(label.getContents().toString())) {
                    mapPara.put("size", label.getContents().toString());
                }
            } else if (label.getRow() == 2) {
                if (!StringUtils.pdNULL(label.getContents().toString())) {
                    mapPara.put("volumeName", label.getContents().toString());
                }
            } else if (label.getRow() == 3) {
                if (!StringUtils.pdNULL(label.getContents().toString())) {
                    mapPara.put("resourceId", label.getContents().toString());
                }
            } else if (label.getRow() == 4) {
                if (!StringUtils.pdNULL(label.getContents().toString())) {
                    mapPara.put("resVmID", label.getContents().toString());
                }
            } else if (label.getRow() == 5) {
                if (!StringUtils.pdNULL(label.getContents().toString())) {
                    mapPara.put("type", label.getContents().toString());
                }
            }

            if (label.getColumn() != 0 && mapPara.size() > 0) {
                if (map.get(label.getColumn()) != null) {
                    Map<String, String> mapPara1 = map.get(label.getColumn());
                    mapPara1.putAll(mapPara);
                    map.put(label.getColumn(), mapPara1);
                } else {
                    map.put(label.getColumn(), mapPara);
                }
            }
        }
        HttpRest httpRest = new HttpRestImpl();
        int i = 0;
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<Integer, Map<String, String>> set : map.entrySet()) {
//            System.out.print(i++);
            for (Map.Entry<String, String> set2 : set.getValue().entrySet()) {
                System.out.println("行:" + set.getKey() + "，key:" + set2.getKey().toString() + "，value:" + set2.getValue().toString());
            }
            String failString = doReport(httpRest, set.getValue(), 0);
            if (!StringUtils.pdNULL(failString)) {
                stringBuffer.append(failString + "\n");
            }
        }
        System.out.println("all:" + stringBuffer.toString());
    }

    private static String doReport(HttpRest httpRest, Map<String, String> map, int i) {
        String s = "";
        try {
            s = httpRest.postParamAsMap("http://10.144.242.126:80/vmservice/addVolume", map, null, null);
            JSONObject jsonObject = JSON.parseObject(s);
            if (jsonObject.get("statusCode").equals(800) && jsonObject.get("returnObj").equals(true)) {
                System.out.print("success add " + JSON.toJSONString(map));
            } else {
                System.out.print("fail add " + JSON.toJSONString(map));
                s = map.get("accountId");
            }
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return s;
    }

    public static void main(String[] args) {
        getHost("C:\\Users\\22984\\Desktop\\tem.xls");
    }
}
