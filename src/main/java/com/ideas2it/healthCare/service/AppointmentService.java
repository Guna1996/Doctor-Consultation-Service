package com.ideas2it.healthCare.service;

import com.ideas2it.healthCare.dto.AppointmentDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentService {

    AppointmentDto addAppointment(AppointmentDto appointmentDto);
    List<AppointmentDto> getAppointments();

    AppointmentDto getAppointmentById(int id);

    boolean isAppointmentAvailable(LocalDate date, LocalTime time );

    AppointmentDto rescheduleAppointment(AppointmentDto appointmentDto);

   String deleteAppointmentById(int id);
}
