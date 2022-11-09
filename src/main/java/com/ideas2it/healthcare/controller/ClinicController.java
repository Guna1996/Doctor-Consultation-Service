/**
 * <p>
 * This is the package contains classes are Doctor clinic controller,
 * Patient controller, Doctor controller, Clinic controller,
 * Appointment controller, Feedback controller, Specialization controller,
 * Timeslot controller, Patient Vital controller
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.controller;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.common.ErrorConstants;
import com.ideas2it.healthcare.common.MessageConstants;
import com.ideas2it.healthcare.dto.ClinicDto;
import com.ideas2it.healthcare.response.UserResponse;
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
import org.springframework.web.bind.annotation.RequestParam;
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
    private UserResponse userResponse;

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
    public ResponseEntity<Map<String, ?>> addClinic(@Valid @RequestBody ClinicDto clinicDto) {
        return userResponse.responseEntity(clinicService.addClinic(clinicDto),
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
    @GetMapping
    public ResponseEntity<Map<String, ?>> getClinics(
            @RequestParam(name = Constants.PAGE_NUMBER) Integer pageNumber,
            @RequestParam(name = Constants.TOTAL_ROWS) Integer totalRows) {
        int totalPages = clinicService.getCountOfClinics();
        int pages = MathUtil.pageCount(totalPages, totalRows);
        String message = MessageConstants.SUCCESSFULLY_RETRIEVED_CLINICS;
        if (pages <= pageNumber) {
            message = ErrorConstants.CLINICS_NOT_FOUND;
        }
        return userResponse.responseEntity(message,
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
    public ResponseEntity<Map<String, ?>> getClinicById(@PathVariable(Constants.ID) Integer id) {
        String message = MessageConstants.SUCCESSFULLY_RETRIEVED_CLINIC;
        ClinicDto clinicDto = clinicService.getClinicById(id);
        if (null == clinicDto) {
            message = ErrorConstants.CLINIC_NOT_FOUND;
        }
        return userResponse.responseEntity(message,
                clinicDto, HttpStatus.OK);
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
    public ResponseEntity<Map<String, ?>> updateClinic(@Valid @RequestBody ClinicDto clinicDto) {
        return userResponse.responseEntity(clinicService.updateClinic(clinicDto),
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
    public ResponseEntity<Map<String, ?>> removeClinic(@PathVariable(Constants.ID) Integer id) {
        return userResponse.responseEntity(clinicService.removeClinicById(id),
                null, HttpStatus.OK);
    }
}
