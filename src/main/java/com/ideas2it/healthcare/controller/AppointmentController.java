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
import com.ideas2it.healthcare.response.Response;
import com.ideas2it.healthcare.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

/**
 * <p>
 * This AppointmentController class is used to fix, reschedule and cancel
 * appointment by patient.
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
     * This method is used to add appointment of a
     * patient after validating it.
     * </p>
     *
     * @param appointmentDto is appointment object
     * @return AppointmentDto
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> addAppointment(@Valid @RequestBody AppointmentDto appointmentDto) {
        return Response.responseEntity("Success",
                appointmentService.addAppointment(appointmentDto),
                HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to reschedule appointment
     * of a patient after validating it.
     * </p>
     *
     * @param appointmentDto is details of appointment
     * @return AppointmentDto
     *
     */
    @PutMapping
    public ResponseEntity<Map<String, Object>> rescheduleAppointment(@Valid @RequestBody AppointmentDto appointmentDto) {
        return Response.responseEntity("Success",
                appointmentService.rescheduleAppointment(appointmentDto),
                HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to cancel the appointment
     * of a patient.
     * </p>
     *
     * @param id is appointment id
     * @return string
     */
    @PutMapping(Constants.PATH_ID)
    public ResponseEntity<String> deleteAppointment(@PathVariable(Constants.ID) int id) {
        return new ResponseEntity<>(appointmentService.deleteAppointmentById(id), HttpStatus.OK);
    }
}
