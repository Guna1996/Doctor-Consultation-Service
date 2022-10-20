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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

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
     * fix new appointment
     *
     * @param appointmentDto is appointment object
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity<AppointmentDto> addAppointment(@Valid @RequestBody AppointmentDto appointmentDto) {

        return new ResponseEntity<>(appointmentService.addAppointment(appointmentDto), HttpStatus.OK);
    }

    /**
     * Get all the Appointments
     *
     * @return ResponseEntity
     */
    @GetMapping("/{pageNumber}/{totalRows}")
    public ResponseEntity<List<AppointmentDto>> getAppointments(@PathVariable("pageNumber") int pageNumber
            , @PathVariable("totalRows") int totalRows) {

        return new ResponseEntity<>(appointmentService.getAppointments(pageNumber, totalRows), HttpStatus.OK);
    }

    /**
     * Get the Appointment by id
     *
     * @param id is appointment id
     * @return ResponseEntity
     */
    @GetMapping(Constants.ID)
    public ResponseEntity<AppointmentDto> getAppointmentById(@PathVariable(Constants.PATH_ID) int id) {

        return new ResponseEntity<>(appointmentService.getAppointmentById(id), HttpStatus.OK);
    }

    /**
     * Update reschedule appointment using appointment id
     *
     * @param appointmentDto is Appointment object
     */
    @PutMapping
    public ResponseEntity<AppointmentDto> rescheduleAppointment(@Valid @RequestBody AppointmentDto appointmentDto) {

        return new ResponseEntity<>(appointmentService.rescheduleAppointment(appointmentDto), HttpStatus.OK);
    }

    /**
     * Delete appointment by Id
     *
     * @param id is appointment id
     * @return string
     */
    @DeleteMapping(Constants.ID)
    public ResponseEntity<String> deleteAppointment(@PathVariable(Constants.PATH_ID) int id) {

        return new ResponseEntity<>(appointmentService.deleteAppointmentById(id), HttpStatus.OK);
    }
}
