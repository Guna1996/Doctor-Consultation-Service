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

import com.ideas2it.healthcare.dto.DoctorClinicDto;

import java.util.List;

/**
 * <p>
 * Doctor clinic service interface consists of abstract methods which is used
 * for performing crud operation. it is used to transfer objects between
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
     * This method is used to assign doctor into clinic
     * table by getting doctor id and clinic id
     * from this method we can know how many doctors are
     * assigned in particular clinic
     * </p>
     *
     * @param doctorClinicDto {@link DoctorClinicDto} is a dto class of doctor clinic
     * @return {@link String}
     */
    String assignDoctorToClinic(DoctorClinicDto doctorClinicDto);

    /**
     * <p>
     * This method is used to delete doctor in a clinic by
     * getting id and it will InActive the status
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
     * by doctor id and clinic id and sent the object to the repository layer
     * for read operation
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
     * by clinic id using pagination which can get only the
     * required number of rows.
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
     * which mean active doctors are counted by this method and it will return
     * the count of active doctors
     * </p>
     *
     * @param clinicId {@link Integer} is id of clinic
     * @return {@link Integer} count of doctors
     */
    Integer getCountOfDoctorsByClinicId(Integer clinicId);

    /**
     * <p>
     * This method is used to update doctor into clinic
     * table by getting details such as doctor id and clinic id
     * from the user to update
     * </p>
     *
     * @param doctorClinicDto {@link DoctorClinicDto} is a dto class of doctor clinic
     * @return {@link String}
     */
    String updateDoctorTimeslotsInClinic(DoctorClinicDto doctorClinicDto);
}
