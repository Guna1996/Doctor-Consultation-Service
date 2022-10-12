package com.ideas2it.doctorConsultancyService.common;

public class Constants {
    public static final String ACTIVE = "active";

    public static final String INACTIVE = "inactive";
    public static final String NAME_REGEX = "([A-Z][a-z]{2,30}\\s*)+";

    public static final String EMAIL_REGEX = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
    public static final String MOBILE_NUMBER_REGEX = "^(0/91)?[7-9][0-9]{9}$";
    public static final String STREET_REGEX = "\"^[1-9]\\\\d*(?: ?(?:[a-z]|[/-] ?\\\\d+[a-z]?))?$\"";
    public static final String PINCODE_REGEX = "^[1-9][0-9]{5}$";
    public static final String COUNTRY_REGEX = "[a-zA-Z]{2,}";

    public static final String DATE_OF_BORTH_REGEX ="^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$";

    public static final String GENDER_REGEX ="^male$|^female$";


}

