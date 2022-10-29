/**
 * <p>
 * This is the base package for all the mapper classes
 * which is for DoctorMapper, PatientMapper and ClinicMapper
 * classes
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.mapper;

import com.ideas2it.healthcare.dto.ClinicDto;
import com.ideas2it.healthcare.model.Clinic;

/**
 * <p>
 * ClinicMapper is used convert Clinic object to
 * ClinicDto and ClinicDto into Clinic
 * </p>
 *
 * @author Mohamed Jubair
 * @version 1
 * @since 2022-10-10
 */
public class ClinicMapper {

    /**
     * <p>
     * This method is used to convert ClinicDto to
     * Clinic model
     * </p>
     *
     * @return {@link Clinic}
     */
    public static Clinic fromDto(ClinicDto clinicDto) {
        Clinic clinic = new Clinic();
        if (null != clinicDto) {
            clinic.setId(clinicDto.getId());
            clinic.setName(clinicDto.getName());
            clinic.setDoorNumber(clinicDto.getDoorNumber());
            clinic.setStreetName(clinicDto.getStreetName());
            clinic.setCity(clinicDto.getCity());
            clinic.setState(clinicDto.getState());
            clinic.setPinCode(clinicDto.getPinCode());
            clinic.setContactNumber(clinicDto.getContactNumber());
            clinic.setStatus(clinicDto.getStatus());
            }
        return clinic;
    }

    /**
     * <p>
     * This method is used to convert Clinic to
     * ClinicDto
     * </p>
     *
     * @return {@link ClinicDto}
     */
    public static ClinicDto toDto(Clinic clinic) {
        ClinicDto clinicDto = new ClinicDto();
        if (null != clinic) {
            clinicDto.setId(clinic.getId());
            clinicDto.setName(clinic.getName());
            clinicDto.setDoorNumber(clinic.getDoorNumber());
            clinicDto.setStreetName(clinic.getStreetName());
            clinicDto.setCity(clinic.getCity());
            clinicDto.setState(clinic.getState());
            clinicDto.setPinCode(clinic.getPinCode());
            clinicDto.setContactNumber(clinic.getContactNumber());
            clinicDto.setStatus(clinic.getStatus());
        }
        return clinicDto;
    }
}
