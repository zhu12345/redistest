package cn.LinuxShell.impl;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import cn.LinuxShell.CheckCDRNoService;
import cn.constant.ConstantKey;
import cn.hwsservice.service.CdrFileService;
import cn.taskScheduled.SendEmail;
import cn.util.httpUtil.HttpRestImpl;
import cn.util.httpUtil.dao.HttpRest;
import cn.util.shell.ShellOperateUtils;
import cn.util.string.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class CheckCDRNoServiceImpl implements CheckCDRNoService {
    private static final Logger logger = LoggerFactory.getLogger(CheckCDRNoServiceImpl.class);
    @Resource
    private CdrFileService cdrFileService;

    /**
     * 规则组合
     * @param strings ["CTC_cn-sxty1_20180913000346.zip","CTC_cn-sxty1_20180913010337.zip","CTC_cn-sxty1_20180913020341.zip","CTC_cn-sxty1_20180913030334.zip","CTC_cn-sxty1_20180913040338.zip","CTC_cn-sxty120180913050331.zip","CTC_cn-sxty1_20180913060334.zip","CTC_cn-sxty1_20180913070333.zip","CTC_cn-sxty1_20180913080335.zip","CTC_cn-sxty1_20180913090335.zip","CTC_cn-sxty1_20180913100336.ip","CTC_cn-sxty1_20180913110333.zip","CTC_cn-sxty1_20180913120337.zip","CTC_cn-sxty1_20180913130330.zip","CTC_cn-sxty1_20180913140334.zip","CTC_cn-sxty1_20180913150342.zip","CTC_cn-sxt1_20180913160338.zip","CTC_cn-sxty1_20180913170329.zip","CTC_cn-sxty1_20180913180337.zip","CTC_cn-sxty1_20180913190343.zip","CTC_cn-sxty1_20180913200330.zip","CTC_cn-sxty1_2018091321034.zip","CTC_cn-sxty1_20180913220334.zip","CTC_cn-sxty1_20180913230344.zip"]
     * @param region cn-sxty1
     * @param date 20180913
     * @return
     */
    private StringBuffer checkByDateAndRegion(List<String> strings, String region, String date) {
        logger.info("核查的资源池：{}和对应日期：{}", region, date);
        //搜集不存在的文件信息
        StringBuffer stringBuffer1 = new StringBuffer();
        try {
            List<String> maters = new ArrayList<String>();
            if (strings.size() != 12) {
                for (int i = 0; i < 24; i++) {
                    String s1 = "";

                    if (i < 10) {
                        s1 = "0" + String.valueOf(i);
                    } else {
                        s1 = String.valueOf(i);
                    }
                    maters.add("CTC_" + region + "_" + date + s1);
                }
            }

            for (String mater : maters) {
                boolean contcat = false;
                for (String s : strings) {
                    if (s.contains(mater)) {
                        contcat = true;
                        break;
                    }
                }
                if (contcat == false) {
                    stringBuffer1.append(mater + "|");
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return stringBuffer1;
    }

    // /hwservice/getZones
    public void repay() {
        try {
            HttpRest httpRest = new HttpRestImpl();
            String s = httpRest.getParamAsMap(ConstantKey.ng + ConstantKey.getZones,
                    null, null);
            JSONObject jsonObject = JSON.parseObject(s);
            List zoneConfigs = JSON.parseObject(jsonObject.getString("returnObj"), List.class);
            System.out.println(JSON.toJSONString(zoneConfigs));
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -1);
            String yesterday = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
            StringBuffer stringBuffer = new StringBuffer();
            StringBuffer pdString = new StringBuffer();
            List<String> zoneString = new ArrayList<String>();
            for (Object o : zoneConfigs) {
                JSONObject jsonObject1 = JSON.parseObject(o.toString());
                logger.info(jsonObject1.getString("regionId"));
                zoneString.add(jsonObject1.getString("regionId"));
            }
            Connection connection = null;
            InputStream stdout = null;
            Session session = null;
            try {
                connection = ShellOperateUtils.getConnection(ConstantKey.userName, ConstantKey.password,
                        ConstantKey.host, ConstantKey.port);
                session = ShellOperateUtils.getSession(connection);
                String cmd = ConstantKey.Often_cd + "ls |grep " + yesterday;
                stdout = ShellOperateUtils.getInputStream(session, cmd);
                String split = "\\|";
                stringBuffer = ShellOperateUtils.doShell(stdout, "|");
                String[] fileNames = stringBuffer.toString().split(split);
                StringBuffer stringBufferCheck = new StringBuffer();
                Map<String, CopyOnWriteArrayList<String>> regionFileName = new HashMap<String, CopyOnWriteArrayList<String>>();
                for (String fileName : fileNames) {
                    CopyOnWriteArrayList<String> copyOnWriteArrayList = null;
                    for (String region : zoneString) {
                        if (fileName.contains(region)) {
                            copyOnWriteArrayList = regionFileName.get(region);
                            if (copyOnWriteArrayList == null) {
                                copyOnWriteArrayList = new CopyOnWriteArrayList<String>();
                            }
                            copyOnWriteArrayList.add(fileName);
                            regionFileName.put(region, copyOnWriteArrayList);
                        }
                    }
                }
                for (String region : zoneString) {
                    pdString.append("|");
                    stringBufferCheck.append(this.checkByDateAndRegion(regionFileName.get(region), region, yesterday)).append("|");
                }
                logger.info(pdString.toString());
                List<String> listType = new ArrayList<String>();
                if (!stringBufferCheck.toString().equals(pdString.toString())) {
                    List<String> lostFile = Arrays.asList(stringBufferCheck.toString().split("\\|"));
                    stringBufferCheck.delete(0, stringBufferCheck.length()).append("检查话单异常：下面是缺失的压缩文件开头的字符串\n");
                    for (int i = 0; i < lostFile.size(); i++) {
                        if ("".equals(lostFile.get(i))) {
                            continue;
                        }
                        stringBufferCheck.append(lostFile.get(i)).append("\n");
                    }
                    getMappingType(httpRest, listType);
                    stringBufferCheck.append("\n华为mapping映射不对应的：");
                    for (String s1 : listType) {
                        stringBufferCheck.append(s1 + "\n");
                    }
                    SendEmail.sendMail(stringBufferCheck);
                } else {
                    stringBufferCheck.delete(0, stringBufferCheck.length()).append("检查话单正常!");
                    getMappingType(httpRest, listType);
                    stringBufferCheck.append("\n华为mapping映射不对应的：");
                    for (String s1 : listType) {
                        stringBufferCheck.append(s1 + "\n");
                    }
                    SendEmail.sendMail(stringBufferCheck);
                }
                for (String fileName : fileNames) {
                    doSaveCDRFile(connection, fileName);
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            } finally {
                ShellOperateUtils.closeStream(stdout);
                ShellOperateUtils.closeSession(session);
                ShellOperateUtils.closeConnection(connection);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    //保存检查出来的压缩文件
    private void doSaveCDRFile(Connection connection, String fileName) {
        InputStream stdout = null;
        Session session = null;
        try {
            session = ShellOperateUtils.getSession(connection);
            String cmd = ConstantKey.Often_cd + "stat " + fileName;
            stdout = ShellOperateUtils.getInputStream(session, cmd);
            String split = "\\|";
            StringBuffer stringBuffer = ShellOperateUtils.doShell(stdout, "|");
            String[] strings = stringBuffer.toString().split("\\|");
            Map<String, String> map = new HashMap<String, String>();
            for (String s : strings) {
                String[] a = s.replace(" ", "").split("\\t");
                for (String s1 : a) {
                    if (!StringUtils.pdNULL(s1) && s1.contains(":")) {
                        String[] strings1 = s1.split("\\:");
                        String key = "";
                        String value = "";
                        for (int i = 0; i < strings1.length; i++) {
                            if (i == 0) {
                                key = strings1[i];
                            } else {
                                value = value + strings1[i];
                            }
                        }
                        map.put(key, value);
                    }
                }
            }
            if (map.get("Size") != null && map.get("Access") != null) {
                String size = map.get("Size");
                String accessDate = map.get("Access").substring(0, 16);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-ddHHmmss");
                Date date = dateFormat.parse(accessDate);
                //获取zip文件中的文件资源 ResourceType
                String s = getResourceTypes(connection, fileName);
                cdrFileService.saveCdrFile(fileName, Double.parseDouble(size), date, s);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            ShellOperateUtils.closeStream(stdout);
            ShellOperateUtils.closeSession(session);
        }
    }
    //获取zip文件中的文件资源

    /**
     * CTC_cn-ahwh1_20180912000350.zip
     * unzip -l CTC_cn-ahwh1_20180912000350.zip 执行后的返回
     * File: 'CTC_cn-ahwh1_20180912000350.zip'
     * Size: 5334492   	Blocks: 10432      IO Block: 4096   regular file
     * Device: 821h/2081d	Inode: 71511055    Links: 1
     * Access: (0644/-rw-r--r--)  Uid: ( 1002/ ubuntus)   Gid: ( 1002/  owners)
     * Access: 2018-09-12 08:05:00.161009616 +0800
     * Modify: 2018-09-12 08:05:00.141010224 +0800
     * Change: 2018-09-12 08:05:00.141010224 +0800
     * Birth: -
     *
     * @param connection
     * @param fileName
     * @return
     */
    private String getResourceTypes(Connection connection, String fileName) {
        InputStream stdout = null;
        Session session = null;
        StringBuffer stringBufferresult = new StringBuffer();
        try {
            String cmd = ConstantKey.Often_cd + "unzip -l " + fileName;
            session = ShellOperateUtils.getSession(connection);
            stdout = ShellOperateUtils.getInputStream(session, cmd);
            StringBuffer stringBuffer = ShellOperateUtils.doShell(stdout, "|");
            String[] strings = stringBuffer.toString().split("\\|");
            Map<String, String> map = new HashMap<String, String>();
            for (String s : strings) {
                System.out.println(s);
                String[] a = s.split("\\_");
                if (a.length > 2 && !a[2].contains(".zip") && !Arrays.asList(stringBufferresult.toString().split("\\|")).contains(a[2])) {
                    System.out.println(a[2]);
                    stringBufferresult.append(a[2]).append("|");
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            ShellOperateUtils.closeStream(stdout);
            ShellOperateUtils.closeSession(session);
        }
        return stringBufferresult.toString();
    }

    //获取话单mapping
    private void getMappingType(HttpRest httpRest, List<String> listType) {
        String result = httpRest.getParamAsMap(ConstantKey.hws_cdr_url + ConstantKey.getErrorMappingInfo,
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
