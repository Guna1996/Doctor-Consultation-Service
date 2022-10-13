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
import com.ideas2it.healthCare.dto.DoctorDto;
import com.ideas2it.healthCare.model.Doctor;
import com.ideas2it.healthCare.model.DoctorClinic;
import com.ideas2it.healthCare.service.DoctorClinicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctorclinic")
public class DoctorClinicController {

    private final DoctorClinicService doctorClinicService;

    private final DoctorClinicDto doctorClinicDto;
    @PostMapping
    public ResponseEntity<DoctorClinicDto> assignDoctorToClinic (@RequestBody DoctorClinicDto doctorClinicDto) {
        DoctorClinicDto assignedDoctorClinicDto = doctorClinicService.assignDoctorToClinic(doctorClinicDto);
        return new ResponseEntity<>(assignedDoctorClinicDto, HttpStatus.OK);
    }


}
