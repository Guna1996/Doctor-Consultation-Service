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
     * @return ResponseEntity<Map<String, Object>>
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> addAppointment(@Valid @RequestBody AppointmentDto appointmentDto) {
        return Response.responseEntity(Constants.APPOINTMENT_ADDED_SUCCESSFULLY,
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
     * @return ResponseEntity<Map<String, Object>>
     */
    @PutMapping
    public ResponseEntity<Map<String, Object>> rescheduleAppointment(@Valid @RequestBody AppointmentDto appointmentDto) {
        return Response.responseEntity(Constants.APPOINTMENT_RESCHEDULED_SUCCESSFULLY,
                appointmentService.rescheduleAppointment(appointmentDto),
                HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to cancel the appointment
     * of a patient.
     * </p>
     *
     * @param id {@link Integer} is appointment id
     * @return ResponseEntity<String>
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
     * @param doctorId {@link Integer}
     * @param pageNumber {@link Integer}
     * @param totalRows {@link Integer}
     * @return ResponseEntity<Map<String, Object>>
     */
    @GetMapping(Constants.PATH_APPOINTMENT_ID)
    public ResponseEntity<Map<String, Object>> getAppointmentsByDoctorId(
            @PathVariable(name = Constants.URL_DOCTOR_ID) Integer doctorId,
            @PathVariable(name = Constants.PAGE_NUMBER) Integer pageNumber,
            @PathVariable(name = Constants.TOTAL_ROWS) Integer totalRows) {
        return Response.responseEntity(Constants.SUCCESSFULLY_RETRIEVED_APPOINTMENT,
                appointmentService.getAppointmentsByDoctorId(doctorId, pageNumber, totalRows),
                HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to get
     * list of appointments of a patient.
     * </p>
     *
     * @param patientId {@link Integer}
     * @param pageNumber {@link Integer}
     * @param totalRows {@link Integer}
     * @return ResponseEntity<Map<String, Object>>
     */
    @GetMapping(Constants.PATIENT_APPOINTMENT)
    public ResponseEntity<Map<String, Object>> getAppointmentsByPatientId(
            @PathVariable(name = Constants.PATH_PATIENT_ID) Integer patientId,
            @PathVariable(name = Constants.PAGE_NUMBER) Integer pageNumber,
            @PathVariable(name = Constants.TOTAL_ROWS) Integer totalRows) {
        return Response.responseEntity(Constants.SUCCESSFULLY_RETRIEVED_APPOINTMENT,
                appointmentService.getAppointmentsByPatientId(patientId, pageNumber, totalRows),
                HttpStatus.OK);
    }
}
