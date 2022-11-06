/**
 * <p>
 * This is the base package for all the controller classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.controller;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.common.ErrorConstants;
import com.ideas2it.healthcare.common.MessageConstants;
import com.ideas2it.healthcare.dto.AppointmentDto;
import com.ideas2it.healthcare.exception.NotFoundException;
import com.ideas2it.healthcare.response.CustomResponse;
import com.ideas2it.healthcare.service.AppointmentService;
import com.ideas2it.healthcare.util.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
 * This Appointment controller class is used to fix,
 * reschedule and cancel the appointment by patient.
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

    @Autowired
    private CustomResponse customResponse;

    /**
     * <p>
     * This method is used to add appointment of a patient
     * by getting details such as scheduled on, patient id,
     * doctor id, clinic id, etc from the patient
     * </p>
     *
     * @param appointmentDto {@link AppointmentDto} is appointment object
     * @return {@link ResponseEntity}
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> addAppointment(
            @Valid @RequestBody AppointmentDto appointmentDto) {
        return customResponse.responseEntity(MessageConstants.APPOINTMENT_ADDED_SUCCESSFULLY,
                appointmentService.addAppointment(appointmentDto),
                HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to reschedule appointment of a patient by getting
     * details such as reschedule date and time from the patient
     * </p>
     *
     * @param appointmentDto {@link AppointmentDto} is details of appointment
     * @return {@link ResponseEntity}
     */
    @PutMapping
    public ResponseEntity<Map<String, Object>> rescheduleAppointment(
            @Valid @RequestBody AppointmentDto appointmentDto) {
        return customResponse.responseEntity(MessageConstants.APPOINTMENT_RESCHEDULED_SUCCESSFULLY,
                appointmentService.rescheduleAppointment(appointmentDto), HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to cancel the appointment of a patient
     * by getting appointment id from the patient
     * </p>
     *
     * @param id {@link Integer} is appointment id
     * @return {@link ResponseEntity}
     */
    @PutMapping(Constants.URL_ID)
    public ResponseEntity<Map<String, Object>> removeAppointment(@PathVariable(Constants.ID) Integer id) {
        return customResponse.responseEntity(appointmentService.removeAppointmentById(id),
                null, HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to get appointments of a doctor by doctor id,
     * page number and total rows required
     * </p>
     *
     * @param doctorId   {@link Integer} is id of doctor
     * @param pageNumber {@link Integer} is page number
     * @param totalRows  {@link Integer} is number of row to be shown
     * @return {@link ResponseEntity}
     */
    @GetMapping(Constants.URL_GET_APPOINTMENTS_BY_DOCTOR_ID)
    public ResponseEntity<Map<String, Object>> getAppointmentsByDoctorId(
            @PathVariable(name = Constants.DOCTOR_ID_PATH) Integer doctorId,
            @PathVariable(name = Constants.PAGE_NUMBER) Integer pageNumber,
            @PathVariable(name = Constants.TOTAL_ROWS) Integer totalRows) {
        int totalPages = appointmentService.countOfAppointmentByDoctorId(doctorId);
        System.out.println(totalPages);
        int pages = MathUtil.pageCount(totalPages, totalRows);
        if (pages <= pageNumber) {
            throw new NotFoundException(ErrorConstants.APPOINTMENTS_NOT_FOUND);
        }
        return customResponse.responseEntity(MessageConstants
                .SUCCESSFULLY_RETRIEVED_APPOINTMENTS, appointmentService
                .getAppointmentsByDoctorId(doctorId, pageNumber, totalRows), HttpStatus
                .OK,pages);
    }

    /**
     * <p>
     * This method is used to get ist of appointments of a patient
     * by patient id, page number and total rows required
     * </p>
     *
     * @param patientId  {@link Integer} is id of patient
     * @param pageNumber {@link Integer} is page number
     * @param totalRows  {@link Integer} is number of row to be shown
     * @return {@link ResponseEntity}
     */
    @GetMapping(Constants.URL_GET_APPOINTMENTS_BY_PATIENT_ID)
    public ResponseEntity<Map<String, Object>> getAppointmentsByPatientId(
            @PathVariable(name = Constants.PATIENT_ID_PATH) Integer patientId,
            @PathVariable(name = Constants.PAGE_NUMBER) Integer pageNumber,
            @PathVariable(name = Constants.TOTAL_ROWS) Integer totalRows) {
        int totalPages = appointmentService.countOfAppointmentByPatientId(patientId);
        int pages = MathUtil.pageCount(totalPages, totalRows);
        if (pages <= pageNumber) {
            throw new NotFoundException(ErrorConstants.APPOINTMENTS_NOT_FOUND);
        }
        return customResponse.responseEntity(MessageConstants
                .SUCCESSFULLY_RETRIEVED_APPOINTMENTS, appointmentService
                .getAppointmentsByPatientId(patientId, pageNumber, totalRows), HttpStatus.OK, pages);
    }
}
