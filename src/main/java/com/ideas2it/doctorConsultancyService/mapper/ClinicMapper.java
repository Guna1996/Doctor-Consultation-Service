package com.ideas2it.doctorConsultancyService.mapper;

import com.ideas2it.doctorConsultancyService.dto.ClinicDto;
import com.ideas2it.doctorConsultancyService.model.Clinic;
import org.springframework.stereotype.Component;

@Component
public class ClinicMapper {


    public static Clinic fromDto(ClinicDto clinicDto) {
        return Clinic.builder()
                .id(clinicDto.getId())
                .name(clinicDto.getName())
                .doorNumber(clinicDto.getDoorNumber())
                .city(clinicDto.getCity())
                .state(clinicDto.getState())
                .pinCode(clinicDto.getPinCode())
                .contactNumber(clinicDto.getContactNumber())
                .build();
    }

    public static ClinicDto toDto(Clinic clinic) {

        return ClinicDto.builder()
                .id(clinic.getId())
                .name(clinic.getName())
                .doorNumber(clinic.getDoorNumber())
                .city(clinic.getCity())
                .state(clinic.getState())
                .pinCode(clinic.getPinCode())
                .contactNumber(clinic.getContactNumber())
                .build();
    }
}
