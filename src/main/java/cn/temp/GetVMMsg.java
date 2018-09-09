package cn.temp;

import cn.file.xlsFile.XLSBo;
import cn.file.xlsFile.XLSFileUtils;
import cn.util.httpUtil.HttpRestImpl;
import cn.util.httpUtil.dao.HttpRest;
import cn.util.string.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import jxl.write.Label;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GetVMMsg {

    private static final Logger logger = LoggerFactory.getLogger(GetVMMsg.class);

    public void getHost(String readxlsFileString, String writetxtFileString) {
        XLSFileUtils xlsFileUtils = new XLSFileUtils();
        File file = new File(readxlsFileString);
        XLSBo xlsBo = xlsFileUtils.readExcel(file);
        Map<Integer, Map<String , String>> map = new HashMap<Integer, Map<String , String>>();
        for (Label label : xlsBo.getLabelList()) {
            Map<String , String> mapPara = new HashMap<String , String>();
            System.out.println(label.getRow());
            if (label.getRow() == 0) {
                if (!StringUtils.pdNULL(label.getContents().toString())) {
                    mapPara.put("accountId", label.getContents().toString());
                }
            } else if (label.getRow() == 1) {
                if (!StringUtils.pdNULL(label.getContents().toString())) {
                    mapPara.put("vmId", label.getContents().toString());
                }
            } else if (label.getRow() == 2) {
                if (!StringUtils.pdNULL(label.getContents().toString())) {
                    mapPara.put("regionId", label.getContents().toString());
                }
            }
            if (label.getColumn() != 0 && mapPara.size() > 0) {
                if (map.get(label.getColumn()) != null) {
                    Map<String , String> mapPara1 = map.get(label.getColumn());
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
        File f = new File(writetxtFileString);
        for (Map.Entry<Integer, Map<String , String>> set:map.entrySet()) {
            /*System.out.print(i++);
            for (Map.Entry<String, String> set2: set.getValue().entrySet()) {
                System.out.println("行:" + set.getKey() + "，key:" + set2.getKey().toString() + "，value:" + set2.getValue().toString());
            }*/
            stringBuffer.append(getVMEBSNETWORK(httpRest, set.getValue(), 0));
        }
        try {
            writeToFile(f, stringBuffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String doReport(HttpRest httpRest,Map<String , String> map, int i) {
        StringBuffer stringBuffer = new StringBuffer();
        String status = "";
        String s = "";

        try {
            s = httpRest.getParamAsMap("http://10.144.242.126/hws_api/getVMStatus", null, map);
            System.out.print(s);
            JSONObject jsonObject = JSON.parseObject(s);
            if (jsonObject.get("statusCode") != null && jsonObject.get("statusCode").toString().equals("800")) {
                if (jsonObject.get("returnObj") != null) {
                    JSONObject jsonObject1 = JSON.parseObject(jsonObject.getString("returnObj"));
                    if (jsonObject1.get("status") != null) {
                        status = jsonObject1.getString("status");
                    } else {
                        status = "notfound";
                    }
                } else {
                    status = "notfound";
                }
            } else {
                if (i == 0) {
                    cleanToken(httpRest, map.get("accountId"));
                    i++;
                    status =  doReport(httpRest, map, i);
                }else {
                    status = "Non-existent";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            status = "doerror" + e.getMessage();
            if (i == 0) {
                cleanToken(httpRest, map.get("accountId"));
                i++;
                status =  doReport(httpRest, map, i);
            }else {
                status = "Non-existent";
            }
        }
        String string = stringBuffer.append(map.get("vmId")).append("|").append(status).append("\n").toString();
        logger.info("returnResultnew:" + string);
        return string;
    }

    private String getVMEBSNETWORK(HttpRest httpRest,Map<String , String> map, int i) {
        StringBuffer stringBuffer = new StringBuffer();
        String s = "";
        try {
            s = httpRest.getParamAsMap("http://10.144.242.126/hws_api/getVMByVmId", null, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("return:" + s);
        JSONObject jsonObject = JSON.parseObject(s);
        String vmId = "";
        List<String> publicIps = new ArrayList<String>();
        List<String> volumeIds = new ArrayList<String>();
        if (jsonObject.get("statusCode") != null && jsonObject.get("statusCode").toString().equals("800")) {
            if (jsonObject.get("returnObj") != null) {
                JSONObject jsonObject1 = JSON.parseObject(jsonObject.getString("returnObj"));
                vmId = jsonObject1.getString("id");
                if (jsonObject1.get("publicIPboList") != null) {
                    JSONArray jsonObject2 = JSON.parseArray(jsonObject1.getString("publicIPboList"));
                    for (int i$=0;i$<jsonObject2.size();i$++) {
                        JSONObject jsonObject3 = JSON.parseObject(jsonObject2.get(i$).toString());
                        publicIps.add(jsonObject3.get("id").toString());
                    }
                }
                if (jsonObject1.get("volumeBoList") != null) {
                    JSONArray jsonObject2 = JSON.parseArray(jsonObject1.getString("volumeBoList"));
                    for (int i$=0;i$<jsonObject2.size();i$++) {
                        JSONObject jsonObject3 = JSON.parseObject(jsonObject2.get(i$).toString());
                        volumeIds.add(jsonObject3.get("id").toString());
                    }
                }
            }
        }
        for (String vol: volumeIds) {
            stringBuffer.append(vmId).append("\t").append("EBS").append("\t").append(vol).append("\n");
        }
        for (String pub:publicIps) {
            stringBuffer.append(vmId).append("\t").append("NETWORK").append("\t").append(pub).append("\n");
        }
        return stringBuffer.toString();
    }

    private void writeToFile(File file,StringBuffer stringBuffer) throws Exception{
        OutputStream outputStream = new FileOutputStream(file);
        outputStream.write(stringBuffer.toString().getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();
    }
    public void cleanToken(HttpRest httpRest,String accountId) {
        String s = "";
        Map<String, String> map = new HashMap();
        map.put("accountId", accountId);
        try {
            s = httpRest.postParamAsMap("http://10.144.242.126/hws_api/cleanToken", map, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
