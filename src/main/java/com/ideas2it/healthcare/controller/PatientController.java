/**
 * <p>
 * This is the package contains classes are DoctorClinicController,
 * PatientController,DoctorController,ClinicController,
 * AppointmentController,FeedbackController,SpecializationController,
 * TimeslotController,VitalController
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.controller;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.dto.AppointmentDto;
import com.ideas2it.healthcare.dto.PatientDto;
import com.ideas2it.healthcare.dto.VitalDto;
import com.ideas2it.healthcare.response.Response;
import com.ideas2it.healthcare.service.PatientService;
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

import java.util.List;
import java.util.Map;

/**
 * <p>
 * This PatientController class is used to get and update
 * information from the patient and also used to get
 * their vitals and appointments.
 * </p>
 *
 * @author Ramachandran
 * @version 1
 * @since 2022-10-10
 */
@RestController
@RequestMapping(Constants.URL_PATIENT)
public class PatientController {

    @Autowired
    private PatientService patientService;

    /**
     * <p>
     * This addPatient method is used to add patient details
     * by getting inputs from the patient.
     * </p>
     *
     * @param patientDto is patient detail
     * @return String
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> addPatient(@RequestBody PatientDto patientDto) {
        return Response.responseEntity("Success", patientService.addPatient(patientDto), HttpStatus.OK);
    }

    /**
     * <p>
     * This getPatientById method is used to get
     * a details of a patient.
     * </p>
     *
     * @param id is patient id
     * @return PatientDto
     */
    @GetMapping(Constants.URL_ID)
    public ResponseEntity<Map<String, Object>> getPatientById(@PathVariable int id) {
        return Response.responseEntity("Success", patientService.getPatientById(id), HttpStatus.OK);
    }

    /**
     * <p>
     * This updatePatient method is used to update patient
     * detail after validating it.
     * </p>
     *
     * @param patientDto is detail of patient
     * @return PatientDto
     */
    @PutMapping
    public ResponseEntity<Map<String, Object>> updatePatient(@RequestBody PatientDto patientDto) {
        return Response.responseEntity("Success", patientService.updatePatient(patientDto), HttpStatus.OK);
    }


    /**
     * <p>
     * This getVitalByPatientId method is used to
     * get vitals of patient.
     * </p>
     *
     * @param patientId is id of patient
     * @param pageNumber is page number to show
     * @param totalRows  is a set of rows to be shown
     * @return List<VitalDto>
     */
    @GetMapping(Constants.VITAL_PATIENT_ID + Constants.PAGINATION)
    public ResponseEntity<Map<String, Object>> getVitalByPatientId(@PathVariable(name = Constants.PATH_PATIENT_ID) int patientId,
                                              @PathVariable(name = Constants.PAGE_NUMBER) int pageNumber,
                                              @PathVariable(name = Constants.TOTAL_ROWS) int totalRows) {
        return Response.responseEntity("Success",
                patientService.getVitalsByPatientId(patientId, pageNumber, totalRows),
                HttpStatus.OK);
    }

    /**
     * <p>
     * This getAppointmentsByPatientId method is used
     * to get list of appointments of a patient.
     * </p>
     *
     * @param patientId is id of patient
     * @param pageNumber is page number to show
     * @param totalRows  is a set of rows to be shown
     * @return List<AppointmentDto>
     */
    @GetMapping(Constants.PATIENT_APPOINTMENT)
    public ResponseEntity<Map<String, Object>> getAppointmentsByPatientId(@PathVariable(name = Constants.PATH_PATIENT_ID) int patientId,
                                                           @PathVariable(name = Constants.PAGE_NUMBER) int pageNumber,
                                                           @PathVariable(name = Constants.TOTAL_ROWS) int totalRows) {
        return Response.responseEntity("Success",
                patientService.getAppointmentsByPatientId(patientId, pageNumber, totalRows),
                HttpStatus.OK);
    }
}
