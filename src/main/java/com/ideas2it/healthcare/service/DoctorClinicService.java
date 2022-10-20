/**
 * <p>
 * This package contains interfaces are DoctorClinicService,
 * PatientService, DoctorService, ClinicService,
 * AppointmentService, FeedbackService, SpecializationService,
 * TimeslotControllerService, VitalServiceImpl.
 * </p>
 * <p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.service;

import com.ideas2it.healthcare.dto.DoctorClinicDto;

import java.util.List;

/**
 * <p>
 * Doctor clinic  interface is used for converting dto into entity
 * class and also it helps to passing objects between controller
 * to repository for CRUD operations
 * </p>
 *
 * @author Ramachandran
 * @version 1
 * @since 2022-10-10
 */
public interface DoctorClinicService {

    /**
     * <p>
     * This assignDoctorToClinic abstract method is used to assign
     * doctor into clinic table by getting doctor and clinic id
     * from the user
     * </p>
     *
     * @param doctorClinicDto is a dto class of doctor clinic
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
    List<DoctorClinicDto> getDoctorClinics(int pageNumber, int totalRows);

    /**
     * <p>
     * This method is used to delete doctor id on the
     * doctor clinic table which is it will InActive the status
     * column in doctor clinic table in the database
     * </p>
     *
     * @param id - id of the doctor
     * @return String
     */
    String deleteDoctorFromClinic(Integer id);

    /**
     * <p>
     * This updateDoctorClinic method is used for
     * updating doctor id into the doctor clinic table
     * by getting input from the user
     * </p>
     *
     * @param doctorClinicDto is a dto class of doctor clinic
     * @return DoctorClinicDto
     */
    DoctorClinicDto updateDoctorClinic(DoctorClinicDto doctorClinicDto);

    DoctorClinicDto getByDoctorIdAndClinicId(int doctorId, int clinicId);

}
