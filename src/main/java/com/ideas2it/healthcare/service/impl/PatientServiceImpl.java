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
import com.ideas2it.healthcare.dto.PatientDto;
import com.ideas2it.healthcare.exception.NotFoundException;
import com.ideas2it.healthcare.exception.SqlException;
import com.ideas2it.healthcare.mapper.PatientMapper;
import com.ideas2it.healthcare.model.Patient;
import com.ideas2it.healthcare.repository.PatientRepository;
import com.ideas2it.healthcare.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>
 * Patient Service Impl class implements Patient Service
 * and it contains methods and with helps of passing object to
 * Patient Repository interface
 * </p>
 *
 * @author Ramachandran
 * @version 1
 * @since 2022-10-10
 */
@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    /**
     * {@inheritDoc}
     */
    public String addPatient(PatientDto patientDto) {
        try {
            patientRepository.save(PatientMapper.fromDto(patientDto));
            return MessageConstants.PATIENT_ADDED_SUCCESSFULLY;
        } catch (DataIntegrityViolationException exception) {
            throw new NotFoundException(ErrorConstants.PATIENT_ALREADY_EXISTS);
        } catch (DataAccessException exception) {
            throw new SqlException(ErrorConstants.CANNOT_ACCESS_DATABASE);
        }
    }

    /**
     * {@inheritDoc}
     */
    public PatientDto getPatientById(Integer id) {
        try {
            return patientRepository.findByIdAndStatus(id, Constants.ACTIVE).stream()
                    .map(PatientMapper::toDto)
                    .findFirst()
                    .orElseThrow(() -> new NotFoundException(ErrorConstants.PATIENT_NOT_FOUND));
        } catch (DataAccessException exception) {
            throw new SqlException(ErrorConstants.CANNOT_ACCESS_DATABASE);
        }
    }

    /**
     * {@inheritDoc}
     */
    public String updatePatient(PatientDto patientDto) {
        try {
            Optional<Patient> patient = patientRepository.findByIdAndStatus(patientDto.getId(),
                    Constants.ACTIVE);
            if (patient.isEmpty()) {
                throw new NotFoundException(MessageConstants.PATIENT_UNABLE_TO_UPDATE);
            }
            patientRepository.save(PatientMapper.fromDto(patientDto));
            return MessageConstants.PATIENT_UPDATED_SUCCESSFULLY;
        } catch (DataAccessException exception) {
            throw new SqlException(ErrorConstants.CANNOT_ACCESS_DATABASE);
        }
    }
}