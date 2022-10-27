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

    /**
     * <p>
     * This method is used to get timeslots of a particular doctor and clinic
     * by doctor and clinic id
     * </p>
     *
     * @param clinicId is id of clinic
     * @param doctorId is id of doctor
     * @return DoctorClinicDto
     */
    DoctorClinicDto getTimeslotsByDoctorIdAndClinicId(int doctorId, int clinicId);

    /**
     * <p>
     * This method is used to get doctor clinic
     * by doctor clinic id
     * </p>
     *
     * @param id is id of doctor clinic
     * @return DoctorClinicDto
     */
    DoctorClinicDto getDoctorClinicById(int id);

    /**
     * <p>
     * This method is used to get Doctors of a particular clinic
     * by clinic id
     * </p>
     *
     * @param clinicId is id of clinic
     * @param pageNumber is page number of appointments
     * @param totalRows is required number of rows to be displayed
     * @return List<DoctorClinicDto>
     */
    public List<DoctorClinicDto> getDoctorsByClinicId(int clinicId, int pageNumber, int totalRows);
}
