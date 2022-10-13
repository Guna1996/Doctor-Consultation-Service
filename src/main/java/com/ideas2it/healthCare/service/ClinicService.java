package com.ideas2it.healthCare.service;

import com.ideas2it.healthCare.dto.ClinicDto;
import com.ideas2it.healthCare.exception.NotFoundException;

import java.util.List;

public interface ClinicService {
    ClinicDto addClinic(ClinicDto clinicDto);

    List<ClinicDto> getClinics();

    ClinicDto getClinicById(int id);

    ClinicDto updateClinic(ClinicDto clinicDto);

    String deleteClinicById(int id);
}
