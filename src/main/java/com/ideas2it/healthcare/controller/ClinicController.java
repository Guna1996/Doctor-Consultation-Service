/**
 * <p>
 * This is the base package for all the controller classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.controller;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.dto.AppointmentDto;
import com.ideas2it.healthcare.dto.ClinicDto;
import com.ideas2it.healthcare.dto.DoctorClinicDto;
import com.ideas2it.healthcare.service.ClinicService;
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
 * This ClinicController class is a Controller class and this
 * class is used to get input and display outputs for clinics
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
     * add new clinics details
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
     * Get all the Clinics
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
     * Get the Clinic by id
     * </p>
     *
     * @param id is clinic id
     * @return ClinicDto
     */
    @GetMapping(Constants.ID)
    public ClinicDto getClinicById(@PathVariable(Constants.PATH_ID) int id) {
        return clinicService.getClinicById(id);
    }

    /**
     * <p>
     * Update clinic record by using it's id
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
     * Delete clinic by Id
     * </p>
     *
     * @param id is clinic id
     * @return String
     */
    @PutMapping(Constants.ID)
    public String deleteClinic(@PathVariable(Constants.PATH_ID) int id) {
        return clinicService.deleteClinicById(id);
    }

    @GetMapping("/appointment/{doctorId}/{pageNumber}/{totalRows}")
    public List<DoctorClinicDto> getAppointmentsByPatientId(@PathVariable(name = "doctorId") int doctorId,
                                                            @PathVariable(name = "pageNumber") int pageNumber,
                                                            @PathVariable(name = "totalRows") int totalRows) {
        return clinicService.getDoctorsByClinicId(doctorId, pageNumber, totalRows);
    }
}
