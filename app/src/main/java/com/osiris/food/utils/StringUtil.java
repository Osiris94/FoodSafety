package com.osiris.food.utils;

public class StringUtil {
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((!Character.isWhitespace(str.charAt(i)))) {
                return false;
            }
        }
        return true;
    }


    public static boolean isEmpty(Object obj) {
        if (obj != null && !"".equals(obj.toString())
                && !"null".equals(obj.toString().trim())) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 计算换行符
     * @param str
     * @return
     */
    public static int countN(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if ('\n' == str.charAt(i)) {
                count++;
            }
        }
        return count;
    }
}
