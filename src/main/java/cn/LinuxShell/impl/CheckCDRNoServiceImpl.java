package cn.LinuxShell.impl;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import cn.LinuxShell.CheckCDRNoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CheckCDRNoServiceImpl implements CheckCDRNoService {
    private static final Logger logger = LoggerFactory.getLogger(CheckCDRNoServiceImpl.class);

    @Override
    public StringBuffer checkByDateAndRegion(String region, String date) {
        logger.info("核查的资源池：{}和对应日期：{}",region,date);
        StringBuffer stringBuffer1 = new StringBuffer();
        try {
            StringBuffer stringBuffer = this.doShell("ubuntus","bss@ter439",
                    "10.144.242.110",22, date, region);
            String[] strings =  stringBuffer.toString().split(":");
            List<String> maters = new ArrayList<String>();
            if (strings.length != 12) {
                for (int i = 0; i < 24;i++) {
                    String s1 = "";

                    if (i < 10) {
                        s1 = "0"+String.valueOf(i);
                    } else {
                        s1 = String.valueOf(i);
                    }
                    maters.add("CTC_"+region+"_"+date+s1);
                }
            }

            for (String mater: maters) {
                boolean contcat = false;
                for (String s: strings) {
                    if (s.contains(mater)) {
                        contcat = true;
                        break;
                    }
                }
                if (contcat == false) {
                    stringBuffer1.append(mater+"|");
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return stringBuffer1;
    }

    private  StringBuffer doShell(String username,String password, String host, int port, String date, String region) throws Exception{
        String cmd = "cd /cdr_file_backup/udr/rawdata;ls |grep " + date + "|grep " + region;
        Connection conn = new Connection(host, port);
        conn.connect(); // make sure the connection is opened
        boolean isAuthenticated = conn.authenticateWithPassword(username,password);
        InputStream stdout = null;
        if (isAuthenticated==true) {
            Session sess = conn.openSession();
// 执行cmd
            sess.execCommand(cmd);
            stdout = new StreamGobbler(sess.getStdout());
        }
        StringBuffer stringBuffer = new StringBuffer();
        if (stdout != null) {
            @SuppressWarnings("resource")
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(stdout));
            String line ="";
            while (line != null) {
                line = br.readLine();
                System.out.println(line);
                stringBuffer.append(line + ":");
            }
        }
        return stringBuffer;
    }


}
