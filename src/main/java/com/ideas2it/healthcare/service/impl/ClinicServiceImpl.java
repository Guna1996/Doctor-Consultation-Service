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
import com.ideas2it.healthcare.dto.ClinicDto;
import com.ideas2it.healthcare.exception.NotFoundException;
import com.ideas2it.healthcare.exception.SqlException;
import com.ideas2it.healthcare.mapper.ClinicMapper;
import com.ideas2it.healthcare.model.Clinic;
import com.ideas2it.healthcare.repository.ClinicRepository;
import com.ideas2it.healthcare.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * Clinic serviceImpl class implements Clinic service
 * and it contains methods and with helps of passing object to
 * ClinicRepository interface
 * </p>
 *
 * @author Gunaseelan K
 * @version 1
 * @since 2022-10-10
 */
@Service
public class ClinicServiceImpl implements ClinicService {

    @Autowired
    private ClinicRepository clinicRepository;

    /**
     * {@inheritDoc}
     */
    public ClinicDto addClinic(ClinicDto clinicDto) {
        Clinic clinic = ClinicMapper.fromDto(clinicDto);
        try {
            return ClinicMapper.toDto(clinicRepository.save(clinic));
        } catch (DataAccessException exception) {
            throw new SqlException(exception.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<ClinicDto> getClinics(Integer pageNumber, Integer totalRows) {
        try {
            List<Clinic> clinics = clinicRepository.findAllByStatus(Constants.ACTIVE,
                    PageRequest.of(pageNumber, totalRows)).toList();
            if (clinics.isEmpty()) {
                throw new NotFoundException(ErrorConstants.CLINIC_NOT_FOUND);
            }
            return clinics.stream()
                    .map(ClinicMapper::toDto)
                    .collect(Collectors.toList());
        } catch (DataAccessException exception) {
            throw new SqlException(ErrorConstants.DATABASE_NOT_FOUND);
        }
    }

    /**
     * {@inheritDoc}
     */
    public ClinicDto getClinicById(Integer id) {
        try {
            return clinicRepository.findByIdAndStatus(id, Constants.ACTIVE).stream().
                    map(ClinicMapper::toDto)
                    .findFirst()
                    .orElseThrow(() -> new NotFoundException(ErrorConstants.CLINIC_NOT_FOUND));
        } catch (DataAccessException exception) {
            throw new SqlException(ErrorConstants.DATABASE_NOT_FOUND);
        }
    }

    /**
     * {@inheritDoc}
     */
    public ClinicDto updateClinic(ClinicDto clinicDto) {
        try {
            return ClinicMapper.toDto(clinicRepository.save(ClinicMapper.fromDto(clinicDto)));
        } catch (DataAccessException exception) {
            throw new SqlException(exception.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    public String removeClinicById(Integer id) {
        try {
            if (1 <= clinicRepository.removeClinicById(id)) {
                return MessageConstants.CLINIC_DELETED_SUCCESSFULLY;
            }
            throw new NotFoundException(ErrorConstants.CLINIC_NOT_FOUND);
        } catch (DataAccessException exception) {
            throw new SqlException(exception.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    public Integer countOfClinics() {
        try {
            return clinicRepository.countByStatus(Constants.ACTIVE);
        } catch (DataAccessException exception) {
            throw new SqlException(exception.getMessage());
        }
    }

}

