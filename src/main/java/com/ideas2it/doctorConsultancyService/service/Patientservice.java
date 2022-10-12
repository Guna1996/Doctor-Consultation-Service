package com.ideas2it.doctorConsultancyService.service;

import com.ideas2it.doctorConsultancyService.dto.PatientDto;
import com.ideas2it.doctorConsultancyService.exception.NotFoundException;
import com.ideas2it.doctorConsultancyService.model.Patient;
import org.springframework.data.crossstore.ChangeSetPersister;

public interface Patientservice {
    PatientDto addPatient(PatientDto patientDto);

    PatientDto getPatientById(Integer id) throws NotFoundException;

    PatientDto updatePatient(PatientDto patientDto) throws NotFoundException;

    String deletPatient(Integer id) throws NotFoundException;
}
