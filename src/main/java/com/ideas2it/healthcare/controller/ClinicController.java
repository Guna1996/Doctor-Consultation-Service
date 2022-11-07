/**
 * <p>
 * This is the base package for all the controller classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.controller;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.common.ErrorConstants;
import com.ideas2it.healthcare.common.MessageConstants;
import com.ideas2it.healthcare.dto.ClinicDto;
import com.ideas2it.healthcare.exception.NotFoundException;
import com.ideas2it.healthcare.response.CustomResponse;
import com.ideas2it.healthcare.service.ClinicService;
import com.ideas2it.healthcare.util.MathUtil;
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
 * This Clinic controller class is used to add a clinic details ,
 * update the clinic details, delete the clinic  and get specific clinic
 * by admin.
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
    
    @Autowired
    private CustomResponse customResponse;

    /**
     * <p>
     * This method is used to add clinic by getting details
     * such as name, door number, street name, etc
     * </p>
     *
     * @param clinicDto {@link ClinicDto} is clinic object
     * @return {@link ResponseEntity}
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> addClinic(@Valid @RequestBody ClinicDto clinicDto) {
        return customResponse.responseEntity(clinicService.addClinic(clinicDto),
                null, HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to get all the details such as name, door number,
     * street name, etc of available clinics. Based on the user input such
     * as page number and total rows total page is calculated and
     * displayed using pagination.
     * </p>
     *
     * @param pageNumber {@link Integer} is page number
     * @param totalRows  {@link Integer} is number of row to be shown
     * @return {@link ResponseEntity}
     */
    @GetMapping(Constants.URL_PAGINATION)
    public ResponseEntity<Map<String, Object>> getClinics(
            @PathVariable(Constants.PAGE_NUMBER) Integer pageNumber,
            @PathVariable(Constants.TOTAL_ROWS) Integer totalRows) {
        int totalPages = clinicService.getCountOfClinics();
        int pages = MathUtil.pageCount(totalPages, totalRows);
        if (pages <= pageNumber) {
            throw new NotFoundException(ErrorConstants.CLINICS_NOT_FOUND);
        }
        return customResponse.responseEntity(MessageConstants.SUCCESSFULLY_RETRIEVED_CLINICS,
                clinicService.getClinics(pageNumber, totalRows), HttpStatus.OK, pages);
    }

    /**
     * <p>
     * This method is used to get a particular clinic detail such as
     * name, door number, street name,etc of active clinic
     * </p>
     *
     * @param id {@link Integer} is clinic id
     * @return {@link ResponseEntity}
     */
    @GetMapping(Constants.URL_ID)
    public ResponseEntity<Map<String, Object>> getClinicById(@PathVariable(Constants.ID) Integer id) {
        return customResponse.responseEntity(MessageConstants.SUCCESSFULLY_RETRIEVED_CLINIC,
                clinicService.getClinicById(id), HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to update the recorded details of a clinic
     * such as name, door number, street name,etc
     * </p>
     *
     * @param clinicDto {@link ClinicDto} is contains clinic details
     * @return {@link ResponseEntity}
     */
    @PutMapping
    public ResponseEntity<Map<String, Object>> updateClinic(@Valid @RequestBody ClinicDto clinicDto) {
        return customResponse.responseEntity(clinicService.updateClinic(clinicDto),
                null, HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to help admin to remove the clinic
     * details by clinic id and it will inactive the column status
     * </p>
     *
     * @param id {@link Integer} is clinic id
     * @return {@link ResponseEntity}
     */
    @PutMapping(Constants.URL_ID)
    public ResponseEntity<Map<String, Object>> removeClinic(@PathVariable(Constants.ID) Integer id) {
        return customResponse.responseEntity(clinicService.removeClinicById(id),
                null, HttpStatus.OK);
    }
}
