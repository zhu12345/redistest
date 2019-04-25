package cn.redis.test;

import com.alibaba.fastjson.JSON;

import java.io.File;

public class Test {
    public static void main(String[] args) {
        File file = new File("C:\\huadan");
        System.out.println(file.exists());
        System.out.println(file.canExecute());
        System.out.println(file.canRead());
        System.out.println(file.canWrite());
        System.out.println(file.isAbsolute());
        System.out.println(file.isDirectory());
        System.out.println(file.isFile());
        System.out.println(file.isHidden());
        System.out.println(JSON.toJSONString(file.list()));
        File file2 = new File("C:\\huadan\\oosPrice\\058fdb05fa0f4af889f6bc7ea91702e2_CT_OOS_OOS_20180705010101.csv");
        System.out.println(file2.delete());
    }
}
