/**
 * <p>
 * This is the base package for all the controller classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.controller;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.dto.ClinicDto;
import com.ideas2it.healthcare.service.ClinicService;
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
 * This ClinicController class is used to add,
 * update, delete and get specific clinic.
 * </p>
 *
 * @author Gunaseelan K
 * @version 1
 * @since 2022-10-10
 */
@RequestMapping("/clinic")
@RestController
public class ClinicController {

    @Autowired
    private ClinicService clinicService;

    /**
     * <p>
     * This method is used to add clinic by getting
     * information from a doctor after validating it.
     * </p>
     *
     * @param clinicDto is clinic object
     * @return ClinicDto
     */
    @PostMapping
    public ClinicDto addClinic(@Valid @RequestBody ClinicDto clinicDto) {
        return clinicService.addClinic(clinicDto);
    }

    /**
     * <p>
     * This method is used to get all the details
     * of available clinics.
     * </p>
     *
     * @param pageNumber is page number to show
     * @param totalRows  is a set of rows to be shown
     * @return List<ClinicDto>
     */
    @GetMapping(Constants.PAGE_PATH)
    public List<ClinicDto> getClinics(@PathVariable(Constants.PAGE_NUMBER) int pageNumber,
                                      @PathVariable(Constants.TOTAL_ROWS) int totalRows) {
        return clinicService.getClinics(pageNumber, totalRows);
    }

    /**
     * <p>
     * This method is used to get a particular
     * clinic to assign to a particular
     * doctor.
     * </p>
     *
     * @param id is clinic id
     * @return ClinicDto
     */
    @GetMapping(Constants.PATH_ID)
    public ClinicDto getClinicById(@PathVariable(Constants.ID) int id) {
        return clinicService.getClinicById(id);
    }

    /**
     * <p>
     * This method is used to correct the recorded
     * details of a clinic after validating it.
     * </p>
     *
     * @param clinicDto is Loan object
     * @return ClinicDto
     */
    @PutMapping
    public ClinicDto updateClinic(@Valid @RequestBody ClinicDto clinicDto) {
        return clinicService.updateClinic(clinicDto);
    }

    /**
     * <p>
     * This method is used to remove the unwanted
     * details of a clinic
     * </p>
     *
     * @param id is clinic id
     * @return String
     */
    @PutMapping(Constants.PATH_ID)
    public String deleteClinic(@PathVariable(Constants.ID) int id) {
        return clinicService.deleteClinicById(id);
    }

    /**
     * <p>
     * This method is used to all doctors
     * who are worked in a clinic.
     * </p>
     *
     * @param doctorId
     * @param pageNumber
     * @param totalRows
     * @return List<DoctorClinicDto>
     */
    @GetMapping(Constants.GET_DOCTOR_BY_CLINIC_ID_PATH)
    public List<DoctorClinicDto> getDoctorsByClinicId(@PathVariable(Constants.PATH_DOCTOR_ID) int doctorId,
                                                      @PathVariable(Constants.PAGE_NUMBER) int pageNumber,
                                                      @PathVariable(Constants.TOTAL_ROWS) int totalRows){
        return clinicService.getDoctorsByClinicId(doctorId, pageNumber, totalRows);
    }
}
