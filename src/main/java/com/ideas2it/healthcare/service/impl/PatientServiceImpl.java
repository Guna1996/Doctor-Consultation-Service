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
import com.ideas2it.healthcare.dto.PatientDto;
import com.ideas2it.healthcare.exception.NotFoundException;
import com.ideas2it.healthcare.mapper.PatientMapper;
import com.ideas2it.healthcare.model.Patient;
import com.ideas2it.healthcare.repository.PatientRepository;
import com.ideas2it.healthcare.service.AppointmentService;
import com.ideas2it.healthcare.service.PatientService;
import com.ideas2it.healthcare.service.PatientVitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>
 * PatientServiceImpl class implements PatientService
 * and it contains methods and with helps of passing object to
 * PatientRepository interface
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

    @Autowired
    private PatientVitalService patientVitalService;

    @Autowired
    private AppointmentService appointmentService;

    /**
     * {@inheritDoc}
     */
    public PatientDto addPatient(PatientDto patientDto) {
        return PatientMapper.toDto(patientRepository.save(PatientMapper.fromDto(patientDto)));
    }

    /**
     * {@inheritDoc}
     */
    public PatientDto getPatientById(Integer id) {
        return patientRepository.findByIdAndStatus(id, Constants.ACTIVE).stream()
                .map(PatientMapper::toDto)
                .findFirst()
                .orElseThrow(() -> new NotFoundException(ErrorConstants.PATIENT_NOT_FOUND));
    }

    /**
     * {@inheritDoc}
     */
    public PatientDto updatePatient(PatientDto patientDto) {
        Optional<Patient> patient = patientRepository.findByIdAndStatus(patientDto.getId(),
                Constants.ACTIVE);
        if (patient.isEmpty()) {
            throw new NotFoundException(MessageConstants.PATIENT_UNABLE_TO_UPDATE);
        }
        return PatientMapper.toDto(patientRepository.save(PatientMapper.fromDto(patientDto)));
    }
}