/**
 * <p>
 * This package contains interfaces of DoctorClinicService,
 * PatientService, DoctorService, ClinicService,
 * AppointmentService, FeedbackService, SpecializationService,
 * TimeslotControllerService, VitalServiceImpl.
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.service;

import com.ideas2it.healthcare.dto.FeedbackDto;

import java.util.List;

/**
 * <p>
 * FeedbackService interface consists of abstract methods which is used
 * for performing CRUD operation. it is used to transfer objects between
 * controller and repository
 * </p>
 *
 * @author Bala Ashwanth
 * @since 2022-10-10
 */
public interface FeedbackService {

    /**
     * <p>
     * This abstract method is used to add feedback
     * of user to the database
     *
     * </p>
     *
     * @param feedbackDto {@link FeedbackDto}
     * @return {@link FeedbackDto}
     */
    FeedbackDto addFeedback(FeedbackDto feedbackDto);

    /**
     * <p>
     * This abstract method is used to delete feedback
     * from the database by feedback id
     *
     * </p>
     *
     * @param id {@link int}
     * @return {@link String}
     */
    String deleteFeedback(int id);

    List<FeedbackDto> getFeedbackByDoctorId(int doctorId, int pageNumber, int totalRows);

}
