package com.ideas2it.healthCare.service.impl;

import com.ideas2it.healthCare.common.Constants;
import com.ideas2it.healthCare.dto.AppointmentDto;
import com.ideas2it.healthCare.exception.NotFoundException;
import com.ideas2it.healthCare.mapper.AppointmentMapper;
import com.ideas2it.healthCare.mapper.ClinicMapper;
import com.ideas2it.healthCare.model.Appointment;
import com.ideas2it.healthCare.repo.AppointmentRepository;
import com.ideas2it.healthCare.service.AppointmentService;
import com.ideas2it.healthCare.service.ClinicService;
import com.ideas2it.healthCare.service.DoctorService;
import com.ideas2it.healthCare.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private AppointmentMapper appointmentMapper;


    public AppointmentDto addAppointment(AppointmentDto appointmentDto) {

        if (doctorService.isDoctorAvailable(appointmentDto.getDoctorId()) && patientService.isPatientAvailable(appointmentDto.getPatientId())
                && clinicService.isClinicAvailable(appointmentDto.getClinicId()) && isAppointmentAvailable(appointmentDto.getScheduledDate(), appointmentDto.getScheduledTime())) {
            LocalDateTime scheduledOn = LocalDateTime.of(appointmentDto.getScheduledDate(), appointmentDto.getScheduledTime());
            appointmentDto.getDoctor().setId(appointmentDto.getDoctorId());
            appointmentDto.getPatient().setId(appointmentDto.getPatientId());
            appointmentDto.getClinic().setId(appointmentDto.getClinicId());
            appointmentDto.setScheduledOn(scheduledOn);
            Appointment appointment = appointmentMapper.fromDto(appointmentDto);
            return appointmentMapper.toDto(appointmentRepository.save(appointment));
        }
        throw new NotFoundException("doctor, clinic or patient not found");
    }


    public List<AppointmentDto> getAppointments() {
        System.out.println("hi");
        List<Appointment> appointments = appointmentRepository.findAllByStatus(Constants.ACTIVE);
        System.out.println("hey da");
        if (appointments.isEmpty()) {
            throw new NotFoundException("No appointment Found");
        }
        return appointments.stream()
                .map(appointmentMapper::toDto)
                .collect(Collectors.toList());
    }


    public AppointmentDto getAppointmentById(int id) {

        return appointmentRepository.findByIdAndStatus(id, Constants.ACTIVE).stream().
                map(appointmentMapper::toDto).
                findFirst().
                orElseThrow(() -> new NotFoundException("NO appointment Found"));
    }


    public boolean isAppointmentAvailable(LocalDate date, LocalTime time) {

        return appointmentRepository.findByScheduledOnAndStatus(LocalDateTime.of(date, time), Constants.ACTIVE).isEmpty();
    }


    public String deleteAppointmentById(int id) {

        Optional<Appointment> appointmentById = appointmentRepository.findByIdAndStatus(id, Constants.ACTIVE);
        if (appointmentById.isEmpty()) {
            throw new NotFoundException("No Clinic Found");
        }
        Appointment appointment = appointmentById.get();
        appointment.setStatus(Constants.INACTIVE);
        appointmentRepository.save(appointment);
        return "deleted successfully";
    }

    public AppointmentDto rescheduleAppointment(AppointmentDto appointmentDto) {

        appointmentDto.setScheduledOn(LocalDateTime.of(appointmentDto.getScheduledDate(), appointmentDto.getScheduledTime()));
        Appointment appointment = appointmentMapper.fromDto(appointmentDto);
        return appointmentMapper.toDto(appointmentRepository.save(appointment));
    }
}
