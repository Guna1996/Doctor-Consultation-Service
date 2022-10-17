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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final ModelMapper modelMapper;

    private final DoctorService doctorService;

    private final PatientService patientService;

    private final ClinicService clinicService;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, ModelMapper modelMapper, DoctorService doctorService, PatientService patientService, ClinicService clinicService) {
        this.appointmentRepository = appointmentRepository;
        this.modelMapper = modelMapper;
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.clinicService = clinicService;
    }

    @Override
    public AppointmentDto addAppointment(AppointmentDto appointmentDto) {

        if(doctorService.isDoctorAvailable(appointmentDto.getDoctorId()) && patientService.isPatientAvailable(appointmentDto.getPatientId())
            && clinicService.isAvailableClinic(appointmentDto.getClinicId())) {
            DoctorDto doctor = doctorService.getDoctorById(appointmentDto.getDoctorId());
            PatientDto patient = patientService.getPatientById(appointmentDto.getDoctorId());
            ClinicDto clinic = clinicService.getClinicById(appointmentDto.getClinicId());
            appointmentDto.setDoctor(doctor);
            appointmentDto.setPatient(patient);
            appointmentDto.setClinic(clinic);
            Appointment appointment = modelMapper.map(appointmentDto, Appointment.class);
            return modelMapper.map(appointmentRepository.save(appointment), AppointmentDto.class);
        } else {
            throw new NotFoundException("doctor, clinic or patient not found");
        }
    }

    @Override
    public List<AppointmentDto> getAppointments() {

        List<Appointment> appointments = appointmentRepository.findAllByStatus(Constants.ACTIVE);
        System.out.println("hi its work");

        if (appointments.isEmpty()) {
            throw new NotFoundException("No appointment Found");
        } else {
            return appointments.stream()
                    .map(appointment -> modelMapper.map(appointment, AppointmentDto.class))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public AppointmentDto getAppointmentById(int id) {
        return appointmentRepository.findByIdAndStatus(id, Constants.ACTIVE).stream().
                map(appointment -> modelMapper.map(appointment, AppointmentDto.class)).
                findFirst().
                orElseThrow(() -> new NotFoundException("NO appointments Found"));
    }


}
