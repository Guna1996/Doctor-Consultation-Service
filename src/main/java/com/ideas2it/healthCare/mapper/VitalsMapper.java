package com.ideas2it.healthCare.mapper;

import com.ideas2it.healthCare.dto.DoctorDto;
import com.ideas2it.healthCare.dto.PatientDto;
import com.ideas2it.healthCare.dto.VitalsDto;
import com.ideas2it.healthCare.model.Doctor;
import com.ideas2it.healthCare.model.Patient;
import com.ideas2it.healthCare.model.Vitals;

public class VitalsMapper {

    public static Vitals fromDto(VitalsDto vitalsDto) {
        Vitals vitals = new Vitals();
        if(null != vitalsDto) {
            vitals.setId(vitalsDto.getId());
            vitals.setHeight(vitalsDto.getHeight());
            vitals.setWeight(vitalsDto.getWeight());
            vitals.setPulse(vitalsDto.getPulse());
            vitals.setSystolic(vitalsDto.getSystolic());
            vitals.setDiastolic(vitalsDto.getDiastolic());
            vitals.setSugarLevel(vitalsDto.getSugarLevel());
            vitals.setStatus(vitalsDto.getStatus());
            vitals.setBloodPressure(vitalsDto.getBloodPressure());
            DoctorDto doctorDto = vitalsDto.getDoctor();
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
                vitals.setDoctor(doctor);
            }
            PatientDto patientDto = vitalsDto.getPatient();
            if(null != patientDto) {
                Patient patient = new Patient();
                patient.setId(patient.getId());
                patient.setName(patientDto.getName());
                patient.setDateOfBirth(patientDto.getDateOfBirth());
                patient.setMobileNumber(patientDto.getMobileNumber());
                patient.setGender(patientDto.getGender());
                patient.setEmail(patientDto.getEmail());
                patient.setStatus(patientDto.getStatus());
                vitals.setPatient(patient);
            }
        }
        return vitals;
    }
    public static VitalsDto toDto(Vitals vitals) {
        VitalsDto vitalsDto = new VitalsDto();
        if(null != vitals) {
            vitalsDto.setId(vitals.getId());
            vitalsDto.setHeight(vitals.getHeight());
            vitalsDto.setWeight(vitals.getWeight());
            vitalsDto.setPulse(vitals.getPulse());
            vitalsDto.setSystolic(vitals.getSystolic());
            vitalsDto.setDiastolic(vitals.getDiastolic());
            vitalsDto.setSugarLevel(vitals.getSugarLevel());
            vitalsDto.setStatus(vitals.getStatus());
            vitalsDto.setBloodPressure(vitals.getBloodPressure());
            Doctor doctor = vitals.getDoctor();
            if(null != doctor) {
                DoctorDto doctorDto =new DoctorDto();
                doctorDto.setId(doctor.getId());
                doctorDto.setName(doctor.getName());
                doctorDto.setGender(doctor.getGender());
                doctorDto.setDateOfBirth(doctor.getDateOfBirth());
                doctorDto.setDateOfRegistration(doctor.getDateOfRegistration());
                doctorDto.setQualification(doctor.getQualification());
                doctorDto.setMobileNumber(doctor.getMobileNumber());
                doctorDto.setGender(doctor.getGender());
                doctorDto.setCity(doctor.getCity());
                doctorDto.setStatus(doctor.getStatus());
                vitalsDto.setDoctor(doctorDto);
            }
            Patient patient = vitals.getPatient();
            if(null != patient) {
                PatientDto patientDto = new PatientDto();
                patientDto.setId(patient.getId());
                patientDto.setName(patient.getName());
                patientDto.setDateOfBirth(patient.getDateOfBirth());
                patientDto.setMobileNumber(patient.getMobileNumber());
                patientDto.setGender(patient.getGender());
                patientDto.setEmail(patient.getEmail());
                patientDto.setStatus(patient.getStatus());
                vitalsDto.setPatient(patientDto);
            }
        }
        return vitalsDto;
    }
}
