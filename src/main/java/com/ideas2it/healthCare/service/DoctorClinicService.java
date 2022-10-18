/**
 * <p>
 * This package contains interfaces are DoctorClinicService,
 * PatientService, DoctorService, ClinicService,
 * AppointmentService, FeedbackService, SpecializationService,
 * TimeslotControllerService, VitalsServiceImpl.
 * </p>
 * <p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthCare.service;

import com.ideas2it.healthCare.dto.DoctorClinicDto;

import java.util.List;
import java.util.Optional;

public interface DoctorClinicService {

    /**
     * <p>
     * This assignDoctorToClinic abstract method is used to assign
     * doctor into clinic table by getting doctor and clinic id
     * from the user
     * </p>
     *
     * @param doctorClinicDto
     *
     * @return DoctorClinicDto
     */
    DoctorClinicDto assignDoctorToClinic(DoctorClinicDto doctorClinicDto);

    /**
     * <p>
     * This getDoctorClinics method is used to
     * get all the doctors from the clinic which are
     * in active state in the DoctorClinic table
     * </p>
     *
     * @return List<DoctorClinicDto>
     */
    List<DoctorClinicDto> getDoctorClinics();

    /**
     * <p>
     * This method is used to delete doctor id on the
     * doctorclinic table which is it will InActive the status
     * column in doctorclinic table in the database
     * </p>
     *
     * @param id - id of the doctor
     *
     * @return String
     */
    String deleteDoctorFromClinic(Integer id);

    /**
     * <p>
     * This updateDoctorClinic method is used for
     * updating doctor id into the doctorclinic table
     * by getting input from the user
     * </p>
     *
     * @param doctorClinicDto
     *
     * @return DoctorClinicDto
     */
    DoctorClinicDto updateDoctorClinic(DoctorClinicDto doctorClinicDto);

    DoctorClinicDto getByDoctorIdAndClinicId(int doctorId, int clinicId);

}
