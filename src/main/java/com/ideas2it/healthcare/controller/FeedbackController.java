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
import com.ideas2it.healthcare.dto.FeedbackDto;
import com.ideas2it.healthcare.exception.NotFoundException;
import com.ideas2it.healthcare.response.CustomResponse;
import com.ideas2it.healthcare.service.FeedbackService;
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

import java.util.Map;

/**
 * <p>
 * This Feedback controller class is used to add the feedback of
 * patient given regard the doctor and also we can get the remove the
 * specific feedback of the doctor and also we can get all the feedbacks
 * of doctor.
 * </p>
 *
 * @author Bala Ashwanth
 * @version 1
 * @since 2022-10-10
 */
@RestController
@RequestMapping(Constants.URL_FEEDBACK)
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private CustomResponse customResponse;

    /**
     * <p>
     * This method is used to add a feedback from a patient by getting details
     * such as comments, rating, doctor id and patient id from the patient
     * </p>
     *
     * @param feedbackDto {@link FeedbackDto} is contains details of feedback
     * @return {@link ResponseEntity}
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> addFeedback(@RequestBody FeedbackDto feedbackDto) {
        return customResponse.responseEntity(MessageConstants.FEEDBACK_ADDED_SUCCESSFULLY,
                feedbackService.addFeedback(feedbackDto),
                HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to remove feedback of a patient by
     * getting feedback id and it will delete the feedback like
     * inactive the column of the status
     * </p>
     *
     * @param id {@link Integer} is an integer that refer id in database
     * @return {@link ResponseEntity}
     */
    @PutMapping(Constants.URL_ID)
    public ResponseEntity<Map<String, Object>> removeFeedbackById(@PathVariable(Constants.ID) Integer id) {
        return customResponse.responseEntity(feedbackService.deleteFeedback(id),
                null, HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to get feedbacks along with pagination of a doctor
     * by getting doctor id, page number and total rows required
     * </p>
     *
     * @param doctorId   {@link Integer} is id of doctor
     * @param pageNumber {@link Integer} is page number
     * @param totalRows  {@link Integer} is number of row to be shown
     * @return {@link ResponseEntity}
     */
    @GetMapping(Constants.URL_GET_FEEDBACKS_BY_DOCTOR_ID)
    public ResponseEntity<Map<String, Object>> getFeedbacksByDoctorId(
            @PathVariable(name = Constants.DOCTOR_ID_PATH) Integer doctorId,
            @PathVariable(name = Constants.PAGE_NUMBER) Integer pageNumber,
            @PathVariable(name = Constants.TOTAL_ROWS) Integer totalRows) {
        int totalPages = feedbackService.countOfFeedbacksByDoctorId(doctorId);
        if (0 == totalPages) {
            throw new NotFoundException(ErrorConstants.FEEDBACKS_NOT_FOUND);
        }
        return customResponse.responseEntity(MessageConstants.SUCCESSFULLY_RETRIEVED_FEEDBACK_FOR_DOCTOR,
                feedbackService.getFeedbackByDoctorId(doctorId, pageNumber, totalRows),
                HttpStatus.OK, MathUtil.pageCount(totalPages, totalRows));
    }
}
