/**
 * <p>
 * This is the base package for all the Dto classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.doctorConsultancyService.dto;

import com.ideas2it.doctorConsultancyService.model.Doctor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

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
@Data
public class SpecializationDto {

    private int id;

    @NotNull
    @Size(min = 2, message = "User name should have at least 2 characters")
    private String name;

    @NotNull
    private String status;

    private List<Doctor> doctors;
}
