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
import com.ideas2it.healthcare.dto.SpecializationDto;
import com.ideas2it.healthcare.exception.NotFoundException;
import com.ideas2it.healthcare.response.SuccessResponse;
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
 * This SpecializationController class is used to
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
    private SuccessResponse successResponse;

    /**
     * <p>
     * This method is used to add details of a Specialization by getting name
     * from the admin
     * </p>
     *
     * @param specializationDto {@link SpecializationDto}is details of specialization
     * @return {@link ResponseEntity}
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> addSpecialization(
            @Valid @RequestBody SpecializationDto specializationDto) {
        return successResponse.responseEntity(MessageConstants.SPECIALIZATION_ADDED_SUCCESSFULLY,
                specializationService.saveSpecialization(specializationDto),
                HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to get all the details
     * of Specialization.
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
        int totalPages = specializationService.countOfSpecializations();
        if (totalPages == 0) {
            throw new NotFoundException(ErrorConstants.SPECIALIZATIONS_NOT_FOUND);
        }
        return successResponse.responseEntity(MessageConstants.SUCCESSFULLY_RETRIEVED_SPECIALIZATIONS,
                specializationService.getAllSpecializations(pageNumber, totalRows),
                HttpStatus.OK, MathUtil.getExactCount(totalPages, totalRows));
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
        return successResponse.responseEntity(MessageConstants.SUCCESSFULLY_RETRIEVED_SPECIALIZATION,
                specializationService.getSpecializationById(id),
                HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to update the details such as name
     * of a Specialization.
     * </p>
     *
     * @param specializationDto {@link SpecializationDto} is details of SpecializationDto
     * @return {@link ResponseEntity}
     */
    @PutMapping
    public ResponseEntity<Map<String, Object>> updateSpecialization(
            @RequestBody SpecializationDto specializationDto) {
        return successResponse.responseEntity(MessageConstants.SPECIALIZATION_UPDATED_SUCCESSFULLY,
                specializationService.updateSpecialization(specializationDto),
                HttpStatus.OK);
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
        return successResponse.responseEntity(specializationService.removeSpecializationById(id),
                null, HttpStatus.OK);
    }
}