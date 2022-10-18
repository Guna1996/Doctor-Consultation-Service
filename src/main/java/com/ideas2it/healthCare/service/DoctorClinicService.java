package com.ideas2it.healthCare.service;

import com.ideas2it.healthCare.dto.DoctorClinicDto;
import com.ideas2it.healthCare.model.DoctorClinic;

import java.util.List;
import java.util.Optional;

public interface DoctorClinicService {

    DoctorClinicDto assignDoctorToClinic(DoctorClinicDto doctorClinicDto);

    List<DoctorClinicDto> getDoctorClinics();

    String deleteDoctorFromClinic(Integer id);

    DoctorClinicDto updateDoctorClinic(DoctorClinicDto doctorClinicDto);

    DoctorClinicDto getByDoctorIdAndClinicId(int doctorId, int clinicId);

}
