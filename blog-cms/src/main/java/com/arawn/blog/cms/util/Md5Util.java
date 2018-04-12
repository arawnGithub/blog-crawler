package com.arawn.blog.cms.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * MD5工具类
 */
public class Md5Util {

    public static final String SALT = "arawn";

    /**
     * MD5
     * @param str
     * @param salt
     * @return
     */
    public static String md5(String str, String salt) {
        return new Md5Hash(str, salt).toString();
    }

    public static void main(String[] args) {
        System.out.println(md5("123456", SALT));
    }
}
