/**
 * <p>
 * This is the base package for all the service implement classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.doctorConsultancyService.service.impl;

import com.ideas2it.doctorConsultancyService.common.Constants;
import com.ideas2it.doctorConsultancyService.dto.DoctorDto;
import com.ideas2it.doctorConsultancyService.exception.NotFoundException;
import com.ideas2it.doctorConsultancyService.mapper.DoctorMapper;
import com.ideas2it.doctorConsultancyService.model.Doctor;
import com.ideas2it.doctorConsultancyService.repo.DoctorRepository;
import com.ideas2it.doctorConsultancyService.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * This DoctorServiceImpl class is a service class this class implements
 * DoctorService which is an interface and get information from
 * the repository and provided to controller through
 * DoctorDto
 * </p>
 *
 * @author  Mohamed Jubair
 *
 * @version 1
 *
 * @since   2022-10-10
 */
@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private DoctorRepository doctorRepository;

    private ModelMapper modelMapper;

    /**
     * <p>
     * This method is used to create and update Doctor's record by
     * getting DoctorDto as an input and convert it in to
     * Doctor model with the help of mapper class
     * </p>
     *
     * @param doctorDto {@link DoctorDto}
     *
     */
    @Override
    public DoctorDto saveOrUpdate(DoctorDto doctorDto) {
        Doctor doctor = modelMapper.map(doctorDto, Doctor.class);
        doctor = doctorRepository.save(doctor);
        return modelMapper.map(doctor, DoctorDto.class);
    }

    /**
     * <p>
     * This method is used to get all Doctor's record by
     * getting all Doctors from database and convert it in to
     * DoctorDto with the help of mapper class
     * </p>
     *
     * @return {@link List<DoctorDto>}
     */
    @Override
    public List<DoctorDto> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAllByStatus(Constants.ACTIVE);
        if (!doctors.isEmpty()){
            List<DoctorDto> doctorDtos = new ArrayList<>();
            for (Doctor doctor : doctors){
                doctorDtos.add(modelMapper.map(doctor, DoctorDto.class));
            }
            return doctorDtos;
        } else {
            throw new NotFoundException("No Doctor is Preset");
        }

    }

    /**
     * <p>
     * This method is used to get the Doctor by id and
     * convert it into DoctorDto
     * </p>
     *
     * @param id {@link int}
     *
     * @return {@link DoctorDto} the employee object
     */
    @Override
    public DoctorDto getDoctorById(int id) {
        Doctor doctor = doctorRepository
                .findByIdAndStatus(id, Constants.ACTIVE)
                .orElseThrow(()-> new NotFoundException("No Doctor Found"));
        return modelMapper.map(doctor, DoctorDto.class);
    }

    /**
     * <p>
     * This method is used to delete the Doctor by id
     * </p>
     *
     * @param id {@link int}
     *
     */
    @Override
    public String deleteById(int id) {
        Doctor doctor = doctorRepository
                .findByIdAndStatus(id, Constants.ACTIVE)
                .orElseThrow(() -> new NotFoundException("No Doctor Founded"));
        doctor.setStatus(Constants.INACTIVE);
        doctorRepository.save(doctor);
        return "Deleted Successfully";

    }
}
