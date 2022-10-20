/**
 * <p>
 * This is the base package for all the service implement classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.service.impl;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.common.UserConstants;
import com.ideas2it.healthcare.dto.DoctorDto;
import com.ideas2it.healthcare.exception.NotFoundException;
import com.ideas2it.healthcare.mapper.DoctorMapper;
import com.ideas2it.healthcare.model.Doctor;
import com.ideas2it.healthcare.repo.DoctorRepository;
import com.ideas2it.healthcare.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
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
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;


    /**
     * {@inheritDoc}
     */
    @Override
    public DoctorDto saveOrUpdateDoctor(DoctorDto doctorDto) {
        doctorDto.setStatus(Constants.ACTIVE);
        Doctor doctor = doctorRepository.save(DoctorMapper.fromDto(doctorDto));
        return DoctorMapper.toDto(doctor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DoctorDto> getAllDoctors(int pageNumber, int totalRows) {
        List<Doctor> doctors = doctorRepository.findAllByStatus(Constants.ACTIVE
                , PageRequest.of(pageNumber, totalRows)).toList();
        if (doctors.isEmpty()) {
            throw new NotFoundException(UserConstants.DOCTORS_NOT_FOUND);
        }
        return doctors.stream().map(DoctorMapper::toDto).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DoctorDto getDoctorById(int id) {
        Doctor doctor = doctorRepository
                .findByIdAndStatus(id, Constants.ACTIVE)
                .orElseThrow(() -> new NotFoundException(UserConstants.DOCTOR_NOT_FOUND));
        return DoctorMapper.toDto(doctor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String deleteDoctorById(int id) {
        if (doctorRepository.deleteDoctorById(id) == 1) {
            return UserConstants.DELETED_SUCCESSFULLY;
        }
        return UserConstants.DOCTOR_NOT_FOUND_TO_DELETE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDoctorAvailable(int id) {
        return doctorRepository.existsByIdAndStatus(id, Constants.ACTIVE);
    }
}
