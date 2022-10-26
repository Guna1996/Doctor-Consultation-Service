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
@RequestMapping("/doctor-clinic")
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
     * @return DoctorClinicDto
     */
    @PostMapping
    public DoctorClinicDto assignDoctorToClinic(@RequestBody DoctorClinicDto doctorClinicDto) {
        return doctorClinicService.assignDoctorToClinic(doctorClinicDto);
    }

    /**
     * <p>
     * This method is used to get all the doctor
     * which are active in the clinic table
     * </p>
     *
     * @param pageNumber is page number to show
     * @param totalRows  is a set of rows to be shown
     * @return List<DoctorClinicDto>
     */
    @GetMapping(Constants.PAGE_PATH)
    public List<DoctorClinicDto> getDoctorClinics(@PathVariable(Constants.PAGE_NUMBER) int pageNumber,
                                                                  @PathVariable(Constants.TOTAL_ROWS) int totalRows) {
        return doctorClinicService.getDoctorClinics(pageNumber, totalRows);
    }

    /**
     * <p>
     * This deleteDoctorFromClinic method is used to InActive the
     * doctor column in DoctorClinic table
     * </p>
     *
     * @param id - id of the doctor object
     * @return String
     */

    @PutMapping(Constants.ID)
    public String deleteDoctorFromClinic(@PathVariable(Constants.PATH_ID) int id) {
        return doctorClinicService.deleteDoctorFromClinic(id);
    }

    /**
     * <p>
     * This method is used to get doctor clinic object by
     * getting doctor id and patient id from the user
     * </p>
     *
     * @param doctorId is id of doctor object
     * @param clinicId is id of clinic object
     * @return DoctorClinicDto
     */
    @GetMapping(Constants.PATIENT_ID_CLINIC_ID)
    public DoctorClinicDto getTimeslots
    (@PathVariable(Constants.PATH_DOCTOR_ID) int doctorId, @PathVariable(Constants.PATH_CLINIC_ID) int clinicId) {
        return doctorClinicService.getTimeslotsByDoctorIdAndClinicId(doctorId, clinicId);
    }

    /**
     * <p>
     * This method is used to update doctor id into
     * Doctor-Clinic table by getting doctor id which are
     * active in the doctor table
     * </p>
     *
     * @param doctorClinicDto is details of doctor clinic
     * @return DoctorClinicDto
     */
    @PutMapping
    public DoctorClinicDto updateClinic(@Valid @RequestBody DoctorClinicDto doctorClinicDto) {
        return doctorClinicService.updateDoctorClinic(doctorClinicDto);
    }

    /**
     * <p>
     * Get the Doctor Clinic by id
     * </p>
     *
     * @param id is doctorClinic id
     * @return DoctorClinicDto
     */
    @GetMapping(Constants.ID)
    public DoctorClinicDto getDoctorClinicById(@PathVariable(Constants.PATH_ID) int id) {
        return doctorClinicService.getDoctorClinicById(id);
    }
}
