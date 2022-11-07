/**
 * <p>
 * This is the base package for all the util classes
 * which is for simple calculations
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.util;

/**
 * <p>
 * MathUtil class is used to do simple maths
 * calculations.
 * </p>
 *
 * @author Bala Ashwanth
 * @version 1
 * @since 2022-10-10
 */
public class MathUtil {

    /**
     * <p>
     * This method is used to get round of
     * value of two numbers.
     * </p>
     * @param count {@link Float}
     * @param rows {@link Float}
     * @return {@link Integer}
     */
    public static Integer pageCount(float count, float rows) {
        float result =  count/rows;
        int pages = (int) Math.floor(result);
        if (pages < result) {
            return pages + 1;
        }
        return pages;
    }
}
