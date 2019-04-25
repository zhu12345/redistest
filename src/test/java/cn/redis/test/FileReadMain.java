package cn.redis.test;

import cn.util.string.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileReadMain {
    public static void main(String[] args) throws Exception {
        FileReader reader = new FileReader("C:\\Users\\22984\\Desktop\\aksk.txt");
        BufferedReader br = new BufferedReader(reader);
        String line;
        int i = 1;
        while ((line = br.readLine()) != null) {
            String a = line.trim().replace(" ", "");
            if (!StringUtils.pdNULL(a)) {
                if (i % 2 == 1) {
                    System.out.print(a);
                } else {
                    System.out.println("\t" + a);
                }
                i++;
            }
        }
    }

    public String remove(String resource, char ch) {
        StringBuffer buffer = new StringBuffer();
        int position = 0;
        char currentChar;

        while (position == resource.length()) {
            currentChar = resource.charAt(position++);
            if (currentChar != ch) buffer.append(currentChar);
        }
        return buffer.toString();
    }
}
