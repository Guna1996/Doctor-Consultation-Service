package com.ideas2it.healthCare.service;

import com.ideas2it.healthCare.dto.AppointmentDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AppointmentService {

    AppointmentDto addAppointment(AppointmentDto appointmentDto);
    List<AppointmentDto> getAppointments();

    AppointmentDto getAppointmentById(int id);
//
//    AppointmentDto updateappointment(AppointmentDto appointmentDto) throws NotFoundException;
//
//    String deleteappointmentById(int id) throws NotFoundException;
}
