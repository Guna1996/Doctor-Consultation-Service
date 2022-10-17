/**
 * <p>
 * This is the package contains classes are DoctorClinicController,
 * PatientController,DoctorController,ClinicController,
 * AppointmentController,FeedbackController,SpecializationController,
 * TimeslotController,VitalsController
 * </p>
 *
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthCare.controller;

import com.ideas2it.healthCare.dto.DoctorClinicDto;
import com.ideas2it.healthCare.service.DoctorClinicService;
import lombok.RequiredArgsConstructor;
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

/**
 *
 */
@RestController
@RequestMapping("/doctorclinic")
public class DoctorClinicController {

    @Autowired
    private DoctorClinicService doctorClinicService;

    @PostMapping
    public ResponseEntity<DoctorClinicDto> assignDoctorToClinic (@RequestBody DoctorClinicDto doctorClinicDto) {
        DoctorClinicDto assignedDoctorClinicDto = doctorClinicService.assignDoctorToClinic(doctorClinicDto);
        return new ResponseEntity<>(assignedDoctorClinicDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DoctorClinicDto>> getDoctorClinics() {
        List<DoctorClinicDto> getsDoctorClinics = doctorClinicService.getDoctorClinics();
        return new ResponseEntity<>(doctorClinicService.getDoctorClinics(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<DoctorClinicDto> deleteDoctorFromClinic(@RequestBody DoctorClinicDto doctorClinicDto) {
        return new ResponseEntity<>(doctorClinicService.updateDoctorClinic(doctorClinicDto), HttpStatus.OK);
    }
}
