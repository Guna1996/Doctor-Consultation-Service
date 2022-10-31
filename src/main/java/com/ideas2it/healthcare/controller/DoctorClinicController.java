/**
 * <p>
 * This package contains classes are DoctorClinicController,
 * PatientController,DoctorController,ClinicController,
 * AppointmentController,FeedbackController,SpecializationController,
 * TimeslotController,VitalController
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.controller;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.common.MessageConstants;
import com.ideas2it.healthcare.dto.DoctorClinicDto;
import com.ideas2it.healthcare.response.Response;
import com.ideas2it.healthcare.service.DoctorClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * Doctor Clinic Controller class is used to assign and delete
 * doctors from clinic and used to get timeslot of a doctor
 * in a clinic.
 * </p>
 *
 * @author Ramachandran
 * @version 1
 * @since 2022-10-10
 */
@RestController
@RequestMapping(Constants.URL_DOCTOR_CLINIC)
public class DoctorClinicController {

    @Autowired
    private DoctorClinicService doctorClinicService;

    /**
     * <p>
     * This method is used to assign doctor to
     * a clinic.
     * </p>
     *
     * @param doctorClinicDto {@link DoctorClinicDto} is details of doctor clinic
     * @return {@link ResponseEntity<Map<String, Object>>}
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> assignDoctorToClinic(@RequestBody DoctorClinicDto doctorClinicDto) {
        return Response.responseEntity(MessageConstants.DOCTOR_ASSIGNED_TO_CLINIC_SUCCESSFULLY,
                doctorClinicService.assignDoctorToClinic(doctorClinicDto),
                HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to remove
     * doctor from a clinic.
     * </p>
     *
     * @param id {@link Integer} id of the doctor object
     * @return {@link Response<String>}
     */
    @PutMapping(Constants.URL_ID)
    public ResponseEntity<String> deleteDoctorFromClinic(@PathVariable(Constants.ID) Integer id) {
        return new ResponseEntity<>(doctorClinicService.deleteDoctorFromClinic(id), HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used get timeslot of a doctor
     * to the clinics.
     * </p>
     *
     * @param doctorId {@link Integer} is id of doctor object
     * @param clinicId {@link Integer} is id of clinic object
     * @return {@link ResponseEntity<Map<String, Object>>}
     */
    @GetMapping(Constants.URL_GET_TIMESLOTS)
    public ResponseEntity<Map<String, Object>> getTimeslots(
            @PathVariable(Constants.DOCTOR_ID) Integer doctorId,
            @PathVariable(Constants.CLINIC_ID) Integer clinicId) {
        return Response.responseEntity(MessageConstants.SUCCESSFULLY_RETRIEVED_ALL_TIMESLOTS,
                doctorClinicService.getTimeslotsByDoctorIdAndClinicId(doctorId, clinicId),
                HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used get available
     * doctors in a particular clinic.
     * </p>
     *
     * @param clinicId {@link Integer}
     * @param pageNumber {@link Integer} is page number
     * @param totalRows {@link Integer} is number of row to be shown
     * @return {@link ResponseEntity<Map<String, Object>>}
     */
    @GetMapping(Constants.URL_GET_DOCTORS_BY_CLINIC_ID)
    public ResponseEntity<Map<String, Object>> getDoctorsByClinicId(
            @PathVariable(Constants.CLINIC_ID) Integer clinicId,
            @PathVariable(Constants.PAGE_NUMBER) Integer pageNumber,
            @PathVariable(Constants.TOTAL_ROWS) Integer totalRows) {
        return Response.responseEntity(MessageConstants.SUCCESSFULLY_RETRIEVED_DOCTORS_IN_CLINIC,
                doctorClinicService.getDoctorsByClinicId(clinicId, pageNumber, totalRows),
                HttpStatus.OK);
    }
}
