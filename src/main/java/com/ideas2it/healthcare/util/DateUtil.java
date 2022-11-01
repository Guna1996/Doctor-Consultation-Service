package com.ideas2it.healthcare.util;

import java.time.LocalDate;
import java.time.Period;

public class DateUtil {

    public static int getDifferenceInYears(LocalDate date) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(date, currentDate).getYears();
    }
}
