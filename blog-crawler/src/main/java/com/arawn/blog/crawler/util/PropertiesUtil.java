package com.arawn.blog.crawler.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * properties工具类
 *
 * @author user
 */
public class PropertiesUtil {

    /**
     * 根据key获取value值
     *
     * @param key
     * @return
     */
    public static String getValue(String key) {
        Properties prop = new Properties();
        InputStream in = PropertiesUtil.class.getResourceAsStream("/crawler.properties");
        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(key);
    }

}