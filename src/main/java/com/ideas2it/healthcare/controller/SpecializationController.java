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
import com.ideas2it.healthcare.dto.SpecializationDto;
import com.ideas2it.healthcare.exception.CustomException;
import com.ideas2it.healthcare.response.CustomResponse;
import com.ideas2it.healthcare.service.SpecializationService;
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
 * This Specialization Controller class is used to
 * assign specializations to doctors.
 * </p>
 *
 * @author Mohamed Jubair
 * @version 1
 * @since 2022-10-10
 */
@RestController
@RequestMapping(Constants.URL_SPECIALIZATION)
public class SpecializationController {

    @Autowired
    private SpecializationService specializationService;

    @Autowired
    private CustomResponse customResponse;

    /**
     * <p>
     * This method is used to add details of a Specialization by getting name
     * </p>
     *
     * @param specializationDto {@link SpecializationDto}is details of specialization
     * @return {@link ResponseEntity}
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> addSpecialization(
            @Valid @RequestBody SpecializationDto specializationDto) {
        return customResponse.responseEntity(
                specializationService.addOrUpdateSpecialization(specializationDto),
                null,
                HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to get all the details of Specialization.
     * Based on the user input such as page number and total rows
     * total page is calculated and displayed using pagination.
     * </p>
     *
     * @param pageNumber {@link Integer} is page number
     * @param totalRows  {@link Integer} is number of row to be shown
     * @return {@link ResponseEntity}
     */
    @GetMapping(Constants.URL_PAGINATION)
    public ResponseEntity<Map<String, Object>> getAllSpecializations(
            @PathVariable(Constants.PAGE_NUMBER) Integer pageNumber,
            @PathVariable(Constants.TOTAL_ROWS) Integer totalRows) {
        int totalPages = specializationService.getSpecializationsCount();
        int pages = MathUtil.pageCount(totalPages, totalRows);
        if (pages <= pageNumber) {
            throw new CustomException(ErrorConstants.SPECIALIZATIONS_NOT_FOUND);
        }
        return customResponse.responseEntity(MessageConstants.SUCCESSFULLY_RETRIEVED_SPECIALIZATIONS,
                specializationService.getAllSpecializations(pageNumber, totalRows),
                HttpStatus.OK, pages);
    }

    /**
     * <p>
     * This method is used to get details of a particular specialization
     * by getting specialization id
     * </p>
     *
     * @param id {@link Integer} is id of Specialization
     * @return {@link ResponseEntity}
     */
    @GetMapping(Constants.URL_ID)
    public ResponseEntity<Map<String, Object>> getSpecializationById(@PathVariable Integer id) {
        SpecializationDto specializationDto = specializationService.getSpecializationById(id);
        String message = MessageConstants.SUCCESSFULLY_RETRIEVED_SPECIALIZATION;
        if (null == specializationDto) {
            message = ErrorConstants.SPECIALIZATION_NOT_FOUND;
        }
        return customResponse.responseEntity(message, specializationDto, HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to update all the details
     * of a Specialization
     * </p>
     *
     * @param specializationDto {@link SpecializationDto} is id of Specialization
     * @return {@link ResponseEntity}
     */
    @PutMapping
    public ResponseEntity<Map<String, Object>> updateSpecialization(
            @Valid @RequestBody SpecializationDto specializationDto) {
        return customResponse.responseEntity(specializationService.addOrUpdateSpecialization(specializationDto),
                null, HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to remove the details of a Specialization
     * by specialization id
     * </p>
     *
     * @param id {@link Integer} is id of Specialization
     * @return {@link ResponseEntity}
     */
    @PutMapping(Constants.URL_ID)
    public ResponseEntity<Map<String, Object>> removeSpecializationById(@PathVariable Integer id) {
        return customResponse.responseEntity(specializationService.removeSpecializationById(id),
                null, HttpStatus.OK);
    }
}