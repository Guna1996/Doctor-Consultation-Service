/**
 * <p>
 * This is the base package for all the dto classes and
 * the dto package classes are Doctor dto,Specialization dto,
 * Clinic dto,Doctor clinic dto,Appointment dto,Feedback dto,Patient dto,
 * Patient vital dto,Timeslot dto.
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * This Authentication request class contains details for login
 *
 * @author Gunaseelan K
 * @version 1
 * @since 2022-10-10
 */
@Data
public class AuthenticationRequest implements Serializable {

    private String username;

    private String password;
}
