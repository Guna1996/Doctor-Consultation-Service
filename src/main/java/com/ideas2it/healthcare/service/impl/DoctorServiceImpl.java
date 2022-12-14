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
import com.ideas2it.healthcare.mapper.DoctorMapper;
import com.ideas2it.healthcare.model.Doctor;
import com.ideas2it.healthcare.repository.DoctorRepository;
import com.ideas2it.healthcare.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * This Doctor Service Impl class is a service class this class implements
 * Doctor Service which is an interface and get information from
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
    public String addOrUpdateDoctor(DoctorDto doctorDto) {
        String response;
        doctorRepository.save(DoctorMapper.fromDto(doctorDto));
        if (0 == doctorDto.getId()) {
            response = MessageConstants.DOCTOR_ADDED_SUCCESSFULLY;
        } else {
            response = MessageConstants.DOCTOR_UPDATED_SUCCESSFULLY;
        }
        return response;
    }

    /**
     * {@inheritDoc}
     */
    public List<DoctorDto> getAllDoctors(Integer pageNumber, Integer totalRows) {
        List<Doctor> doctors = doctorRepository.findAllByStatus(Constants.ACTIVE,
                PageRequest.of(pageNumber, totalRows)).toList();
        return doctors.stream().map(DoctorMapper::toDto).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    public DoctorDto getDoctorById(Integer id) {
        return doctorRepository
                .findByIdAndStatus(id, Constants.ACTIVE)
                .stream()
                .map(DoctorMapper::toDto)
                .findFirst()
                .orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    public String removeDoctorById(Integer id) {
        String response = null;
        if (1 <= doctorRepository.removeDoctorById(id)) {
            response = MessageConstants.DOCTOR_REMOVED_SUCCESSFULLY;
        }
        return response;
    }

        /**
         * {@inheritDoc}
         */
        public Integer getDoctorsCount () {
            return doctorRepository.countByStatus(Constants.ACTIVE);
        }
    }

