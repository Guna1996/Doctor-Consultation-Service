/**
 * <p>
 * This is the base package for all the mapper classes
 * which is for DoctorMapper, PatientMapper and ClinicMapper
 * classes
 * </p>
 * Copyright 2022 - Ideas2it
 */
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

/**
 * <p>
 * DoctorClinicMapper is used convert DoctorClinic object to
 * DoctorClinicDto and DoctorClinicDto into DoctorClinic
 * </p>
 *
 * @author Mohamed Jubair
 * @version 1
 * @since 2022-10-10
 */
public class DoctorClinicMapper {

    /**
     * <p>
     * This method is used to convert Doctor Clinic to
     * DoctorClinic model
     * </p>
     *
     * @return {@link DoctorClinic}
     */
    public static DoctorClinic fromDto(DoctorClinicDto doctorClinicDto) {
        DoctorClinic doctorClinic = new DoctorClinic();
        if (null != doctorClinicDto) {
            doctorClinic.setId(doctorClinicDto.getId());
            doctorClinic.setStatus(doctorClinicDto.getStatus());
            DoctorDto doctorDto = doctorClinicDto.getDoctor();
            if (null != doctorDto) {
                Doctor doctor = new Doctor();
                doctor.setId(doctorDto.getId());
                doctor.setName(doctorDto.getName());
                doctor.setConsultationFee(doctorDto.getConsultationFee());
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
            if (null != clinicDto) {
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
            if (null != timeslotsDto) {
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

    /**
     * <p>
     * This method is used to convert DoctorClinic to
     * DoctorClinicDto
     * </p>
     *
     * @return {@link DoctorClinicDto}
     */
    public static DoctorClinicDto toDto(DoctorClinic doctorClinic) {
        DoctorClinicDto doctorClinicDto = new DoctorClinicDto();
        if (null != doctorClinic) {
            doctorClinicDto.setId(doctorClinic.getId());
            doctorClinicDto.setStatus(doctorClinic.getStatus());
            Doctor doctor = doctorClinic.getDoctor();
            if (null != doctor) {
                DoctorDto doctorDto = new DoctorDto();
                doctorDto.setId(doctor.getId());
                doctorDto.setName(doctor.getName());
                doctorDto.setDateOfBirth(doctor.getDateOfBirth());
                doctorDto.setGender(doctor.getGender());
                doctorDto.setConsultationFee(doctor.getConsultationFee());
                doctorDto.setQualification(doctor.getQualification());
                doctorDto.setDateOfRegistration(doctor.getDateOfRegistration());
                doctorDto.setMobileNumber(doctor.getMobileNumber());
                doctorDto.setCity(doctor.getCity());
                doctorDto.setStatus(doctor.getStatus());
                doctorClinicDto.setDoctor(doctorDto);
            }
            Clinic clinic = doctorClinic.getClinic();
            if (null != clinic) {
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
            if (null != timeslots) {
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
