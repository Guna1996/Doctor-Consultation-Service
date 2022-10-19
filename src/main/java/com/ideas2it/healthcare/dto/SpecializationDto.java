/**
 * <p>
 * This is the base package for all the Dto classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * <p>
 * This SpecializationDto class is a Dto class and this class is used
 * to transfer the object from controller layer to service
 * layer
 * </p>
 *
 * @author  Mohamed Jubair
 *
 * @version 1
 *
 * @since   2022-10-10
 */
@Getter
@Setter
public class SpecializationDto {

    private int id;

    @NotNull
    @Size(min = 2, message = "User name should have at least 2 characters")
    //@Pattern(regexp = Constants.NAME_REGEX, message = "Enter You Name in this (Firstname Secondname) format")
    private String name;

    private String status;

    private Set<DoctorDto> doctors;

}
