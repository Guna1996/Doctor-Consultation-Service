package com.ideas2it.healthCare.service;

import com.ideas2it.healthCare.dto.AppointmentDto;

public interface AppointmentService {

    AppointmentDto addAppointment(AppointmentDto appointmentDto);

//    List<AppointmentDto> getappointments() throws NotFoundException;
//
//    AppointmentDto getappointmentById(int id) throws NotFoundException;
//
//    AppointmentDto updateappointment(AppointmentDto appointmentDto) throws NotFoundException;
//
//    String deleteappointmentById(int id) throws NotFoundException;
}
