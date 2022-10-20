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
import com.ideas2it.healthcare.dto.ClinicDto;
import com.ideas2it.healthcare.dto.DoctorClinicDto;
import com.ideas2it.healthcare.service.DoctorClinicService;
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
 * Doctor Clinic Controller class with the helps of getting inputs
 * from doctor and passing it to Doctor Clinic Service to store the data's
 * into the database and it help to do CRUD operation
 * </p>
 *
 * @author Ramachandran
 * @version 1
 * @since 2022-10-10
 */
@RestController
@RequestMapping("/doctor_clinic")
public class DoctorClinicController {

    @Autowired
    private DoctorClinicService doctorClinicService;

    /**
     * <p>
     * This method is used to assign doctor id into
     * Doctor-Clinic table by getting doctor id which are
     * active in the doctor table
     * </p>
     *
     * @param doctorClinicDto is details of doctor clinic
     * @return DoctorClinicDto as ResponseEntity
     */
    @PostMapping
    public ResponseEntity<DoctorClinicDto> assignDoctorToClinic(@RequestBody DoctorClinicDto doctorClinicDto) {
        DoctorClinicDto assignedDoctorClinicDto = doctorClinicService.assignDoctorToClinic(doctorClinicDto);
        return new ResponseEntity<>(assignedDoctorClinicDto, HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to get all the doctor
     * which are active in the clinic table
     * </p>
     *
     * @param pageNumber is page number to show
     * @param totalRows  is a set of rows to be shown
     * @return List<DoctorClinicDto> as ResponseEntity
     */
    @GetMapping("/{pageNumber}/{totalRows}")
    public ResponseEntity<List<DoctorClinicDto>> getDoctorClinics(@PathVariable("pageNumber") int pageNumber,
                                                                  @PathVariable("totalRows") int totalRows) {
        return new ResponseEntity<>(doctorClinicService.getDoctorClinics(pageNumber, totalRows), HttpStatus.OK);
    }

    /**
     * <p>
     * This deleteDoctorFromClinic method is used to InActive the
     * doctor column in DoctorClinic table
     * </p>
     *
     * @param id - id of the doctor object
     * @return String as ResponseEntity
     */

    @DeleteMapping(Constants.ID)
    public ResponseEntity<String> deleteDoctorFromClinic(@PathVariable(Constants.PATH_ID) int id) {
        return new ResponseEntity<>(doctorClinicService.deleteDoctorFromClinic(id), HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to get doctor clinic object by
     * getting doctor id and patient id from the user
     * </p>
     *
     * @param doctorId is id of doctor object
     * @param clinicId is id of clinic object
     * @return DoctorClinicDto as ResponseEntity
     */
    @GetMapping(Constants.PATIENT_ID_CLINIC_ID)
    public ResponseEntity<DoctorClinicDto> getByDoctorIdAndClinicId(@PathVariable(Constants.PATH_DOCTOR_ID) int doctorId, @PathVariable(Constants.PATH_CLINIC_ID) int clinicId) {
        return new ResponseEntity<>(doctorClinicService.getByDoctorIdAndClinicId(doctorId, clinicId), HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to update doctor id into
     * Doctor-Clinic table by getting doctor id which are
     * active in the doctor table
     * </p>
     *
     * @param doctorClinicDto is details of doctor clinic
     * @return DoctorClinicDto as ResponseEntity
     */
    @PutMapping
    public ResponseEntity<DoctorClinicDto> updateClinic(@Valid @RequestBody DoctorClinicDto doctorClinicDto) {
        return new ResponseEntity<>(doctorClinicService.updateDoctorClinic(doctorClinicDto), HttpStatus.OK);
    }
}
