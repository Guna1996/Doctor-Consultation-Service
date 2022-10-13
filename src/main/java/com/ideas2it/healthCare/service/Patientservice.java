package com.ideas2it.healthCare.service;

import com.ideas2it.healthCare.dto.PatientDto;
import com.ideas2it.healthCare.exception.NotFoundException;

public interface Patientservice {
    PatientDto addPatient(PatientDto patientDto);

    PatientDto getPatientById(Integer id) throws NotFoundException;

    PatientDto updatePatient(PatientDto patientDto) throws NotFoundException;

    String deletPatient(Integer id) throws NotFoundException;
}
