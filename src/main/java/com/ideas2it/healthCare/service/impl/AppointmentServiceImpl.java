package com.ideas2it.healthCare.service.impl;

import com.ideas2it.healthCare.common.Constants;
import com.ideas2it.healthCare.dto.AppointmentDto;
import com.ideas2it.healthCare.exception.NotFoundException;
import com.ideas2it.healthCare.mapper.AppointmentMapper;
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
import java.time.Period;
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


    public AppointmentDto addAppointment(AppointmentDto appointmentDto) {
        System.out.println(appointmentDto.getScheduledOn());
        LocalDate date = appointmentDto.getScheduledOn().toLocalDate();
        LocalDate currentDate = LocalDate.now();
        if (Period.between(date, currentDate).getDays() < 0) {
            if (doctorService.isDoctorAvailable(appointmentDto.getDoctor().getId()) && patientService.isPatientAvailable(appointmentDto.getPatient().getId())
                    && clinicService.isClinicAvailable(appointmentDto.getClinic().getId())) {
                return save(appointmentDto);
            }
            throw new NotFoundException("doctor, clinic or patient not found");
        }
        throw new NotFoundException("please enter valid date and time");
    }


    public List<AppointmentDto> getAppointments() {
        System.out.println("hi");
        List<Appointment> appointments = appointmentRepository.findAllByStatus(Constants.ACTIVE);
        System.out.println("hey da");
        if (appointments.isEmpty()) {
            throw new NotFoundException("No appointment Found");
        }
        return appointments.stream()
                .map(AppointmentMapper::toDto)
                .collect(Collectors.toList());
    }


    public AppointmentDto getAppointmentById(int id) {

        return appointmentRepository.findByIdAndStatus(id, Constants.ACTIVE).stream().
                map(AppointmentMapper::toDto).
                findFirst().
                orElseThrow(() -> new NotFoundException("NO appointments Found"));
    }


    public boolean isAppointmentAvailable(int id, LocalDateTime dateTime) {

        return appointmentRepository.findByDoctorIdAndScheduledOnAndStatus(id, dateTime, Constants.ACTIVE).isEmpty();
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
        Appointment appointment = AppointmentMapper.fromDto(appointmentDto);
        return AppointmentMapper.toDto(appointmentRepository.save(appointment));
    }

    public AppointmentDto save(AppointmentDto appointmentDto) {
        if (isAppointmentAvailable(appointmentDto.getDoctor().getId(), appointmentDto.getScheduledOn())) {
            Appointment appointment = AppointmentMapper.fromDto(appointmentDto);
            appointment.setStatus(Constants.ACTIVE);
            return AppointmentMapper.toDto(appointmentRepository.save(appointment));
        }
        throw new NotFoundException("This schedule is unavailable. kindly choose other schedule");
    }
}
