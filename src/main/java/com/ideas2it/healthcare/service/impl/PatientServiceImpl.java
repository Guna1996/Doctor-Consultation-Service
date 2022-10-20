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
package com.ideas2it.healthcare.service.impl;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.common.ErrorConstants;
import com.ideas2it.healthcare.common.UserConstants;
import com.ideas2it.healthcare.dto.PatientDto;
import com.ideas2it.healthcare.exception.NotFoundException;
import com.ideas2it.healthcare.mapper.PatientMapper;
import com.ideas2it.healthcare.model.Patient;
import com.ideas2it.healthcare.repo.PatientRepository;
import com.ideas2it.healthcare.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

    @Override
    public String deletePatient(Integer id) {
        if (patientRepository.deletePatiendById(id) == 1){
            return UserConstants.DELETED_SUCCESSFULLY;
        }
        return ErrorConstants.PATIENT_NOT_FOUND;
    }

    /**
     * {@inheritDoc}
     */
    public List<PatientDto> getPatients(int pageNumber, int totalRows) {
        List<Patient> patients = patientRepository.findAllByStatus(Constants.ACTIVE,
                PageRequest.of(pageNumber, totalRows)).toList();
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
    @Override
    public boolean isPatientAvailable(Integer id) {
        return patientRepository.findByIdAndStatus(id, Constants.ACTIVE).isPresent();
    }


}