/**
 * <p>
 * This package contains classes are constants
 * and error constants  and user constants.
 * </p>
 *
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.common;

import javax.servlet.http.PushBuilder;

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

    public static final String PATH_ID = "/{id}";

    public static final String ID = "id";

    public static final String PATH_DOCTOR_ID = "doctorId";

    public static final String PATH_CLINIC_ID = "clinicId";

    public static final String PATIENT_ID = "patient_id";

    public static final String DOCTOR_ID = "doctor_id";

    public static final String CLINIC_ID = "clinic_id";

    public static final String STATUS = "status";

    public static final String CREATED_AT = "created_at";

    public static final String NAME = "name";

    public static final String DOOR_NUMBER = "door_number";

    public static final String STREET_NAME = "street_name";

    public static final String CITY = "city";

    public static final String STATE = "state";

    public static final String PIN_CODE = "pin_code";

    public static final String CONTACT_NUMBER = "contact_number";

    public static final String DATE_OF_BIRTH = "date_of_birth";

    public static final String GENDER = "gender";

    public static final String QUALIFICATION = "qualification";

    public static final String SPECIALIZATION_ID = "specialization_id";

    public static final String DOCTOR_SPECIALIZATION = "doctor_specialization";

    public static final String DATE_OF_REGISTRATION = "date_of_registration";

    public static final String MOBILE_NUMBER = "mobile_number";

    public static final String DOCTOR_CLINIC_TIMESLOT = "doctor_clinic_timeslot";

    public static final String DOCTOR_CLINIC_ID = "doctor_clinic_id";

    public static final String TIMESLOT_ID = "timeslot_id";

    public static final String EMAIL = "email";

    public static final String BLOOD_PRESSURE = "blood_pressure";

    public static final String SUGAR_LEVEL = "sugar_level";
    public static final String CONSULTATION_FEE = "consultation_fee";
    public static final String PAGE_NUMBER = "pageNumber";

    public static final String TOTAL_ROWS = "totalRows";

    public static final String PAGE_PATH = "/{pageNumber}/{totalRows}";

    public static final String GET_PATIENT_PATH = "/patient/{patientId}/{pageNumber}/{totalRows}";

    public static final String PATIENT_ID_CLINIC_ID = "/{doctorId}/{clinicId}";

    public static final String VITAL_PATIENT_ID = "/vital/{patientId}";

    public static final String SCHEDULED_ON = "scheduled_on";

    public static final String  PATH_APPOINTMENT_ID= "/appointment/{doctorId}/{pageNumber}/{totalRows}";

    public static final String PATH_FEEDBACK_ID = "/feedbacks/{doctorId}/{pageNumber}/{totalRows}";

    public static final String PATH_PATIENT_ID = "patientId";

    public static final String PATIENT_APPOINTMENT = "/appointment/{patientId}/{pageNumber}/{totalRows}";
}

