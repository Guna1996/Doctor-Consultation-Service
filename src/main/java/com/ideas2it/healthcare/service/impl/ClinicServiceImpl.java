/**
 * <p>
 * This package contains classes are Doctor Clinic Impl,
 * Patient Impl, Doctor Impl, Clinic Impl,
 * Appointment Impl, Feedback Impl, Specialization Impl,
 * Timeslot Impl, Patient Vital Impl
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.service.impl;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.common.ErrorConstants;
import com.ideas2it.healthcare.common.MessageConstants;
import com.ideas2it.healthcare.dto.ClinicDto;
import com.ideas2it.healthcare.exception.CustomException;
import com.ideas2it.healthcare.exception.DataBaseException;
import com.ideas2it.healthcare.mapper.ClinicMapper;
import com.ideas2it.healthcare.model.Clinic;
import com.ideas2it.healthcare.repository.ClinicRepository;
import com.ideas2it.healthcare.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * Clinic service Impl class implements Clinic service
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
    public String addClinic(ClinicDto clinicDto) {
        try {
            clinicRepository.save(ClinicMapper.fromDto(clinicDto));
            return MessageConstants.CLINIC_ADDED_SUCCESSFULLY;
        } catch (DataIntegrityViolationException exception) {
            throw new CustomException(ErrorConstants.CLINIC_ALREADY_EXISTS);
        } catch (DataAccessException exception) {
            throw new DataBaseException(ErrorConstants.CANNOT_ACCESS_DATABASE);
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<ClinicDto> getClinics(Integer pageNumber, Integer totalRows) {
        try {
            List<Clinic> clinics = clinicRepository.findAllByStatus(Constants.ACTIVE,
                    PageRequest.of(pageNumber, totalRows)).toList();
            return clinics.stream()
                    .map(ClinicMapper::toDto)
                    .collect(Collectors.toList());
        } catch (DataAccessException exception) {
            throw new DataBaseException(ErrorConstants.CANNOT_ACCESS_DATABASE);
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
                    .orElse(null);
        } catch (DataAccessException exception) {
            throw new DataBaseException(ErrorConstants.CANNOT_ACCESS_DATABASE);
        }
    }

    /**
     * {@inheritDoc}
     */
    public String updateClinic(ClinicDto clinicDto) {
        try {
            clinicRepository.save(ClinicMapper.fromDto(clinicDto));
            return MessageConstants.CLINIC_UPDATED_SUCCESSFULLY;
        } catch (DataAccessException exception) {
            throw new DataBaseException(ErrorConstants.CANNOT_ACCESS_DATABASE);
        }
    }

    /**
     * {@inheritDoc}
     */
    public String removeClinicById(Integer id) {
        String response = ErrorConstants.CLINIC_NOT_FOUND;
        try {
            if (1 <= clinicRepository.removeClinicById(id)) {
                response =  MessageConstants.CLINIC_REMOVED_SUCCESSFULLY;
            }
        } catch (DataAccessException exception) {
            throw new DataBaseException(ErrorConstants.CANNOT_ACCESS_DATABASE);
        }
        return response;
    }

    /**
     * {@inheritDoc}
     */
    public Integer getCountOfClinics() {
        try {
            return clinicRepository.countByStatus(Constants.ACTIVE);
        } catch (DataAccessException exception) {
            throw new DataBaseException(ErrorConstants.CANNOT_ACCESS_DATABASE);
        }
    }

}

