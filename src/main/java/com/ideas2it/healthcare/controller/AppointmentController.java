/**
 * <p>
 * This is the base package for all the controller classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.controller;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.dto.AppointmentDto;
import com.ideas2it.healthcare.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * This AppointmentController class is a Controller class and this
 * class is used to get input and display outputs for appointments
 * </p>
 *
 * @author Gunaseelan K
 * @version 1
 * @since 2022-10-10
 */
@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    /**
     * <p>
     * fix new appointment
     * </p>
     *
     * @param appointmentDto is appointment object
     * @return AppointmentDto
     */
    @PostMapping
    public AppointmentDto addAppointment(@Valid @RequestBody AppointmentDto appointmentDto) {
        return appointmentService.addAppointment(appointmentDto);
    }

    /**
     * <p>
     * Update reschedule appointment using appointment id
     * </p>
     *
     * @param appointmentDto is details of appointment
     * @return AppointmentDto
     *
     */
    @PutMapping
    public AppointmentDto rescheduleAppointment(@Valid @RequestBody AppointmentDto appointmentDto) {
        return appointmentService.rescheduleAppointment(appointmentDto);
    }

    /**
     * <p>
     * Delete appointment by Id
     * </p>
     *
     * @param id is appointment id
     * @return string
     */
    @PutMapping(Constants.ID)
    public String deleteAppointment(@PathVariable(Constants.PATH_ID) int id) {
        return appointmentService.deleteAppointmentById(id);
    }
}
