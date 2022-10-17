/**
 * <p>
 * This is the base package for all the service implement classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthCare.service.impl;

import com.ideas2it.healthCare.common.Constants;
import com.ideas2it.healthCare.dto.DoctorDto;
import com.ideas2it.healthCare.exception.NotFoundException;
import com.ideas2it.healthCare.model.Doctor;
import com.ideas2it.healthCare.repo.DoctorRepository;
import com.ideas2it.healthCare.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * This DoctorServiceImpl class is a service class this class implements
 * DoctorService which is an interface and get information from
 * the repository and provided to controller through
 * DoctorDto
 * </p>
 *
 * @author Mohamed Jubair
 * @version 1
 * @since 2022-10-10
 */
@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    //@Autowired
    private final ModelMapper modelMapper;

    /**
     * <p>
     * This method is used to create and update Doctor's record by
     * getting DoctorDto as an input and convert it in to
     * Doctor model with the help of mapper class
     * </p>
     *
     * @param doctorDto {@link DoctorDto}
     */
    @Override
    public DoctorDto saveOrUpdate(DoctorDto doctorDto) {
        System.out.println(doctorDto.getSpecializations().toString());
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
        if (!doctors.isEmpty()) {
            return doctors.stream()
                    .map(doctor -> modelMapper.map(doctor, DoctorDto.class))
                    .collect(Collectors.toList());
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
     * @return {@link DoctorDto} the employee object
     */
    @Override
    public DoctorDto getDoctorById(int id) {
        Doctor doctor = doctorRepository
                .findByIdAndStatus(id, Constants.ACTIVE)
                .orElseThrow(() -> new NotFoundException("No Doctor Found"));
        return modelMapper.map(doctor, DoctorDto.class);
    }

    /**
     * <p>
     * This method is used to find
     * whether the doctor is delete is
     * deleted or not by returning boolean
     * to another services
     * </p>
     *
     * @param id {@link int}
     *
     *@return {@link String}
     */
    @Override
    public boolean isDoctorAvailable(int id) {
        Optional<Doctor> doctorFromDb = doctorRepository.findByIdAndStatus(id, Constants.ACTIVE);
        if(doctorFromDb.isPresent()) {
            return true;
        } else {
            return false;
        }
    }
}
