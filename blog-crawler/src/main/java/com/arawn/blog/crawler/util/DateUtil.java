package com.arawn.blog.crawler.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author user
 */
public class DateUtil {

    /**
     * 获取当前年月日路径
     *
     * @return
     */
    public static String getCurrentDatePath() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(date);
    }

}