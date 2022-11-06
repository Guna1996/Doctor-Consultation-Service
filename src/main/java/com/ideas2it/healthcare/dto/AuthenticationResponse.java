/**
 * <p>
 * This is the base package for all the model classes
 * which is for doctor, clinic and patient
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * This Authentication response class contains jwt token
 *
 * @author Gunaseelan K
 * @version 1
 * @since 2022-10-10
 */
@Data
public class AuthenticationResponse implements Serializable {

    private final String jwt;
}
