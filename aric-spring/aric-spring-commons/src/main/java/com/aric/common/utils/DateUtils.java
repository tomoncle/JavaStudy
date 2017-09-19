package com.aric.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Date utils
 * Created by liyuanjun .
 */
public class DateUtils {

    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";


    public static Date parseDate(String date) {
        return StringUtils.isEmpty(date) ? null : dateUtils(date).values().iterator().next();
    }

    public static Date parseDate(Long date) {
        return date == null ? null : new Date(date);
    }


    public static String datePattern(String date) {
        return StringUtils.isEmpty(date) ? null : dateUtils(date).keySet().iterator().next();
    }

    public static String dateFormat(String date) {
        return dateFormat(date, null);
    }

    public static String dateFormat(String date, String pattern) {
        DateFormat formatter = new SimpleDateFormat(StringUtils.isNotEmpty(pattern) ? pattern : DEFAULT_DATE_FORMAT);
        return formatter.format(parseDate(date));
    }

    public static String dateFormat(Date date) {
        return dateFormat(date, null);
    }

    public static String dateFormat(Date date, String pattern) {
        DateFormat formatter = new SimpleDateFormat(StringUtils.isNotEmpty(pattern) ? pattern : DEFAULT_DATE_FORMAT);
        return formatter.format(date);
    }


    /**
     * 　 d{4}   4个数字
     * 　 D+     非数字字符匹配1次或多次
     * 　  ＊　    零次或多次匹配前面的字符或子表达式
     * d{1,2} 1到2个数字
     * $      正则结束
     * \      转义字符
     * +　　　 一次或多次匹配前面的字符或子表达式
     */
    private static Map<String, Date> dateUtils(String dateStr) {

        HashMap<String, String> dateRegFormat = new HashMap<>();
        dateRegFormat.put("^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D*$",
                "yyyy-MM-dd-HH-mm-ss");
        dateRegFormat.put("^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}$",
                "yyyy-MM-dd-HH-mm");
        dateRegFormat.put("^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}$", "yyyy-MM-dd-HH");
        dateRegFormat.put("^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}$", "yyyy-MM-dd");
        dateRegFormat.put("^\\d{4}\\D+\\d{1,2}$", "yyyy-MM");
        dateRegFormat.put("^\\d{4}$", "yyyy");
        dateRegFormat.put("^\\d{14}$", "yyyyMMddHHmmss");
        dateRegFormat.put("^\\d{12}$", "yyyyMMddHHmm");
        dateRegFormat.put("^\\d{10}$", "yyyyMMddHH");
        dateRegFormat.put("^\\d{8}$", "yyyyMMdd");
        dateRegFormat.put("^\\d{6}$", "yyyyMM");
        dateRegFormat.put("^\\d{1,2}\\s*:\\s*\\d{1,2}\\s*:\\s*\\d{1,2}$", "yyyy-MM-dd-HH-mm-ss");
        dateRegFormat.put("^\\d{1,2}\\s*:\\s*\\d{1,2}$", "yyyy-MM-dd-HH-mm");
        dateRegFormat.put("^\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}$", "yy-MM-dd");
        dateRegFormat.put("^\\d{1,2}\\D+\\d{1,2}$", "yyyy-dd-MM");
        dateRegFormat.put("^\\d{1,2}\\D+\\d{1,2}\\D+\\d{4}$", "MM-dd-yyyy");
        dateRegFormat.put("^\\d{1,2}\\D+\\d{1,2}\\D+\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}D*$", "MM-dd-yyyy-HH-mm");
        dateRegFormat.put("^\\d{1,2}\\D+\\d{1,2}\\D+\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}D*$", "MM-dd-yyyy-HH-mm-ss");
        String curDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Map<String, Date> dateMap = new HashMap<>();
        try {
            for (String key : dateRegFormat.keySet()) {
                if (Pattern.compile(key).matcher(dateStr).matches()) {
                    DateFormat formatter = new SimpleDateFormat(dateRegFormat.get(key));
                    if (key.equals("^\\d{1,2}\\s*:\\s*\\d{1,2}\\s*:\\s*\\d{1,2}$")
                            || key.equals("^\\d{1,2}\\s*:\\s*\\d{1,2}$")) {//13:05:34 或 13:05 拼接当前日期
                        dateStr = curDate + "-" + dateStr;
                    } else if (key.equals("^\\d{1,2}\\D+\\d{1,2}$")) {//21.1 (日.月) 拼接当前年份
                        dateStr = curDate.substring(0, 4) + "-" + dateStr;
                    }
                    String dateReplace = dateStr.replaceAll("\\D+", "-");
                    dateMap.put(dateRegFormat.get(key), formatter.parse(dateReplace));
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("error:日期格式无效:" + dateStr);
            throw new Exception("日期格式无效");
        } finally {
            return dateMap;
        }
    }



}
