/**
 * <p>
 * This package contains classes are DoctorClinicController,
 * PatientController,DoctorController,ClinicController,
 * AppointmentController,FeedbackController,SpecializationController,
 * TimeslotController,VitalsController
 * </p>
 *
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.controller;

import com.ideas2it.healthcare.dto.DoctorClinicDto;
import com.ideas2it.healthcare.service.DoctorClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * DoctorClinnicController class with the helps of getting inputs
 * from doctor and passing it to DoctorClinicservice to store the data's
 * into the database and it help to do CRUD operation
 * </p>
 *
 * @author Ramachandran
 *
 * @version 1
 *
 * @since 2022-10-18
 */
@RestController
@RequestMapping("/doctor-clinic")
public class DoctorClinicController {

    @Autowired
    private DoctorClinicService doctorClinicService;

    /**
     * <p>
     * This method is used to assign doctor id into
     * Doctor-Clinic table by getting doctor id which are
     * active in the doctor table
     *
     * </p>
     *
     * @param doctorClinicDto
     *
     * @return DoctorClinicDto
     */
    @PostMapping
    public ResponseEntity<DoctorClinicDto> assignDoctorToClinic (@RequestBody DoctorClinicDto doctorClinicDto) {
        DoctorClinicDto assignedDoctorClinicDto = doctorClinicService.assignDoctorToClinic(doctorClinicDto);
        return new ResponseEntity<>(assignedDoctorClinicDto, HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to get all the doctor
     * which are active in the clinic table
     * </p>
     *
     * @return List<DoctorClinicDto>
     *
     */
    @GetMapping
    public ResponseEntity<List<DoctorClinicDto>> getDoctorClinics() {
        List<DoctorClinicDto> getsDoctorClinics = doctorClinicService.getDoctorClinics();
        return new ResponseEntity<>(doctorClinicService.getDoctorClinics(), HttpStatus.OK);
    }

    /**
     * <p>
     * This deleteDoctorFromClinic method is used to InActive the
     * doctor column in DoctorClinic table
     * </p>
     *
     * @param id - id of the doctor
     *
     * @return String
     * 
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoctorFromClinic(@PathVariable("id") int id) {
        return new ResponseEntity<>(doctorClinicService.deleteDoctorFromClinic(id), HttpStatus.OK);
    }

    @GetMapping(path = "/{doctorId}/{clinicId}")
    public ResponseEntity<DoctorClinicDto> getByDoctorIdAndClinicId(@PathVariable("doctorId") int doctorId, @PathVariable("clinicId") int clinicId) {
        return new ResponseEntity<>(doctorClinicService.getByDoctorIdAndClinicId(doctorId, clinicId), HttpStatus.OK);
    }
}
