/**
 * <p>
 * This package contains classes are constants
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

    public static final String NAME_REGEX = "^([A-Z][a-z]{3,30}\\s*)+$";

    public static final String QUALIFICATION_REGEX = "[a-zA-Z]{2,}";

    public static final String MOBILE_NUMBER_REGEX = "^(0/91)?[7-9][0-9]{9}$";

    public static final String PIN_CODE_REGEX = "^[1-9][0-9]{5}$";
    public static final String COUNTRY_REGEX = "[a-zA-Z]{2,}";

    public static final String GENDER_REGEX = "^male$|^female$";

    public static final String ID = "/{id}";

    public static final String PATH_ID = "id";

    public static final String PATH_DOCTOR_ID = "doctorId";

    public static final String PATH_CLINIC_ID = "clinicId";

    public static final String PATIENT_ID = "patientId";

    public static final String PAGE_NUMBER = "pageNumber";

    public static final String TOTAL_ROWS = "totalRows";

    public static final String PAGE_PATH = "/{pageNumber}/{totalRows}";

    public static final String GET_PATIENT_PATH = "/patient/{patientId}/{pageNumber}/{totalRows}";

    public static final String PATIENT_ID_CLINIC_ID = "/{doctorId}/{clinicId}";

    public static final String VITAL_PATIENT_ID = "/vital/{patientId}";
}

