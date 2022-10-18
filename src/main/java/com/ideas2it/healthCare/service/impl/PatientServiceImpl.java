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
import com.ideas2it.healthCare.model.Patient;
import com.ideas2it.healthCare.repo.PatientRepository;
import com.ideas2it.healthCare.service.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private ModelMapper modelMapper;

    /**
     * {@inheritDoc}
     */
    public PatientDto addPatient(PatientDto patientDto) {
        Patient patient = modelMapper.map(patientDto, Patient.class);
        patient = patientRepository.save(patient);
        patientDto = modelMapper.map(patient, PatientDto.class);
        return patientDto;
    }

    /**
     * {@inheritDoc}
     */
    public PatientDto getPatientById(Integer id) {
        Patient patient = patientRepository.findByIdAndStatus(id, Constants.ACTIVE);
        if (patient != null) {
            PatientDto patientDto = modelMapper.map(patient, PatientDto.class);
            return patientDto;
        } else {
            throw new NotFoundException("Patient not found");
        }
    }

    /**
     * {@inheritDoc}
     */
    public PatientDto updatePatient(PatientDto patientDto) {
        if (patientDto != null) {
            Patient patient = modelMapper.map(patientDto, Patient.class);
            patient = patientRepository.save(patient);
            patientDto = modelMapper.map(patient, PatientDto.class);
            return patientDto;
        } else {
            throw new NotFoundException("Patient can't able to update");
        }
    }

    /**
     * {@inheritDoc}
     */
    public String deletePatient(Integer id) {
        Patient patient = patientRepository.findByIdAndStatus(id, Constants.ACTIVE);
        if (patient != null) {
            patient.setStatus(Constants.INACTIVE);
            patientRepository.save(patient);
            return "deleted successfully";
        } else {
            throw new NotFoundException("Patient not found");
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<PatientDto> getPatients() {
        List<Patient> patients = patientRepository.findAllByStatus(Constants.ACTIVE);
        if (patients.isEmpty()) {
            throw new NotFoundException("No Patients Found");
        } else {
            return patients.stream()
                    .map(patient -> modelMapper.map(patient, PatientDto.class))
                    .collect(Collectors.toList());
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean isPatientAvailable(Integer id) {
        Patient patient = patientRepository.findByIdAndStatus(id, Constants.ACTIVE);
        return patient != null;
    }
}