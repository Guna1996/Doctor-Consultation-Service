package com.ideas2it.healthcare.util;

import com.ideas2it.healthcare.common.Constants;

public class VitalUtil {

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
