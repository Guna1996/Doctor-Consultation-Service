/**
 * <p>
 * This package contains interfaces of Doctor clinic service,
 * Patient service, Doctor service, Clinic service,
 * Appointment service, Feedback service, Specialization service,
 * Timeslot service, Patient vital service.
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.service;

import com.ideas2it.healthcare.dto.FeedbackDto;

import java.util.List;

/**
 * <p>
 * Feedback service interface consists of abstract methods which is used
 * for performing crud operation. it is used to transfer objects between
 * controller and repository
 * </p>
 *
 * @author Bala Ashwanth
 * @since 2022-10-10
 */
public interface FeedbackService {

    /**
     * <p>
     * This method is used to add feedback
     * of doctor, patient given feedback details
     * such as comments, ratings for the doctor
     * these details are added into the database
     * </p>
     *
     * @param feedbackDto {@link FeedbackDto}
     * @return {@link String}
     */
    String addFeedback(FeedbackDto feedbackDto);

    /**
     * <p>
     * This method is used to delete feedback which means
     * inactive the status column in database by
     * getting feedback id from admin or patient.
     * </p>
     *
     * @param id {@link Integer}
     * @return {@link String}
     */
    String deleteFeedback(Integer id);

    /**
     * <p>
     * This method is used to get feedback details such as
     * comments, ratings of a doctor by getting doctor id,
     * this method will return the feedback object.
     * </p>
     *
     * @param doctorId   {@link Integer} is id of doctor
     * @param pageNumber {@link Integer} is page number
     * @param totalRows  {@link Integer} is required number of rows to be displayed
     * @return {@link List<FeedbackDto>}
     */
    List<FeedbackDto> getFeedbacksByDoctorId(Integer doctorId, Integer pageNumber, Integer totalRows);

    /**
     * <p>
     * This method is used to get the count of feedback
     * which means active feedback only considered for count
     * and it will return count of feedback as integer
     * by getting doctor id
     * </p>
     *
     * @param doctorId {@link Integer} is id of doctor
     * @return {@link Integer}
     */
    Integer getFeedbacksCountByDoctorId(Integer doctorId);
}
