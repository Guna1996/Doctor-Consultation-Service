package com.ideas2it.healthCare.mapper;

import com.ideas2it.healthCare.dto.AppointmentDto;
import com.ideas2it.healthCare.model.Appointment;
import org.mapstruct.Mapper;

@Mapper
public interface AppointmentMapper {
    Appointment fromDto(AppointmentDto appointmentDto);

    AppointmentDto toDto(Appointment appointment);
}