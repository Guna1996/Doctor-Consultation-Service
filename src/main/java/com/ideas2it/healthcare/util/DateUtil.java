package com.ideas2it.healthcare.util;

import com.ideas2it.healthcare.dto.ClinicDto;
import com.ideas2it.healthcare.exception.NotFoundException;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

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
     * This method is used to check wheather the date is future date
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
            throw new NotFoundException("Please Enter valid schedule time..");
        }
        LocalDate currentDate = LocalDate.now();
        return (0 < Period.between(date, currentDate).getDays());
    }
}
