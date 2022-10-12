package com.ideas2it.doctorConsultancyService.service;

import com.ideas2it.doctorConsultancyService.dto.ClinicDto;
import com.ideas2it.doctorConsultancyService.exception.NotFoundException;

import java.util.List;

public interface ClinicService {
    ClinicDto addClinic(ClinicDto clinicDto);

    List<ClinicDto> getClinics() throws NotFoundException;

    ClinicDto getClinicById(int id) throws NotFoundException;

    ClinicDto updateClinic(ClinicDto clinicDto) throws NotFoundException;

    String deleteClinicById(int id) throws NotFoundException;
}
