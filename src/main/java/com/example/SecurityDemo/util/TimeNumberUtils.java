package com.example.SecurityDemo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ：zfx
 * @date ：Created in 2020/6/22 14:24
 * @description：生成订单号
 * @modified By：
 * @version: 1.0$
 */
public class TimeNumberUtils {
    public static void main(String[] args) {
        //测试方法略..自己去测了哈
        //结果: ===>20200407133313000001
    }


    private static int sequence = 0;
    private static int length = 6;

    public static synchronized String getLocalTrmSeqNum() {
        sequence = sequence >= 999999 ? 1 : sequence + 1;
        String datetime = new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new Date());
        String s = Integer.toString(sequence);
        return datetime +addLeftZero(s, length);
    }

   /**
   * @description 
   *@params  
   * @return  0
   * @author  zfx
   * @date  2020/6/22 14:27
   *
   */
    public static String addLeftZero(String s, int length) {
        // StringBuilder sb=new StringBuilder();
        int old = s.length();
        if (length > old) {
            char[] c = new char[length];
            char[] x = s.toCharArray();
            if (x.length > length) {
                throw new IllegalArgumentException(
                        "Numeric value is larger than intended length: " + s
                                + " LEN " + length);
            }
            int lim = c.length - x.length;
            for (int i = 0; i < lim; i++) {
                c[i] = '0';
            }
            System.arraycopy(x, 0, c, lim, x.length);
            return new String(c);
        }
        return s.substring(0, length);
    }
}
