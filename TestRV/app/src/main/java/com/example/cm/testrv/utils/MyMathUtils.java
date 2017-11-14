package com.example.cm.testrv.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by cm on 2017/11/13.
 */

public class MyMathUtils {

    /* @n  小数点后几位
     */
    public static double formatDouble2(double d, int n) {
        BigDecimal b = new BigDecimal(d).setScale(n, RoundingMode.UP);   // 四舍五入
        return b.doubleValue();
    }


}
