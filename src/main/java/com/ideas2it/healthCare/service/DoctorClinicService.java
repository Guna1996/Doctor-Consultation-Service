package com.ideas2it.healthCare.service;

import com.ideas2it.healthCare.dto.DoctorClinicDto;
import com.ideas2it.healthCare.model.DoctorClinic;

import java.util.List;

public interface DoctorClinicService {

    DoctorClinicDto assignDoctorToClinic(DoctorClinicDto doctorClinicDto);

    List<DoctorClinicDto> getDoctorClinics();

    // DoctorClinicDto updateDoctorToClinic(DoctorClinicDto doctorClinicDto);

    String deleteDoctorFromClinic(Integer id);

}
