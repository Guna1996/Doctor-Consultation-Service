/**
 * <p>
 * This is the base package for all the controller classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.controller;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.common.MessageConstants;
import com.ideas2it.healthcare.dto.ClinicDto;
import com.ideas2it.healthcare.response.Response;
import com.ideas2it.healthcare.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
     * This method is used to add clinic
     * details.
     * </p>
     *
     * @param clinicDto {@link ClinicDto} is clinic object
     * @return {@link ResponseEntity}
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> addClinic(@Valid @RequestBody ClinicDto clinicDto) {
        return Response.responseEntity(MessageConstants.CLINIC_ADDED_SUCCESSFULLY,
                clinicService.addClinic(clinicDto), HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to get all the details
     * of available clinics.
     * </p>
     *
     * @param pageNumber {@link Integer} is page number
     * @param totalRows {@link Integer} is number of row to be shown
     * @return {@link ResponseEntity}
     */
    @GetMapping(Constants.URL_PAGINATION)
    public ResponseEntity<Map<String, Object>> getClinics(
            @PathVariable(Constants.PAGE_NUMBER) Integer pageNumber,
            @PathVariable(Constants.TOTAL_ROWS) Integer totalRows) {
        return Response.responseEntity(MessageConstants.SUCCESSFULLY_RETRIEVED_CLINICS,
                clinicService.getClinics(pageNumber, totalRows), HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to get a particular
     * clinic detail.
     * </p>
     *
     * @param id {@link Integer} is clinic id
     * @return {@link ResponseEntity}
     */
    @GetMapping(Constants.URL_ID)
    public ResponseEntity<Map<String, Object>> getClinicById(@PathVariable(Constants.ID) Integer id) {
        return Response.responseEntity(MessageConstants.SUCCESSFULLY_RETRIEVED_CLINIC,
                clinicService.getClinicById(id), HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to correct the recorded
     * details of a clinic.
     * </p>
     *
     * @param clinicDto {@link ClinicDto} is contains clinic details
     * @return {@link ResponseEntity}
     */
    @PutMapping
    public ResponseEntity<Map<String, Object>> updateClinic(@Valid @RequestBody ClinicDto clinicDto) {
        return Response.responseEntity(MessageConstants.CLINIC_UPDATED_SUCCESSFULLY,
                clinicService.updateClinic(clinicDto), HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to remove the clinic
     * details of a clinic.
     * </p>
     *
     * @param id {@link Integer} is clinic id
     * @return {@link ResponseEntity}
     */
    @PutMapping(Constants.URL_ID)
    public ResponseEntity<String> deleteClinic(@PathVariable(Constants.ID) Integer id) {
        return new ResponseEntity<>(clinicService.deleteClinicById(id), HttpStatus.OK);
    }
}
