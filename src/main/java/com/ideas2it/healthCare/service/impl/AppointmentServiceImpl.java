package com.ideas2it.healthCare.service.impl;

import com.ideas2it.healthCare.dto.AppointmentDto;
import com.ideas2it.healthCare.model.Appointment;
import com.ideas2it.healthCare.repo.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AppointmentServiceImpl {

    AppointmentRepository appointmentRepository;
    ModelMapper modelMapper;

    public AppointmentDto addAppointment(AppointmentDto appointmentDto) {
        Appointment appointment = modelMapper.map(appointmentDto, Appointment.class);
        return modelMapper.map(appointmentRepository.save(appointment), AppointmentDto.class);
    }
}
