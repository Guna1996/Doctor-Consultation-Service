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
import com.ideas2it.healthcare.dto.FeedbackDto;
import com.ideas2it.healthcare.response.UserResponse;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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
    private UserResponse userResponse;

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
    public ResponseEntity<Map<String, ?>> addFeedback(@Valid @RequestBody FeedbackDto feedbackDto) {
        return userResponse.responseEntity(feedbackService.addFeedback(feedbackDto),
                null,
                HttpStatus.CREATED);
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
    public ResponseEntity<Map<String, ?>> removeFeedbackById(@PathVariable(Constants.ID) Integer id) {
        String message = feedbackService.deleteFeedback(id);
        if (null == message) {
            message = ErrorConstants.FEEDBACK_NOT_FOUND;
        }
        return userResponse.responseEntity(message, null, HttpStatus.NO_CONTENT);
    }

    /**
     * <p>
     * This method is used to get feedbacks along with pagination of a doctor
     * by getting doctor id, page number and total rows required. Based on the
     * user input such as page number and total rows total page is calculated
     * and displayed using pagination.
     * </p>
     *
     * @param doctorId   {@link Integer} is id of doctor
     * @param pageNumber {@link Integer} is page number
     * @param totalRows  {@link Integer} is number of row to be shown
     * @return {@link ResponseEntity}
     */
    @GetMapping(Constants.URL_GET_FEEDBACKS_BY_DOCTOR_ID)
    public ResponseEntity<Map<String, ?>> getFeedbacksByDoctorId(
            @PathVariable(name = Constants.DOCTOR_ID_PATH) Integer doctorId,
            @RequestParam(name = Constants.PAGE_NUMBER) Integer pageNumber,
            @RequestParam(name = Constants.TOTAL_ROWS) Integer totalRows) {
        int totalPages = feedbackService.getFeedbacksCountByDoctorId(doctorId);
        int pages = MathUtil.pageCount(totalPages, totalRows);
        String message = MessageConstants.SUCCESSFULLY_RETRIEVED_FEEDBACK_FOR_DOCTOR;
        if (0 >= totalPages) {
            message = ErrorConstants.DOCTOR_NOT_FOUND;
        } else if (pages <= pageNumber) {
            message = ErrorConstants.FEEDBACKS_NOT_FOUND;
        }
        return userResponse.responseEntity(message,
                feedbackService.getFeedbacksByDoctorId(doctorId, pageNumber, totalRows),
                HttpStatus.PARTIAL_CONTENT, MathUtil.pageCount(totalPages, totalRows));
    }
}
