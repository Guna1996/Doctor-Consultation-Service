/**
 * <p>
 * This is the base package for all the service interfaces
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.service;

import com.ideas2it.healthcare.dto.FeedbackDto;
import java.util.List;

/**
 * <p>
 * This Feedback interface is a service interface and this
 * interface is used to contain the body of FeedbackServiceImpl
 * class's methods
 * </p>
 *
 * @author  Bala Ashwanth
 *
 * @since   2022-10-10
 */
public interface  FeedbackService {

    /**
     * <p>
     *  This abstract method is used to add feedback
     *  to the database
     *
     * </p>
     *
     * @param feedbackDto {@link FeedbackDto}
     *
     * @return {@link FeedbackDto}
     */
    FeedbackDto addFeedback(FeedbackDto feedbackDto);

    /**
     * <p>
     *  This abstract method is used to update feedback
     *  to the database
     *
     * </p>
     *
     * @param feedbackDto {@link FeedbackDto}
     *
     * @return {@link FeedbackDto}
     */
    FeedbackDto updateFeedback(FeedbackDto feedbackDto);

    /**
     * <p>
     *  This abstract method is used to get feedback
     *  from the database
     *
     * </p>
     *
     * @param id {@link int}
     *
     * @return {@link FeedbackDto}
     */
    FeedbackDto getFeedbackById(int id);

    /**
     * <p>
     *  This abstract method is used to get all feedback
     *  from the database
     *
     * </p>
     *
     * @param pageNumber {@link int}
     *
     * @param totalRows {@link int}
     *
     * @return {@link List<FeedbackDto>}
     */
    List<FeedbackDto> getFeedbacks(int pageNumber, int totalRows);

    /**
     * <p>
     *  This abstract method is used to delete feedback
     *  from the database
     *
     * </p>
     *
     * @param id {@link int}
     *
     * @return {@link String}
     */
    String deleteFeedback(int id);

}
