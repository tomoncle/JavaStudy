package com.aric.common.utils;

/**
 * 字符串工具类
 * Created by tom.lee on 2016/3/16.
 */
public class StringUtils<T> {


    public static boolean isEmpty(String s){
        return  s==null||s.length()==0;
    }

    public static boolean isNotEmpty(String s){
        return !isEmpty(s);
    }

    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }
    public static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }

}
