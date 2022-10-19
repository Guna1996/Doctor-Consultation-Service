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
import com.ideas2it.healthCare.dto.PatientDto;
import com.ideas2it.healthCare.exception.NotFoundException;
import com.ideas2it.healthCare.mapper.ClinicMapper;
import com.ideas2it.healthCare.mapper.PatientMapper;
import com.ideas2it.healthCare.model.Patient;
import com.ideas2it.healthCare.repo.PatientRepository;
import com.ideas2it.healthCare.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
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
 * @since 2022-07-18
 */
@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    /**
     * {@inheritDoc}
     */
    public PatientDto addPatient(PatientDto patientDto) {
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
                orElseThrow(() -> new NotFoundException("Patient not Found"));
    }

    /**
     * {@inheritDoc}
     */
    public PatientDto updatePatient(PatientDto patientDto) {
        if (patientDto != null) {
            Patient patient = PatientMapper.fromDto(patientDto);
            patient = patientRepository.save(patient);
            patientDto = PatientMapper.toDto(patient);
            return patientDto;
        } else {
            throw new NotFoundException("Patient can't able to update");
        }
    }

    /**
     * {@inheritDoc}
     */
    public String deletePatient(Integer id) {
        Patient patient = patientRepository.findByIdAndStatus(id, Constants.ACTIVE)
                .orElseThrow(() -> new NotFoundException("Patient not found") );
            patient.setStatus(Constants.INACTIVE);
            patientRepository.save(patient);
            return "deleted successfully";
    }

    /**
     * {@inheritDoc}
     */
    public List<PatientDto> getPatients(int pageNumber, int totalRows) {
        List<Patient> patients = patientRepository.findAllByStatus(Constants.ACTIVE,
                PageRequest.of(pageNumber, totalRows)).toList();
        if (patients.isEmpty()) {
            throw new NotFoundException("No Patients Found");
        } else {
            return patients.stream()
                    .map(PatientMapper::toDto)
                    .collect(Collectors.toList());
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean isPatientAvailable(Integer id) {
        Patient patient = patientRepository.findByIdAndStatus(id, Constants.ACTIVE)
                .orElseThrow(()-> new RuntimeException("not found"));
        return patient != null;
    }
}