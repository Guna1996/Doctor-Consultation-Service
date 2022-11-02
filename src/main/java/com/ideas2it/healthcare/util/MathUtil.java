package com.ideas2it.healthcare.util;

public class MathUtil {

    public static Integer getExactCount(Integer count, Integer rows) {
        float result = (float) (count/rows);
        if (Math.floor(result) < result) {
            return Math.round(result) + 1;
        }
        else {
            return Math.round(result);
        }
    }
}
