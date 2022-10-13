package com.ideas2it.doctorConsultancyService.mapper;

import com.ideas2it.doctorConsultancyService.dto.DoctorDto;
import com.ideas2it.doctorConsultancyService.dto.SpecializationDto;
import com.ideas2it.doctorConsultancyService.model.Doctor;
import com.ideas2it.doctorConsultancyService.model.Specialization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * DoctorMapper is used convert Doctor object to
 * DoctorDto and DoctorDto into Doctor
 * </p>
 *
 * @author Mohamed Jubair
 *
 * @version 1
 *
 * @since 2022-07-18
 */
@Component
public class DoctorMapper {

    /**
     * <p>
     * This method is used to convert DoctorDto to Doctor model
     * getting all Doctors from database and convert it in to
     * DoctorDto with the help of mapper class
     * </p>
     *
     * @return {@link List<DoctorDto>}
     */
    public static Doctor fromDto(DoctorDto doctorDto){
        return Doctor.builder()
                .id(doctorDto.getId())
                .name(doctorDto.getName())
                .dateOfBirth(doctorDto.getDateOfBirth())
                .gender(doctorDto.getGender())
                .qualification(doctorDto.getQualification())
                .registrationYear(doctorDto.getRegistrationYear())
                .mobileNumber(doctorDto.getMobileNumber())
                .city(doctorDto.getCity())
                .specialization(doctorDto.getSpecializationDtos().stream().map(SpecializationMapper::fromDto).collect(Collectors.toList()))
                /*.feedbacks(doctorDto.getFeedbackDtos().stream().map(FeedbackMapper::fromDto).collect(Collectors.toList()))*/
                .appointments(doctorDto.getAppointmentDtos().stream().map(AppointmentMapper::fromDto).collect(Collectors.toList()))
                /*.vitals(doctorDto.getVitalsDtos())*/
                .build();
    }

    public static DoctorDto toDto(Doctor doctor){
        return DoctorDto.builder()
                .id(doctor.getId())
                .name(doctor.getName())
                .dateOfBirth(doctor.getDateOfBirth())
                .gender(doctor.getGender())
                .qualification(doctor.getQualification())
                .mobileNumber(doctor.getMobileNumber())
                .city(doctor.getCity())
                .specializationDtos(doctor.getSpecialization().stream().map(SpecializationMapper::toDto).collect(Collectors.toList()))
                .appointmentDtos(doctor.getAppointments().stream().map(AppointmentMapper::toDto).collect(Collectors.toList()))
                .build();
    }
}
