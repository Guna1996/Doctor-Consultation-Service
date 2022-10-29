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
import com.ideas2it.healthcare.response.Response;
import com.ideas2it.healthcare.service.ClinicService;
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

import javax.validation.Valid;
import java.util.Map;


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
@RequestMapping(Constants.URL_CLINIC)
@RestController
public class ClinicController {

    @Autowired
    private ClinicService clinicService;

    /**
     * <p>
     * This method is used to add clinic details into database.
     * </p>
     *
     * @param clinicDto is clinic object
     * @return ClinicDto
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> addClinic(@Valid @RequestBody ClinicDto clinicDto) {
        return Response.responseEntity(Constants.CLINIC_ADDED_SUCCESSFULLY,
                clinicService.addClinic(clinicDto),
                HttpStatus.OK);
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
    @GetMapping(Constants.PAGINATION)
    public ResponseEntity<Map<String, Object>> getClinics(@PathVariable(Constants.PAGE_NUMBER) int pageNumber,
                                      @PathVariable(Constants.TOTAL_ROWS) int totalRows) {
        return Response.responseEntity(Constants.SUCCESSFULLY_RETRIEVED_CLINICS,
                clinicService.getClinics(pageNumber, totalRows),
                HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to get a particular
     * clinic detail by clinic id
     * </p>
     *
     * @param id is clinic id
     * @return ClinicDto
     */
    @GetMapping(Constants.URL_ID)
    public ResponseEntity<Map<String, Object>> getClinicById(@PathVariable(Constants.ID) int id) {
        return Response.responseEntity(Constants.SUCCESSFULLY_RETRIEVED_CLINIC,
                clinicService.getClinicById(id),
                HttpStatus.OK);
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
    public ResponseEntity<Map<String, Object>> updateClinic(@Valid @RequestBody ClinicDto clinicDto) {
        return Response.responseEntity(Constants.CLINIC_UPDATED_SUCCESSFULLY,
                clinicService.updateClinic(clinicDto),
                HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to remove the clinic
     * details of a clinic
     * </p>
     *
     * @param id is clinic id
     * @return String
     */
    @PutMapping(Constants.URL_ID)
    public ResponseEntity<String> deleteClinic(@PathVariable(Constants.ID) int id) {
        return new ResponseEntity<>(clinicService.deleteClinicById(id), HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to all doctors
     * who are worked in a clinic.
     * </p>
     *
     * @param clinicId is id of clinic
     * @param pageNumber is page number to show
     * @param totalRows  is a set of rows to be shown
     * @return List<DoctorClinicDto>
     */
    @GetMapping(Constants.GET_DOCTOR_BY_CLINIC_ID_PATH)
    public ResponseEntity<Map<String, Object>> getDoctorsByClinicId(@PathVariable(Constants.URL_CLINIC_ID) int clinicId,
                                                                    @PathVariable(Constants.PAGE_NUMBER) int pageNumber,
                                                                    @PathVariable(Constants.TOTAL_ROWS) int totalRows){
        return Response.responseEntity("Success",
                clinicService.getDoctorsByClinicId(clinicId, pageNumber, totalRows),
                HttpStatus.OK);
    }
}
