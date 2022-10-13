package com.ideas2it.healthCare.service;

import com.ideas2it.healthCare.dto.DoctorClinicDto;
import com.ideas2it.healthCare.model.DoctorClinic;

public interface DoctorClinicService {

    DoctorClinicDto assignDoctorToClinic(DoctorClinicDto doctorClinicDto);
}
