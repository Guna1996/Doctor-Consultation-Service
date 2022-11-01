package com.ideas2it.healthcare.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class DateUtil {

    public static int getDifferenceInYears(LocalDate date) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(date, currentDate).getYears();
    }

    public static boolean isDateInvalid(LocalDateTime localDate) {
        LocalDate date = localDate.toLocalDate();
        LocalDate currentDate = LocalDate.now();
        return (0 < Period.between(date, currentDate).getDays());
    }
}
