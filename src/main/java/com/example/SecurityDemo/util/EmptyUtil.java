package com.example.SecurityDemo.util;

/**
 * @author ：zfx
 * @date ：Created in 2020/6/29 16:28
 * @description：
 * @modified By：
 * @version: $
 */


import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * @ClassName EmptyUtil
 * @Description 判断对象是否为空工具类
 * @Author AlphaJunS
 * @Date 2020/3/18 20:10
 * @Version 1.0
 */
public class EmptyUtil {

    /**
     * @param o
     * @return boolean
     * @Description 判断是否不为空
     */
    @SuppressWarnings("rawtypes")
    public static boolean isNotEmpty(Object o) {
        boolean boo = false;
        if (o instanceof String) {
            if (null != o) {
                o = (o.toString()).trim();
            }
            if (null != o && !"".equals(o)) {
                boo = true;
            }
        } else if (o instanceof Collection) {
            if (((Collection) o).size() != 0 && null != ((Collection) o)) {
                for (Object object : (Collection) o) {
                    if (null != object) {
                        return true;
                    }
                }
            }
        } else if (o instanceof Map) {
            if (null != o) {
                for (final Iterator ITONE = ((Map) o).keySet().iterator(); ITONE.hasNext(); ) {
                    Object mo;
                    mo = ((Map) o).get(ITONE.next());
                    if (isNotEmpty(mo)) {
                        boo = true;
                    }
                }
            }
        } else {
            if (null != o) {
                boo = true;
            }
        }
        return boo;
    }

    /**
     * @param o
     * @return boolean
     * @Description 判断为空
     */
    public static boolean isEmpty(Object o) {
        return !isNotEmpty(o);
    }

    /**
     * @param o
     * @return boolean
     * @Description 判断对象数组是否为空
     */
    public static boolean arrayIsEmpty(Object[] o) {
        return !arrayIsNotEmpty(o);
    }

    /**
     * @param o
     * @return boolean
     * @Description 断对象数组是否不为空
     */
    public static boolean arrayIsNotEmpty(Object[] o) {
        boolean boo = false;
        if (isEmpty(o)) {
            boo = false;
        } else {
            for (Object object : o) {
                if (isNotEmpty(object)) {
                    boo = true;
                }
            }
        }
        return boo;
    }

    /**
     * @param objects
     * @return boolean
     * @Description 判断全不为空
     */
    public static boolean isAllNotEmpty(Object... objects) {
        for (Object object : objects) {
            if (isEmpty(object)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param objects
     * @return boolean
     * @Description 判断全为空
     */
    public static boolean isAllEmpty(Object... objects) {
        for (Object object : objects) {
            if (isNotEmpty(object)) {
                return false;
            }
        }
        return true;
    }
}