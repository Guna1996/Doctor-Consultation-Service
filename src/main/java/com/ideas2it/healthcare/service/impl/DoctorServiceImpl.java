/**
 * <p>
 * This is the base package for all the service implement classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.service.impl;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.common.ErrorConstants;
import com.ideas2it.healthcare.common.MessageConstants;
import com.ideas2it.healthcare.dto.DoctorDto;
import com.ideas2it.healthcare.exception.NotFoundException;
import com.ideas2it.healthcare.exception.SqlException;
import com.ideas2it.healthcare.mapper.DoctorMapper;
import com.ideas2it.healthcare.model.Doctor;
import com.ideas2it.healthcare.repository.DoctorRepository;
import com.ideas2it.healthcare.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public DoctorDto saveDoctor(DoctorDto doctorDto) {
        try {
            return DoctorMapper.toDto(doctorRepository.save(DoctorMapper.fromDto(doctorDto)));
        } catch (SqlException exception) {
            throw new SqlException(exception.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DoctorDto> getAllDoctors(Integer pageNumber, Integer totalRows) {
        try {
            List<Doctor> doctors = doctorRepository.findAllByStatus(Constants.ACTIVE,
                    PageRequest.of(pageNumber, totalRows)).toList();
            if (doctors.isEmpty()) {
                throw new NotFoundException(ErrorConstants.DOCTORS_NOT_FOUND);
            }
            return doctors.stream().map(DoctorMapper::toDto).collect(Collectors.toList());
        } catch (SqlException exception) {
            throw new SqlException(exception.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DoctorDto getDoctorById(Integer id) {
        try {
            return doctorRepository
                    .findByIdAndStatus(id, Constants.ACTIVE)
                    .stream()
                    .map(DoctorMapper::toDto)
                    .findFirst()
                    .orElseThrow(() -> new NotFoundException(ErrorConstants.DOCTOR_NOT_FOUND));
        } catch (SqlException exception) {
            throw new SqlException(exception.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DoctorDto updateDoctor(DoctorDto doctorDto) {
        return saveDoctor(doctorDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String removeDoctorById(Integer id) {
        try {
            if (1 <= doctorRepository.removeDoctorById(id)) {
                return MessageConstants.DOCTOR_DELETED_SUCCESSFULLY;
            }
            throw new NotFoundException(ErrorConstants.DOCTOR_UNABLE_TO_DELETE);
        } catch (SqlException exception) {
            throw new SqlException(exception.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    public Integer countOfDoctors() {
        try {
            return doctorRepository.countByStatus(Constants.ACTIVE);
        } catch (SqlException exception) {
            throw new SqlException(exception.getMessage());
        }
    }
}
