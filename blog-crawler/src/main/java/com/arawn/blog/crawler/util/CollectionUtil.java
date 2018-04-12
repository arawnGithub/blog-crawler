package com.arawn.blog.crawler.util;

import java.util.Collection;
import java.util.Map;

public class CollectionUtil {

    /**
     * 判断一个集合是否为空
     */
    public static <T> boolean isEmpty(Collection<T> col) {
        if (col == null || col.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断一个集合是否不为空
     */
    public static <T> boolean isNotEmpty(Collection<T> col) {
        if (col != null && col.size() >= 1) {
            return true;
        }
        return false;
    }

    /**
     * 检查一个整形数组是否非空
     *
     * @param array 整形数组
     * @return
     */
    public static boolean isNotEmpty(int[] array) {
        return array != null && array.length >= 1;
    }

    /**
     * 检查一个对象数组是否为空
     *
     * @param array 对象数组
     * @return
     */
    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 检查一个对象数组是否非空
     *
     * @param array 对象数组
     * @return
     */
    public static boolean isNotEmpty(Object[] array) {
        return array != null && array.length >= 1;
    }

    /**
     * 判断Map是否为空
     *
     * @param map
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> boolean isEmpty(Map<K, V> map) {
        if (map == null || map.isEmpty()) {
            return true;
        }

        return false;
    }

    /**
     * 判断Map是否不为空
     *
     * @param map
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> boolean isNotEmpty(Map<K, V> map) {
        return !isEmpty(map);
    }

}