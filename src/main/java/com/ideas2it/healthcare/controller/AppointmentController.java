/**
 * <p>
 * This is the package contains classes are Doctor clinic controller,
 * Patient controller, Doctor controller, Clinic controller,
 * Appointment controller, Feedback controller, Specialization controller,
 * Timeslot controller, Patient Vital controller
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.controller;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.common.ErrorConstants;
import com.ideas2it.healthcare.common.MessageConstants;
import com.ideas2it.healthcare.dto.AppointmentDto;
import com.ideas2it.healthcare.response.UserResponse;
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
import org.springframework.web.bind.annotation.RequestParam;
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
    private UserResponse userResponse;

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
    public ResponseEntity<Map<String, ?>> addAppointment(
            @Valid @RequestBody AppointmentDto appointmentDto) {
        String message = appointmentService.addAppointment(appointmentDto);
        if (null == message) {
            message = ErrorConstants.ENTER_VALID_DATE_TIME;
        }
        return userResponse.responseEntity(message, null, HttpStatus.CREATED);
    }

    /**
     * <p>
     * This method is used to reschedule appointment of a patient
     * by getting details such as reschedule date and time
     * for the appointment from the patient
     * </p>
     *
     * @param appointmentDto {@link AppointmentDto} is details of appointment
     * @return {@link ResponseEntity}
     */
    @PutMapping
    public ResponseEntity<Map<String, ?>> rescheduleAppointment(
            @Valid @RequestBody AppointmentDto appointmentDto) {
        String message = appointmentService.rescheduleAppointment(appointmentDto);
        if (null == message) {
            message = ErrorConstants.ENTER_VALID_DATE_TIME;
        }
        return userResponse.responseEntity(message, null, HttpStatus.NO_CONTENT);
    }

    /**
     * <p>
     * This method is used to cancel the appointment of a patient
     * from the appointment list by getting appointment id
     * from the patient
     * </p>
     *
     * @param id {@link Integer} is appointment id
     * @return {@link ResponseEntity}
     */
    @PutMapping(Constants.URL_ID)
    public ResponseEntity<Map<String, ?>> removeAppointment(@PathVariable(Constants.ID) Integer id) {
        String message = appointmentService.removeAppointmentById(id);
        if (null == message) {
            message = ErrorConstants.APPOINTMENT_NOT_FOUND;
        }
        return userResponse.responseEntity(message,
                null, HttpStatus.NO_CONTENT);
    }

    /**
     * <p>
     * This method is used to get appointments of a doctor by doctor id,
     * page number and total rows required. Based on the user input such
     * as page number and total rows total page is calculated and
     * displayed using pagination.
     * </p>
     *
     * @param doctorId   {@link Integer} is id of doctor
     * @param pageNumber {@link Integer} is page number
     * @param totalRows  {@link Integer} is number of row to be shown
     * @return {@link ResponseEntity}
     */
    @GetMapping(Constants.URL_GET_APPOINTMENTS_BY_DOCTOR_ID)
    public ResponseEntity<Map<String, ?>> getAppointmentsByDoctorId(
            @PathVariable(name = Constants.DOCTOR_ID_PATH) Integer doctorId,
            @RequestParam(name = Constants.PAGE_NUMBER) Integer pageNumber,
            @RequestParam(name = Constants.TOTAL_ROWS) Integer totalRows) {
        int totalPages = appointmentService.getCountOfAppointmentByDoctorId(doctorId);
        int pages = MathUtil.pageCount(totalPages, totalRows);
        String message = MessageConstants.SUCCESSFULLY_RETRIEVED_APPOINTMENTS;
        if (pages <= pageNumber) {
            message = ErrorConstants.APPOINTMENTS_NOT_FOUND;
        }
        return userResponse.responseEntity(message, appointmentService
                .getAppointmentsByDoctorId(doctorId, pageNumber, totalRows), HttpStatus
                .PARTIAL_CONTENT, pages);
    }

    /**
     * <p>
     * This method is used to get ist of appointments of a patient
     * by patient id, page number and total rows required. Based on
     * the user input such as page number and total rows total page
     * is calculated and displayed using pagination.
     * </p>
     *
     * @param patientId  {@link Integer} is id of patient
     * @param pageNumber {@link Integer} is page number
     * @param totalRows  {@link Integer} is number of row to be shown
     * @return {@link ResponseEntity}
     */
    @GetMapping(Constants.URL_PATIENT_ID)
    public ResponseEntity<Map<String, ?>> getAppointmentsByPatientId(
            @PathVariable(name = Constants.PATIENT_ID_PATH) Integer patientId,
            @RequestParam(name = Constants.PAGE_NUMBER) Integer pageNumber,
            @RequestParam(name = Constants.TOTAL_ROWS) Integer totalRows) {
        int totalPages = appointmentService.getCountOfAppointmentByPatientId(patientId);
        int pages = MathUtil.pageCount(totalPages, totalRows);
        String message = MessageConstants.SUCCESSFULLY_RETRIEVED_APPOINTMENTS;
        if (pages <= pageNumber) {
            message = ErrorConstants.APPOINTMENTS_NOT_FOUND;
        }
        return userResponse.responseEntity(message, appointmentService
                .getAppointmentsByPatientId(patientId, pageNumber, totalRows), HttpStatus.PARTIAL_CONTENT,
                pages);
    }
}
