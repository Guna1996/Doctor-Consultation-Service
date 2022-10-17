package com.ideas2it.healthCare.mapper;

import com.ideas2it.healthCare.dto.ClinicDto;
import com.ideas2it.healthCare.model.Clinic;
import org.mapstruct.Mapper;

@Mapper
public interface ClinicMapper {
    Clinic fromDto(ClinicDto clinicDto);

    ClinicDto toDto(Clinic clinic);
}
