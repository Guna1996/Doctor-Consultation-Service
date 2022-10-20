/**
 * <p>
 * This package contains classes are DoctorClinicImpl,
 * PatientImpl, DoctorImpl, ClinicImpl,
 * AppointmentImpl, FeedbackImpl, SpecializationImpl,
 * TimeslotImpl, VitalsImpl
 * </p>
 *
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthCare.service.impl;

import com.ideas2it.healthCare.common.Constants;
import com.ideas2it.healthCare.common.ErrorConstants;
import com.ideas2it.healthCare.common.UserConstants;
import com.ideas2it.healthCare.dto.PatientDto;
import com.ideas2it.healthCare.exception.NotFoundException;
import com.ideas2it.healthCare.mapper.PatientMapper;
import com.ideas2it.healthCare.model.Patient;
import com.ideas2it.healthCare.repo.PatientRepository;
import com.ideas2it.healthCare.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * PatientserviceImpl class implements Patientservice
 * and it contains methods and with helps of passing object to
 * PatientRepository interface
 * </p>
 *
 * @author Ramachandran
 *
 * @version 1
 *
 * @since 2022-10-10
 */
@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    /**
     * {@inheritDoc}
     */
    public PatientDto addPatient(PatientDto patientDto) {
        patientDto.setStatus(Constants.ACTIVE);
        Patient patient = PatientMapper.fromDto(patientDto);
        patient = patientRepository.save(patient);
        patientDto = PatientMapper.toDto(patient);
        return patientDto;
    }

    /**
     * {@inheritDoc}
     */
    public PatientDto getPatientById(Integer id) {
        return patientRepository.findByIdAndStatus(id, Constants.ACTIVE).stream().
                map(PatientMapper::toDto).
                findFirst().
                orElseThrow(() -> new NotFoundException(UserConstants.PATIENT_NOT_FOUND));
    }

    /**
     * {@inheritDoc}
     */
    public PatientDto updatePatient(PatientDto patientDto) {
        Optional<Patient> patient = patientRepository.findByIdAndStatus(patientDto.getId(), Constants.ACTIVE);
        if (patient.isEmpty()) {
            throw new NotFoundException(UserConstants.PATIENT_CANNOT_ABLE_TO_UPDATE);
        }
            return PatientMapper.toDto(patientRepository.save(PatientMapper.fromDto(patientDto)));
    }

    /**
     * {@inheritDoc}
     */
    public String deletePatient(Integer id) {
        Patient patient = patientRepository.findByIdAndStatus(id, Constants.ACTIVE)
                .orElseThrow(() -> new NotFoundException(UserConstants.PATIENT_NOT_FOUND) );
            patient.setStatus(Constants.INACTIVE);
            patientRepository.save(patient);
            return UserConstants.DELETED_SUCCESSFULLY;
    }

    /**
     * {@inheritDoc}
     */
    public List<PatientDto> getPatients() {
        List<Patient> patients = patientRepository.findAllByStatus(Constants.ACTIVE);
        if (patients.isEmpty()) {
            throw new NotFoundException(UserConstants.PATIENT_NOT_FOUND);
        }
            return patients.stream()
                    .map(PatientMapper::toDto)
                    .collect(Collectors.toList());

    }

    /**
     * {@inheritDoc}
     */
    public boolean isPatientAvailable(Integer id) {
        return patientRepository.findByIdAndStatus(id, Constants.ACTIVE).isPresent();
    }
}