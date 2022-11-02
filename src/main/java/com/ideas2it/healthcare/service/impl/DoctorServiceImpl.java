/**
 * <p>
 * This is the base package for all the service implement classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.service.impl;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.common.MessageConstants;
import com.ideas2it.healthcare.dto.DoctorDto;
import com.ideas2it.healthcare.exception.NotFoundException;
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

    private Long totalPages;
    @Autowired
    private DoctorRepository doctorRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public DoctorDto saveDoctor(DoctorDto doctorDto) {
        return DoctorMapper.toDto(doctorRepository.save(DoctorMapper.fromDto(doctorDto)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DoctorDto> getAllDoctors(Integer pageNumber, Integer totalRows) {
        setTotalPages(Math.round(((doctorRepository
                .findAllByStatus(Constants.ACTIVE).size() + 0.0) / totalRows) + 0.4));
        List<Doctor> doctors = doctorRepository.findAllByStatus(Constants.ACTIVE,
                PageRequest.of(pageNumber, totalRows)).toList();
        if (doctors.isEmpty()) {
            throw new NotFoundException(MessageConstants.DOCTORS_NOT_FOUND);
        }
        return doctors.stream().map(DoctorMapper::toDto).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DoctorDto getDoctorById(Integer id) {
        return doctorRepository
                .findByIdAndStatus(id, Constants.ACTIVE)
                .stream()
                .map(DoctorMapper::toDto)
                .findFirst()
                .orElseThrow(() -> new NotFoundException(MessageConstants.DOCTOR_NOT_FOUND));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DoctorDto updateDoctor(DoctorDto doctorDto) {
        return this.saveDoctor(doctorDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String removeDoctorById(Integer id) {
        if (1 <= doctorRepository.deleteDoctorById(id)) {
            return MessageConstants.DOCTOR_DELETED_SUCCESSFULLY;
        }
        return MessageConstants.DOCTOR_UNABLE_TO_DELETE;
    }

    public Long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }
}
