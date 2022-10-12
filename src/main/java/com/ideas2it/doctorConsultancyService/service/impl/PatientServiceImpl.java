package com.ideas2it.doctorConsultancyService.service.impl;

import com.ideas2it.doctorConsultancyService.common.Constants;
import com.ideas2it.doctorConsultancyService.dto.PatientDto;
import com.ideas2it.doctorConsultancyService.exception.NotFoundException;
import com.ideas2it.doctorConsultancyService.mapper.PatientMapper;
import com.ideas2it.doctorConsultancyService.model.Patient;
import com.ideas2it.doctorConsultancyService.repo.PatientRepository;
import com.ideas2it.doctorConsultancyService.service.Patientservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements Patientservice {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public PatientDto addPatient(PatientDto patientDto) {
        Patient patient = PatientMapper.fromDto(patientDto);
        return PatientMapper.toDto(patientRepository.save(patient));
    }

    @Override
    public PatientDto getPatientById(Integer id) throws NotFoundException {
        Patient PatientFromDb = patientRepository.findByIdAndStatus(id, Constants.ACTIVE);
        if(PatientFromDb != null) {
            PatientDto patientDto = PatientMapper.toDto(PatientFromDb);
            return patientDto;
        } else {
            throw new NotFoundException("Patient not found");
        }
    }

    @Override
    public PatientDto updatePatient(PatientDto patientDto) throws NotFoundException {
        Patient patient = patientRepository.findByIdAndStatus(patientDto.getId(), Constants.ACTIVE);
        if(patient != null) {
            patient = PatientMapper.fromDto(patientDto);
            return PatientMapper.toDto(patientRepository.save(patient));
        } else {
            throw new NotFoundException("Patient not found");
        }
    }

    @Override
    public String deletPatient(Integer id) throws NotFoundException {
        Patient patient = patientRepository.deleteByIdAndStatus(id, Constants.ACTIVE);
        if(patient != null) {
            patient.setStatus(Constants.INACTIVE);
            patientRepository.save(patient);
            return "deleted successfully";
        } else {
            throw new NotFoundException("Patient not found");
        }
    }
}