/**
 * <p>
 * This package contains classes are DoctorClinicImpl,
 * PatientImpl, DoctorImpl, ClinicImpl,
 * AppointmentImpl, FeedbackImpl, SpecializationImpl,
 * TimeslotImpl, VitalsImpl
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.service.impl;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.common.ErrorConstants;
import com.ideas2it.healthcare.common.MessageConstants;
import com.ideas2it.healthcare.dto.DoctorClinicDto;
import com.ideas2it.healthcare.exception.NotFoundException;
import com.ideas2it.healthcare.exception.SqlException;
import com.ideas2it.healthcare.mapper.DoctorClinicMapper;
import com.ideas2it.healthcare.repository.DoctorClinicRepository;
import com.ideas2it.healthcare.service.DoctorClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * DoctorClinicServiceImpl class implements DoctorClinicService
 * and it contains methods and with helps of passing object to
 * DoctorClinicRepository interface
 * </p>
 *
 * @author Ramachandran
 * @version 1
 * @since 2022-10-10
 */
@Service
public class DoctorClinicServiceImpl implements DoctorClinicService {

    @Autowired
    private DoctorClinicRepository doctorClinicRepository;

    /**
     * {@inheritDoc}
     */
    public DoctorClinicDto assignDoctorToClinic(DoctorClinicDto doctorClinicDto) {
        try {
            return DoctorClinicMapper
                    .toDto(doctorClinicRepository.save(DoctorClinicMapper.fromDto(doctorClinicDto)));
        } catch (SqlException exception) {
            throw new SqlException(ErrorConstants.DATABASE_NOT_FOUND);
        }
    }

    /**
     * {@inheritDoc}
     */
    public String removeDoctorFromClinic(Integer id) {
        try {
            if (1 <= doctorClinicRepository.removeDoctorClinicById(id)) {
                return MessageConstants.SUCCESSFULLY_DELETED_DOCTOR_FROM_CLINIC;
            }
            throw new NotFoundException(ErrorConstants.DOCTOR_UNABLE_TO_DELETE);
        } catch (SqlException exception) {
            throw new SqlException(ErrorConstants.DATABASE_NOT_FOUND);
        }
    }

    /**
     * {@inheritDoc}
     */
    public DoctorClinicDto getTimeslotsByDoctorIdAndClinicId(Integer doctorId, Integer clinicId) {
        try {
            return DoctorClinicMapper.toDto(doctorClinicRepository
                    .findByDoctorIdAndClinicIdAndStatus(doctorId, clinicId, Constants.ACTIVE)
                    .orElseThrow(() -> new NotFoundException(
                            MessageConstants.DOCTOR_ID_CLINIC_ID_NOT_FOUND)));
        } catch (SqlException exception) {
            throw new SqlException(ErrorConstants.DATABASE_NOT_FOUND);
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<DoctorClinicDto> getDoctorsByClinicId(Integer clinicId, Integer pageNumber,
                                                      Integer totalRows) {
        try {
            return doctorClinicRepository.findByClinicIdAndStatus(clinicId, Constants.ACTIVE,
                            PageRequest.of(pageNumber, totalRows)).toList().stream()
                    .map(DoctorClinicMapper::toDto).collect(Collectors.toList());
        } catch (SqlException exception) {
            throw new SqlException(ErrorConstants.DATABASE_NOT_FOUND);
        }
    }

    /**
     * {@inheritDoc}
     */
    public Integer countOfDoctorsByClinicId(Integer clinicId) {
        try {
            return doctorClinicRepository.countByClinicIdAndStatus(clinicId, Constants.ACTIVE);
        } catch (SqlException exception) {
            throw new SqlException(ErrorConstants.DATABASE_NOT_FOUND);
        }
    }
}

