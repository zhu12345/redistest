package cn.redis.test;

import cn.util.shell.SSH2Utils;

import java.io.File;
import java.io.IOException;

public class Test3 {
    public static void main(String[] args) {
        try {
//            SSH2Utils ssh2Utils = new SSH2Utils("192.168.13.99", "ubuntu", "1qazxsw2", 22, "/home/ubuntu/");
//            ssh2Utils.putFile("C:\\huadan\\hws_adapter\\udr\\pricedata\\CT_HW_VM_20190111154959.csv", "CT_HW_VM_20190111154959.csv");
//            ssh2Utils.downLoadFile("C:\\huadan\\hws_adapter\\udr\\pricedata\\a.txt", "a.txt");
//            SSH2Utils ssh2Utils = new SSH2Utils("10.144.242.136", "ubuntus", "bss@cdr30530", 22, "/home/ubuntus/cdr_adapter/oos/downloadbak/");
//            ssh2Utils.downLoadFile("C:\\huadan\\CT_OOS_OOS_20190115012497.csv", "CT_OOS_OOS_20190115012497.csv");
            String[] s = new String[]{"solr-7.6.0.zip"
            };
            /*for (String fileName : s) {
                SSH2Utils ssh2Utils = new SSH2Utils("42.123.76.15", "logcheck", "75PCKyQmQBbsCZYxRzdVWd%hf%R@ZB", 22, "/backup1/110_hws_adapter/cdr_file_backup/outdata/201809/");
                ssh2Utils.downLoadFile("C:\\Users\\22984\\Desktop\\新建文件夹\\", fileName);
            }*/
            for (String fileName : s) {
                SSH2Utils ssh2Utils = new SSH2Utils("42.123.76.15", "logcheck", "75PCKyQmQBbsCZYxRzdVWd%hf%R@ZB", 22, "/home/logcheck/");
                ssh2Utils.putFile("C:\\Users\\22984\\Desktop\\新建文件夹\\"+ fileName);
            }
            /*for (String fileName : s) {
                SSH2Utils ssh2Utils = new SSH2Utils("42.123.76.15", "logcheck", "75PCKyQmQBbsCZYxRzdVWd%hf%R@ZB", 22, "/home/logcheck/");
                ssh2Utils.downLoadFile("C:\\Users\\22984\\Desktop\\新建文件夹\\", fileName);
            }*/
            /*for (String fileName : s) {
                SSH2Utils ssh2Utils = new SSH2Utils("36.111.164.141", "root", "gQ4^tqabfb", 22, "/root/data/");
                ssh2Utils.putFile("C:\\Users\\22984\\Desktop\\新建文件夹\\" + fileName);
            }
            for (String fileName : s) {
                File file = new File("C:\\Users\\22984\\Desktop\\新建文件夹\\" + fileName);
                if (file.exists()) {
                    file.delete();
                }
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
