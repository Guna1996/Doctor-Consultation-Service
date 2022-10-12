package com.ideas2it.doctorConsultancyService.mapper;

import com.ideas2it.doctorConsultancyService.dto.SpecializationDto;
import com.ideas2it.doctorConsultancyService.model.Specialization;

public class SpecializationMapper {

    public static Specialization fromDto(SpecializationDto specializationDto){
        return Specialization.builder()
                .id(specializationDto.getId())
                .name(specializationDto.getName())
                .status(specializationDto.getStatus())
                .build();
    }

    public static SpecializationDto toDto(Specialization specialization) {
        return SpecializationDto.builder()
                .id(specialization.getId())
                .name(specialization.getName())
                .status(specialization.getStatus())
                .build();
    }
}
