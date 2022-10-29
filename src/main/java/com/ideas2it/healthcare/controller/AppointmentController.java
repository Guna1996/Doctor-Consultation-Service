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
    public AppointmentDto addAppointment(@Valid @RequestBody AppointmentDto appointmentDto) {
        return appointmentService.addAppointment(appointmentDto);
    }

    /**
     * <p>
     * This method is used to reschedule appointment
     * of a patient after validating it.
     * </p>
     *
     * @param appointmentDto is details of appointment
     * @return AppointmentDto
     */
    @PutMapping
    public AppointmentDto rescheduleAppointment(@Valid @RequestBody AppointmentDto appointmentDto) {
        return appointmentService.rescheduleAppointment(appointmentDto);
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
    public String deleteAppointment(@PathVariable(Constants.ID) int id) {
        return appointmentService.deleteAppointmentById(id);
    }

    /**
     * <p>
     * This method is used to get appointments
     * of a doctor.
     * </p>
     *
     * @param doctorId
     * @param pageNumber
     * @param totalRows
     * @return List<AppointmentDto>
     */
    @GetMapping(Constants.PATH_APPOINTMENT_ID)
    public List<AppointmentDto> getAppointmentsByDoctorId(@PathVariable(name = Constants.PATH_DOCTOR_ID) int doctorId,
                                                          @PathVariable(name = Constants.PAGE_NUMBER) int pageNumber,
                                                          @PathVariable(name = Constants.TOTAL_ROWS) int totalRows) {
        return appointmentService.getAppointmentsByDoctorId(doctorId, pageNumber, totalRows);
    }

    /**
     * <p>
     * This getAppointmentsByPatientId method is used
     * to get list of appointments of a patient.
     * </p>
     *
     * @param patientId
     * @param pageNumber
     * @param totalRows
     * @return List<AppointmentDto>
     */
    @GetMapping(Constants.PATIENT_APPOINTMENT)
    public List<AppointmentDto> getAppointmentsByPatientId(@PathVariable(name = Constants.PATH_PATIENT_ID) int patientId,
                                                           @PathVariable(name = Constants.PAGE_NUMBER) int pageNumber,
                                                           @PathVariable(name = Constants.TOTAL_ROWS) int totalRows) {
        return appointmentService.getAppointmentsByPatientId(patientId, pageNumber, totalRows);
    }
}
