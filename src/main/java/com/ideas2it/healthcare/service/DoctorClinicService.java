/**
 * <p>
 * This package contains interfaces of DoctorClinicService,
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
 * DoctorClinicService interface consists of abstract methods which is used
 * for performing CRUD operation. it is used to transfer objects between
 * controller and repository
 * </p>
 *
 * @author Ramachandran
 * @version 1
 * @since 2022-10-10
 */
public interface DoctorClinicService {

    /**
     * <p>
     * This method is used to assign
     * doctor into clinic table by getting doctor and clinic id
     * from the user
     * </p>
     *
     * @param doctorClinicDto {@link DoctorClinicDto} is a dto class of doctor clinic
     * @return {@link DoctorClinicDto}
     */
    DoctorClinicDto assignDoctorToClinic(DoctorClinicDto doctorClinicDto);

    /**
     * <p>
     * This method is used to delete doctor id on the
     * doctor clinic table which is it will InActive the status
     * column in doctor clinic table in the database
     * </p>
     *
     * @param id {@link Integer} id of the doctor
     * @return {@link String}
     */
    String removeDoctorFromClinic(Integer id);

    /**
     * <p>
     * This method is used to get timeslots of a particular doctor and clinic
     * by doctor and clinic id
     * </p>
     *
     * @param clinicId {@link Integer} is id of clinic
     * @param doctorId {@link Integer} is id of doctor
     * @return {@link DoctorClinicDto}
     */
    DoctorClinicDto getTimeslotsByDoctorIdAndClinicId(Integer doctorId, Integer clinicId);

    /**
     * <p>
     * This method is used to get Doctors of a particular clinic
     * by clinic id
     * </p>
     *
     * @param clinicId   {@link Integer} is id of clinic
     * @param pageNumber {@link Integer} is page number
     * @param totalRows  {@link Integer} is required number of rows to be displayed
     * @return {@link List<DoctorClinicDto>}
     */
    List<DoctorClinicDto> getDoctorsByClinicId(Integer clinicId, Integer pageNumber,
                                               Integer totalRows);

    /**
     * <p>
     * This method is used to get the count Doctors of a particular clinic
     * by clinic id
     * </p>
     *
     * @param clinicId   {@link Integer} is id of clinic
     * @return {@link Integer}
     */
    Integer countOfDoctorsByClinicId(Integer clinicId);
}
