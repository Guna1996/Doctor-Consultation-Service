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
import com.ideas2it.healthcare.dto.DoctorClinicDto;
import com.ideas2it.healthcare.service.DoctorClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
@RequestMapping("/doctor-clinic")
public class DoctorClinicController {

    @Autowired
    private DoctorClinicService doctorClinicService;

    /**
     * <p>
     * This method is used to assign doctor to
     * a clinic.
     * </p>
     *
     * @param doctorClinicDto is details of doctor clinic
     * @return DoctorClinicDto
     */
    @PostMapping
    public DoctorClinicDto assignDoctorToClinic(@RequestBody DoctorClinicDto doctorClinicDto) {
        return doctorClinicService.assignDoctorToClinic(doctorClinicDto);
    }

    /**
     * <p>
     * This deleteDoctorFromClinic method is used to remove
     * doctor from a clinic.
     * </p>
     *
     * @param id - id of the doctor object
     * @return String
     */
    @PutMapping(Constants.PATH_ID)
    public String deleteDoctorFromClinic(@PathVariable(Constants.ID) int id) {
        return doctorClinicService.deleteDoctorFromClinic(id);
    }

    /**
     * <p>
     * This method is used get timeslot of a doctor
     * to the clinics.
     * </p>
     *
     * @param doctorId is id of doctor object
     * @param clinicId is id of clinic object
     * @return DoctorClinicDto
     */
    @GetMapping(Constants.PATIENT_ID_CLINIC_ID)
    public DoctorClinicDto getTimeslots(@PathVariable(Constants.PATH_DOCTOR_ID) int doctorId,
                                        @PathVariable(Constants.PATH_CLINIC_ID) int clinicId) {
        return doctorClinicService.getTimeslotsByDoctorIdAndClinicId(doctorId, clinicId);
    }

    @GetMapping(Constants.GET_DOCTOR_BY_CLINIC_ID_PATH)
    public List<DoctorClinicDto> getDoctorsByClinicId(@PathVariable(Constants.PATH_CLINIC_ID) int clinicId,
                                                      @PathVariable(Constants.PAGE_NUMBER) int pageNumber,
                                                      @PathVariable(Constants.TOTAL_ROWS) int totalRows) {
        return doctorClinicService.getDoctorsByClinicId(clinicId, pageNumber, totalRows);
    }
}
