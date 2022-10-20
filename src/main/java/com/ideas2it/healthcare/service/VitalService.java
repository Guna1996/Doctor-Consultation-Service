/**
 * <p>
 * This is the base package for all the service interfaces
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.service;

import com.ideas2it.healthcare.dto.VitalDto;
import java.util.List;

/**
 * <p>
 * This Vital interface is a service interface and this
 * interface is used to contain the body of VitalServiceImpl
 * class's methods
 * </p>
 *
 * @author  Bala Ashwanth
 *
 * @since   2022-10-10
 */
public interface VitalService {

    /**
     * <p>
     *  This abstract method is used to add vital
     *  to the database
     *
     * </p>
     *
     * @param vitalsDto {@link VitalDto}
     *
     * @return {@link VitalDto}
     */
    VitalDto addVitals(VitalDto vitalsDto);

    /**
     * <p>
     *  This abstract method is used to update vital
     *  from the database
     *
     * </p>
     *
     * @param vitalsDto {@link VitalDto}
     *
     * @return {@link VitalDto}
     */
    VitalDto updateVitals(VitalDto vitalsDto);

    /**
     * <p>
     *  This abstract method is used to get vital
     *  from the database
     *
     * </p>
     *
     * @param id {@link int}
     *
     * @return {@link VitalDto}
     */
    VitalDto getVitalsById(int id);

    /**
     * <p>
     *  This abstract method is used to get all vitals
     *  from the database
     *
     * </p>
     *
     * @param pageNumber {@link int}
     *
     * @param totalRows {@link int}
     *
     * @return {@link List<VitalDto>}
     */
    List<VitalDto> getVitals(int pageNumber, int totalRows);

    /**
     * <p>
     *  This abstract method is used to delete vital
     *  from the database
     *
     * </p>
     *
     * @param id {@link int}
     *
     * @return {@link String}
     */
    String deleteVitals(int id);

    /**
     * <p>
     *  This abstract method is used to get all vitals
     *  from the database using patient
     *
     * </p>
     *
     * @param patientId {@link int}
     *
     * @param pageNumber {@link int}
     *
     * @param totalRows {@link int}
     *
     * @return {@link List<VitalDto>}
     */
    List<VitalDto> getVitalsByPatientId(int patientId, int pageNumber, int totalRows);
}
