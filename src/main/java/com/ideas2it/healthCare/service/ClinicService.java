/**
 * <p>
 * This package contains interfaces are DoctorClinicService,
 * PatientService, DoctorService, ClinicService,
 * AppointmentService, FeedbackService, SpecializationService,
 * TimeslotControllerService, VitalsServiceImpl.
 * </p>
 *
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthCare.service;

import com.ideas2it.healthCare.dto.ClinicDto;

import java.util.List;

/**
 * <p>
 * ClinicService interface is used for converting dto into entity
 * class and also it helps to passing objects between controller
 * to repository for CRUD operations
 * </p>
 *
 * @author  Gunaseelan K
 *
 * @version 1
 *
 * @since   2022-07-18
 */
public interface ClinicService {
    ClinicDto addClinic(ClinicDto clinicDto);

    List<ClinicDto> getClinics();

    ClinicDto getClinicById(int id);

    ClinicDto updateClinic(ClinicDto clinicDto);

    String deleteClinicById(int id);

    boolean isClinicAvailable(int id);
}
