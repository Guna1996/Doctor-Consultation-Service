/**
 * <p>
 * This package contains classes are constan constants
 * and error constants  and user constants.
 * </p>
 *
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.common;

/**
 * <p>
 * This Constants class contains static final string variable
 * with values .we can access these variable any where in the
 * global package by using class name and variable name
 * </p>
 *
 * @author Ramachandran
 *
 * @version 1
 *
 * @since 2022-10-10
 */
public class Constants {
    public static final String ACTIVE = "active";

    public static final String INACTIVE = "inactive";

    public static final String NAME_REGEX = "^([A-Z][a-z]{3,30}\\s*)+$";

    public static final String DATE_REGEX = "^((?:18|20)[0-9][0-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$";

    public static final String QUALIFICATION_REGEX = "[a-zA-Z]{2,}";

    public static final String EMAIL_REGEX = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";

    public static final String MOBILE_NUMBER_REGEX = "^(0/91)?[7-9][0-9]{9}$";

    public static final String STREET_REGEX = "\"^[1-9]\\\\d*(?: ?(?:[a-z]|[/-] ?\\\\d+[a-z]?))?$\"";

    public static final String PINCODE_REGEX = "^[1-9][0-9]{5}$";
    public static final String COUNTRY_REGEX = "[a-zA-Z]{2,}";

    public static final String GENDER_REGEX = "^male$|^female$";

    public static final String VACANT = "vacant";

    public static final String OCCUPIED = "occupied";

    public static final String ID ="/{id}";

    public static final String PATH_ID = "id";

    public static final String PATH_DOCTORID = "doctorId";

    public static final String PATH_CLINICID = "clinicId";

    public static final String PATH_PATIENTID = "patientId";

    public static final String PATIENTID_CLINICID = "/{doctorId}/{clinicId}";

    public static final String name = "name";

    public static final String DATE_OF_BIRHT = "date_of_birth";

    public static final String GENDER = "gender";

    public static final String MOBILE_NUMBER = "mobile_numbre";

    public static final String EMAIL = "email";

    public static final String STATUS = "status";

    public static final String PATIENT = "patient";


}
