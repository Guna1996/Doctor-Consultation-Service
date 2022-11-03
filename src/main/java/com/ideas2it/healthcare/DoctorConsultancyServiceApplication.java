/**
 * <p>
 * This is the base package for all other
 * packages in this web application
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * This is the base class which is used to run the web application by
 * executing all other classes in this web application
 * </p>
 *
 * @author Bala Ashwanth, Gunaseelan K, Ramachandhran C, Mohamed Jubair S
 * @version 1
 * @since 2022-10-10
 */
@SpringBootApplication
public class DoctorConsultancyServiceApplication {

    /**
     * <p>
     * This is the Method which is used to run the web application by
     * executing all other classes in this web application
     * </p>
     *
     * @param args {@link String}
     */
    public static void main(String[] args) {
        SpringApplication.run(DoctorConsultancyServiceApplication.class, args);
    }
}
