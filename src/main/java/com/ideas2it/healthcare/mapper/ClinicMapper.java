package com.ideas2it.healthcare.mapper;

import com.ideas2it.healthcare.dto.ClinicDto;
import com.ideas2it.healthcare.dto.DoctorClinicDto;
import com.ideas2it.healthcare.model.Clinic;
import com.ideas2it.healthcare.model.DoctorClinic;

import java.util.ArrayList;
import java.util.List;


public class ClinicMapper {

    public static Clinic fromDto(ClinicDto clinicDto) {
        Clinic clinic = new Clinic();
        if (clinicDto != null) {
            clinic.setId(clinicDto.getId());
            clinic.setName(clinicDto.getName());
            clinic.setDoorNumber(clinicDto.getDoorNumber());
            clinic.setStreetName(clinicDto.getStreetName());
            clinic.setCity(clinicDto.getCity());
            clinic.setState(clinicDto.getState());
            clinic.setPinCode(clinicDto.getPinCode());
            clinic.setContactNumber(clinicDto.getContactNumber());
            clinic.setStatus(clinicDto.getState());

            List<DoctorClinicDto> doctorClinicsDto = clinicDto.getDoctors();
            if (doctorClinicsDto != null) {
                List<DoctorClinic> doctorClinics = new ArrayList<>();
                doctorClinicsDto.forEach(doctorClinicDto -> {
                    DoctorClinic doctorClinic = new DoctorClinic();
                    doctorClinic.setId(doctorClinicDto.getId());
                    doctorClinic.setStatus(doctorClinicDto.getStatus());
                    doctorClinics.add(doctorClinic);
                });
                clinic.setDoctors(doctorClinics);
            }
        }

        return clinic;
    }

    public static ClinicDto toDto(Clinic clinic) {
        ClinicDto clinicDto = new ClinicDto();
        if (clinic != null) {
            clinicDto.setId(clinic.getId());
            clinicDto.setName(clinic.getName());
            clinicDto.setDoorNumber(clinic.getDoorNumber());
            clinicDto.setStreetName(clinic.getStreetName());
            clinicDto.setCity(clinic.getCity());
            clinicDto.setState(clinic.getState());
            clinicDto.setPinCode(clinic.getPinCode());
            clinicDto.setContactNumber(clinic.getContactNumber());
            //clinicDto.setStatus(clinic.getState());

            List<DoctorClinic> doctorClinics = clinic.getDoctors();
            if (doctorClinics != null) {
                List<DoctorClinicDto> doctorClinicsDto = new ArrayList<>();
                doctorClinics.forEach(doctorClinic -> {
                    DoctorClinicDto doctorClinicDto = new DoctorClinicDto();
                    doctorClinicDto.setId(doctorClinic.getId());
                    doctorClinicDto.setStatus(doctorClinic.getStatus());
                    doctorClinicsDto.add(doctorClinicDto);
                });
                clinicDto.setDoctors(doctorClinicsDto);
            }
        }

        return clinicDto;
    }
}
