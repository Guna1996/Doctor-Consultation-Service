package com.ideas2it.healthcare.mapper;

import com.ideas2it.healthcare.dto.ClinicDto;
import com.ideas2it.healthcare.dto.DoctorClinicDto;
import com.ideas2it.healthcare.dto.DoctorDto;
import com.ideas2it.healthcare.dto.TimeslotDto;
import com.ideas2it.healthcare.model.Clinic;
import com.ideas2it.healthcare.model.Doctor;
import com.ideas2it.healthcare.model.DoctorClinic;
import com.ideas2it.healthcare.model.Timeslot;
import java.util.ArrayList;
import java.util.List;

public class DoctorClinicMapper {

    public static DoctorClinic fromDto(DoctorClinicDto doctorClinicDto) {
        DoctorClinic doctorClinic = new DoctorClinic();
        if (doctorClinicDto != null) {
            doctorClinic.setId(doctorClinicDto.getId());
            doctorClinic.setStatus(doctorClinicDto.getStatus());

            DoctorDto doctorDto = doctorClinicDto.getDoctor();
            if (doctorDto != null) {
                Doctor doctor = new Doctor();
                doctor.setId(doctorDto.getId());
                doctor.setName(doctorDto.getName());
                doctor.setDateOfBirth(doctorDto.getDateOfBirth());
                doctor.setGender(doctorDto.getGender());
                doctor.setQualification(doctorDto.getQualification());
                doctor.setDateOfRegistration(doctorDto.getDateOfRegistration());
                doctor.setMobileNumber(doctorDto.getMobileNumber());
                doctor.setCity(doctorDto.getCity());
                doctor.setStatus(doctorDto.getStatus());
                doctorClinic.setDoctor(doctor);
            }

            ClinicDto clinicDto = doctorClinicDto.getClinic();
            if (clinicDto != null) {
                Clinic clinic = new Clinic();
                clinic.setId(clinicDto.getId());
                clinic.setName(clinicDto.getName());
                clinic.setDoorNumber(clinicDto.getDoorNumber());
                clinic.setStreetName(clinicDto.getStreetName());
                clinic.setCity(clinicDto.getCity());
                clinic.setState(clinicDto.getState());
                clinic.setPinCode(clinicDto.getPinCode());
                clinic.setContactNumber(clinicDto.getContactNumber());
                doctorClinic.setClinic(clinic);
            }

            List<TimeslotDto> timeslotsDto = doctorClinicDto.getTimeslots();
            if (timeslotsDto != null) {
                List<Timeslot> timeslots = new ArrayList<>();
                timeslotsDto.forEach(timeslotDto -> {
                    Timeslot timeslot = new Timeslot();
                    timeslot.setId(timeslotDto.getId());
                    timeslot.setTimeslot(timeslotDto.getTimeslot());
                    timeslots.add(timeslot);
                });
                doctorClinic.setTimeslots(timeslots);
            }
        }
        return doctorClinic;
    }

    public static DoctorClinicDto toDto(DoctorClinic doctorClinic) {
        DoctorClinicDto doctorClinicDto = new DoctorClinicDto();
        if (doctorClinic != null) {
            doctorClinicDto.setId(doctorClinic.getId());
            doctorClinicDto.setStatus(doctorClinic.getStatus());

            Doctor doctor = doctorClinic.getDoctor();
            if (doctor != null) {
                DoctorDto doctorDto = new DoctorDto();
                doctorDto.setId(doctor.getId());
                doctorDto.setName(doctor.getName());
                doctorDto.setDateOfBirth(doctor.getDateOfBirth());
                doctorDto.setGender(doctor.getGender());
                doctorDto.setQualification(doctor.getQualification());
                doctorDto.setDateOfRegistration(doctor.getDateOfRegistration());
                doctorDto.setMobileNumber(doctor.getMobileNumber());
                doctorDto.setCity(doctor.getCity());
                doctorDto.setStatus(doctor.getStatus());
                doctorClinicDto.setDoctor(doctorDto);
            }

            Clinic clinic = doctorClinic.getClinic();
            if (clinic != null) {
                ClinicDto clinicDto = new ClinicDto();
                clinicDto.setId(clinic.getId());
                clinicDto.setName(clinic.getName());
                clinicDto.setDoorNumber(clinic.getDoorNumber());
                clinicDto.setStreetName(clinic.getStreetName());
                clinicDto.setCity(clinic.getCity());
                clinicDto.setState(clinic.getState());
                clinicDto.setPinCode(clinic.getPinCode());
                clinicDto.setContactNumber(clinic.getContactNumber());
                doctorClinicDto.setClinic(clinicDto);
            }

            List<Timeslot> timeslots = doctorClinic.getTimeslots();
            if (timeslots != null) {
                List<TimeslotDto> timeslotsDto = new ArrayList<>();
                timeslots.forEach(timeslot -> {
                    TimeslotDto timeslotDto = new TimeslotDto();
                    timeslotDto.setId(timeslot.getId());
                    timeslotDto.setTimeslot(timeslot.getTimeslot());
                    timeslotsDto.add(timeslotDto);
                });
                doctorClinicDto.setTimeslots(timeslotsDto);
            }
        }
        return doctorClinicDto;
    }
}
