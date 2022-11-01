/**
 * <p>
 * This is the base package for all the util classes
 * which is for simple calculations
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.util;

import com.ideas2it.healthcare.common.Constants;

import java.time.LocalDate;

/**
 * <p>
 * VitalUtil class is used for simple vital calculations
 * </p>
 *
 * @author Bala Ashwanth
 * @version 1
 * @since 2022-10-10
 */
public class VitalUtil {

    /**
     * <p>
     * This method is used to find BP risk level with systolic and
     * diastolic values
     * </p>
     *
     * @param systolic {@link Float} is systolic of patient
     * @param diastolic {@link Float} is diastolic of patient
     * @return {@link String}
     */
    public static String getBPRiskLevel(Float systolic, Float diastolic) {
        if ((systolic >= 120 & systolic <= 139) && (diastolic >= 80 && diastolic <= 89)){
            return (Constants.AT_RISK);
        } else if (systolic >= 140 && diastolic >= 90 )  {
            return (Constants.HIGH);
        } else if (systolic < 120 && diastolic < 80 ) {
            return (Constants.NORMAL);
        }
        else {
            return Constants.LOW;
        }
    }
}
