/**
 * <p>
 * This package contains classes are Doctor clinic repository,
 * Patient repository, Doctor repository, Clinic repository,
 * Appointment repository, Feedback repository, Specialization repository,
 * Timeslot repository, Patient vital repository.
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.repository;

import com.ideas2it.healthcare.model.DoctorClinic;
import com.ideas2it.healthcare.model.Timeslot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * Interface Doctor clinic repository extends the Jpa repository
 * and used to retrieve and delete and update
 * Doctor clinic data's from database
 * </p>
 *
 * @author Ramachandran
 * @version 1
 * @since 2022-10-10
 */
@Repository
@Transactional
public interface DoctorClinicRepository extends JpaRepository<DoctorClinic, Integer> {

    /**
     * <p>
     * This method is used to find active Doctor Clinic
     * by Doctor Clinic id, this method fetching active Doctor
     * clinic details only if it's in inactive it will not fetch and return
     * </p>
     *
     * @param id     {@link Integer} is id of the doctorClinic in DoctorClinic table
     * @param status {@link String} is status of the doctorClinic
     * @return {@link Optional<DoctorClinic>}
     */
    Optional<DoctorClinic> findByIdAndStatus(Integer id, String status);

    /**
     * <p>
     * This method is used to find active Clinic by doctor id
     * and clinic id this method fetching active Doctor clinic details
     * only if it's in inactive it will not fetch and return
     * </p>
     *
     * @param clinicId {@link Integer} is id of clinic
     * @param doctorId {@link Integer} is id of doctor
     * @param status   {@link String} is status of doctorClinic
     * @return {@link Optional<DoctorClinic>}
     */
    Optional<DoctorClinic> findByDoctorIdAndClinicIdAndStatus(
            Integer doctorId, Integer clinicId, String status);

    /**
     * <p>
     * This method is used to delete doctor clinic by id
     * and set the status as inactive using query in the
     * database, if we are inactive a detail in Doctor Clinic
     * table after that we can't fetch that detail
     * </p>
     *
     * @param id {@link Integer} is id of the doctorClinic
     * @return {@link Integer}
     */
    @Modifying
    @Query("update doctor_clinic set status = 'inactive' where id=?1 and status = 'active'")
    Integer removeDoctorClinicById(Integer id);

    /**
     * <p>
     * This method is used to find active doctor clinic by clinic id and status
     * and using pagination which can get only the required number of rows.
     * </p>
     *
     * @param clinicId {@link Integer} is id of clinic
     * @param status   {@link String} is status of doctorClinic
     * @param pageable {@link Pageable} contains page number and number of rows required
     * @return {@link Page<DoctorClinic>}
     */
    Page<DoctorClinic> findByClinicIdAndStatus(
            Integer clinicId, String status, Pageable pageable);

    /**
     * <p>
     * This method is used to find the count of all active doctor clinic
     * by clinic id and status, this method only fetch and count the active
     * details only, inactive details it will not concern
     * </p>
     *
     * @param clinicId {@link Integer} is id of clinic
     * @param status   {@link String} is status of doctorClinic
     * @return {@link Integer}
     */
    Integer countByClinicIdAndStatus(Integer clinicId, String status);

    /**
     * <p>
     * This method is used to find the active Doctor clinic by using
     * doctor id and if the status is active it will return
     * the list of corresponding doctor clinic objects
     * </p>
     *
     * @param doctorId {@link Integer} is id of doctor
     * @param status   {@link String} is status of doctorClinic
     * @return {@link DoctorClinic}
     */
    List<DoctorClinic> findByDoctorIdAndStatus(Integer doctorId, String status);

    @Query(value = "select timeslot_id from doctor_clinic_timeslot " +
            "where doctor_clinic_id IN (select id from doctor_clinic " +
            "where doctor_id = ?);", nativeQuery = true)
    List<Integer> getTimeslots(int doctor_id);
}
