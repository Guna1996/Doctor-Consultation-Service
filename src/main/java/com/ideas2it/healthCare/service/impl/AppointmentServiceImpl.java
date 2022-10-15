package com.ideas2it.healthCare.service.impl;

import com.ideas2it.healthCare.common.Constants;
import com.ideas2it.healthCare.dto.AppointmentDto;
import com.ideas2it.healthCare.dto.ClinicDto;
import com.ideas2it.healthCare.dto.DoctorDto;
import com.ideas2it.healthCare.dto.PatientDto;
import com.ideas2it.healthCare.exception.NotFoundException;
import com.ideas2it.healthCare.model.Appointment;
import com.ideas2it.healthCare.repo.AppointmentRepository;
import com.ideas2it.healthCare.service.AppointmentService;
import com.ideas2it.healthCare.service.ClinicService;
import com.ideas2it.healthCare.service.DoctorService;
import com.ideas2it.healthCare.service.PatientService;
import org.modelmapper.ModelMapper;
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
    private ModelMapper modelMapper;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private ClinicService clinicService;

    @Override
    public AppointmentDto addAppointment(AppointmentDto appointmentDto) {

        if (doctorService.isDoctorAvailable(appointmentDto.getDoctorId()) && patientService.isPatientAvailable(appointmentDto.getPatientId())
                && clinicService.isAvailableClinic(appointmentDto.getClinicId()) && isAppointmentAvailable(appointmentDto.getScheduledDate(), appointmentDto.getScheduledTime())) {
            LocalDateTime scheduledOn = LocalDateTime.of(appointmentDto.getScheduledDate(), appointmentDto.getScheduledTime());
            DoctorDto doctor = doctorService.getDoctorById(appointmentDto.getDoctorId());
            PatientDto patient = patientService.getPatientById(appointmentDto.getDoctorId());
            ClinicDto clinic = clinicService.getClinicById(appointmentDto.getClinicId());
            appointmentDto.setDoctor(doctor);
            appointmentDto.setPatient(patient);
            appointmentDto.setClinic(clinic);
            appointmentDto.setScheduledOn(scheduledOn);
            Appointment appointment = modelMapper.map(appointmentDto, Appointment.class);
            return modelMapper.map(appointmentRepository.save(appointment), AppointmentDto.class);
        } else {
            throw new NotFoundException("doctor, clinic or patient not found");
        }
    }

    @Override
    public List<AppointmentDto> getAppointments() {

        List<Appointment> appointments = appointmentRepository.findAllByStatus(Constants.ACTIVE);

        if (appointments.isEmpty()) {
            throw new NotFoundException("No appointment Found");
        }
        return appointments.stream()
                .map(appointment -> modelMapper.map(appointment, AppointmentDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public AppointmentDto getAppointmentById(int id) {

        return appointmentRepository.findByIdAndStatus(id, Constants.ACTIVE).stream().
                map(appointment -> modelMapper.map(appointment, AppointmentDto.class)).
                findFirst().
                orElseThrow(() -> new NotFoundException("NO appointments Found"));
    }

    @Override
    public boolean isAppointmentAvailable(LocalDate date, LocalTime time) {

        return appointmentRepository.findByScheduledOnAndStatus(LocalDateTime.of(date, time), Constants.ACTIVE).isEmpty();
    }

    @Override
    public String deleteAppointmentById(int id) {

        Optional<Appointment> appointmentById = appointmentRepository.findByIdAndStatus(id, Constants.ACTIVE);
        if (appointmentById.isPresent()) {
            Appointment appointment = appointmentById.get();
            appointment.setStatus(Constants.INACTIVE);
            appointmentRepository.save(appointment);
            return "deleted successfully";
        } else {
            throw new NotFoundException("No Clinic Found");
        }
    }

    public AppointmentDto rescheduleAppointment(AppointmentDto appointmentDto) {

        appointmentDto.setScheduledOn(LocalDateTime.of(appointmentDto.getScheduledDate(), appointmentDto.getScheduledTime()));
        Appointment appointment = modelMapper.map(appointmentDto, Appointment.class);
        return modelMapper.map(appointmentRepository.save(appointment), AppointmentDto.class);
    }
}
