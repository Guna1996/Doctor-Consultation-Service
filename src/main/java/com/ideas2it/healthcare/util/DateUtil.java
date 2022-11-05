/**
 * <p>
 * This is the base package for all the util classes
 * which is for simple calculations
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.util;

import com.ideas2it.healthcare.common.ErrorConstants;
import com.ideas2it.healthcare.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

/**
 * <p>
 * DateUtil class is used for date related calculations
 * </p>
 *
 * @author Mohamed Jubair
 * @version 1
 * @since 2022-10-10
 */
public class DateUtil {

    /**
     * <p>
     * This method is used to find difference in years between two dates
     * </p>
     *
     * @param date {@link LocalDate} is date
     * @return {@link Integer}
     */
    public static Integer getDifferenceInYears(LocalDate date) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(date, currentDate).getYears();
    }

    /**
     * <p>
     * This method is used to check whether the date is future date
     * </p>
     *
     * @param localDate {@link LocalDateTime} is date and time
     * @return {@link Boolean}
     */
    public static Boolean isDateInvalid(LocalDateTime localDate) {
        LocalDate date;
        try {
            date = localDate.toLocalDate();
        } catch (Exception exception) {
            throw new NotFoundException(ErrorConstants.ENTER_VALID_SCHEDULE_TIME);
        }
        LocalDate currentDate = LocalDate.now();
        return (0 < Period.between(date, currentDate).getDays());
    }
}
