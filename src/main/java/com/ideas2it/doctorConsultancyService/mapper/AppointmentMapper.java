package com.ideas2it.doctorConsultancyService.mapper;

import com.ideas2it.doctorConsultancyService.dto.AppointmentDto;
import com.ideas2it.doctorConsultancyService.model.Appointment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
public class AppointmentMapper {

    public static Appointment fromDto(AppointmentDto appointmentDto) {
        return Appointment.builder()
                .id(appointmentDto.getId())
                .scheduledOn(appointmentDto.getScheduledOn())
                .patient(PatientMapper.fromDto(appointmentDto.getPatientDto()))
                .doctor(DoctorMapper.fromDto(appointmentDto.getDoctorDto()))
                .clinic(ClinicMapper.fromDto(appointmentDto.getClinicDto()))
                .build();
    }

    public static AppointmentDto toDto(Appointment appointment) {
        return AppointmentDto.builder()
                .id(appointment.getId())
                .scheduledOn(appointment.getScheduledOn())
                .patientDto(PatientMapper.toDto(appointment.getPatient()))
                .doctorDto(DoctorMapper.toDto(appointment.getDoctor()))
                .clinicDto(ClinicMapper.toDto(appointment.getClinic()))
                .build();
    }
}
