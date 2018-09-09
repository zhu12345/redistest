package cn.util.string;

public class StringUtils {

    /**
     * 如果字符串S为空或“”那么返回true
     * @param s
     * @return
     */
    public static boolean pdNULL(String s) {
        if (s==null || s.equals(""))
            return true;
        return false;
    }
}
