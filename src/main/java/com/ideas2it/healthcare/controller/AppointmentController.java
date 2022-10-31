/**
 * <p>
 * This is the base package for all the controller classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.controller;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.common.MessageConstants;
import com.ideas2it.healthcare.dto.AppointmentDto;
import com.ideas2it.healthcare.response.Response;
import com.ideas2it.healthcare.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping(Constants.URL_APPOINTMENT)
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    /**
     * <p>
     * This method is used to add appointment of a
     * patient.
     * </p>
     *
     * @param appointmentDto {@link AppointmentDto} is appointment object
     * @return {@link ResponseEntity}
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> addAppointment(
            @Valid @RequestBody AppointmentDto appointmentDto) {
        return Response.responseEntity(MessageConstants.APPOINTMENT_ADDED_SUCCESSFULLY,
                appointmentService.addAppointment(appointmentDto),
                HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to reschedule appointment
     * of a patient.
     * </p>
     *
     * @param appointmentDto {@link AppointmentDto} is details of appointment
     * @return {@link ResponseEntity}
     */
    @PutMapping
    public ResponseEntity<Map<String, Object>> rescheduleAppointment(
            @Valid @RequestBody AppointmentDto appointmentDto) {
        return Response.responseEntity(MessageConstants.APPOINTMENT_RESCHEDULED_SUCCESSFULLY,
                appointmentService.rescheduleAppointment(appointmentDto), HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to cancel the appointment
     * of a patient.
     * </p>
     *
     * @param id {@link Integer} is appointment id
     * @return {@link ResponseEntity}
     */
    @PutMapping(Constants.URL_ID)
    public ResponseEntity<String> deleteAppointment(@PathVariable(Constants.ID) Integer id) {
        return new ResponseEntity<>(appointmentService.deleteAppointmentById(id), HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to get appointments
     * of a doctor.
     * </p>
     *
     * @param doctorId {@link Integer} is id of doctor
     * @param pageNumber {@link Integer} is page number
     * @param totalRows {@link Integer} is number of row to be shown
     * @return {@link ResponseEntity}
     */
    @GetMapping(Constants.URL_GET_APPOINTMENTS_BY_DOCTOR_ID)
    public ResponseEntity<Map<String, Object>> getAppointmentsByDoctorId(
            @PathVariable(name = Constants.DOCTOR_ID) Integer doctorId,
            @PathVariable(name = Constants.PAGE_NUMBER) Integer pageNumber,
            @PathVariable(name = Constants.TOTAL_ROWS) Integer totalRows) {
        return Response.responseEntity(MessageConstants.SUCCESSFULLY_RETRIEVED_APPOINTMENTS,
                appointmentService.getAppointmentsByDoctorId(doctorId, pageNumber, totalRows),
                HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to get
     * list of appointments of a patient.
     * </p>
     *
     * @param patientId {@link Integer} is id of patient
     * @param pageNumber {@link Integer} is page number
     * @param totalRows {@link Integer} is number of row to be shown
     * @return {@link ResponseEntity}
     */
    @GetMapping(Constants.URL_PATIENT_APPOINTMENT)
    public ResponseEntity<Map<String, Object>> getAppointmentsByPatientId(
            @PathVariable(name = Constants.PATIENT_ID) Integer patientId,
            @PathVariable(name = Constants.PAGE_NUMBER) Integer pageNumber,
            @PathVariable(name = Constants.TOTAL_ROWS) Integer totalRows) {
        return Response.responseEntity(MessageConstants.SUCCESSFULLY_RETRIEVED_APPOINTMENTS,
                appointmentService.getAppointmentsByPatientId(patientId, pageNumber, totalRows),
                HttpStatus.OK);
    }
}
