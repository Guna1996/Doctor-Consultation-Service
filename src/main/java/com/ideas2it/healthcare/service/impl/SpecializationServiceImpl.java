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
import com.ideas2it.healthcare.dto.SpecializationDto;
import com.ideas2it.healthcare.exception.NotFoundException;
import com.ideas2it.healthcare.exception.SqlException;
import com.ideas2it.healthcare.mapper.SpecializationMapper;
import com.ideas2it.healthcare.model.Specialization;
import com.ideas2it.healthcare.repository.SpecializationRepository;
import com.ideas2it.healthcare.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * This SpecializationServiceImpl class is a service class this class implements
 * SpecializationService which is an interface and get information from
 * the repository and provided to controller through
 * DoctorDto
 * </p>
 *
 * @author Mohamed Jubair
 * @version 1
 * @since 2022-10-10
 */
@Service
public class SpecializationServiceImpl implements SpecializationService {

    @Autowired
    private SpecializationRepository specializationRepository;

    /**
     * {@inheritDoc}
     */
    public SpecializationDto saveSpecialization(SpecializationDto specializationDto) {
        try {
            return SpecializationMapper.toDto(specializationRepository
                    .save(SpecializationMapper.fromDto(specializationDto)));
        } catch (DataIntegrityViolationException exception) {
            throw new NotFoundException(ErrorConstants.SPECIALIZATION_ALREADY_EXISTS);
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<SpecializationDto> getAllSpecializations(Integer pageNumber, Integer totalRows) {
        try {
            List<Specialization> specializations = specializationRepository
                    .findAllByStatus(Constants.ACTIVE,
                            PageRequest.of(pageNumber, totalRows)).toList();
            if (specializations.isEmpty()) {
                throw new NotFoundException(ErrorConstants.SPECIALIZATIONS_NOT_FOUND);

            }
            return specializations.stream().map(SpecializationMapper::toDto).collect(Collectors.toList());
        } catch (Exception exception) {
            throw new SqlException(exception.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    public SpecializationDto getSpecializationById(Integer id) {
        try {
            return specializationRepository
                    .findByIdAndStatus(id, Constants.ACTIVE)
                    .stream()
                    .map(SpecializationMapper::toDto)
                    .findFirst()
                    .orElseThrow(() -> new NotFoundException(ErrorConstants.SPECIALIZATION_NOT_FOUND));
        } catch (Exception exception) {
            throw new SqlException(exception.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SpecializationDto updateSpecialization(SpecializationDto specializationDto) {
        try {
            return SpecializationMapper.toDto(specializationRepository
                    .save(SpecializationMapper.fromDto(specializationDto)));
        } catch (Exception exception) {
            throw new SqlException(exception.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    public String removeSpecializationById(Integer id) {
        try {
            if (1 <= specializationRepository.removeSpecializationById(id)) {
                return MessageConstants.SPECIALIZATION_DELETED_SUCCESSFULLY;
            }
            throw new NotFoundException(ErrorConstants.SPECIALIZATION_NOT_FOUND);
        } catch (Exception exception) {
            throw new SqlException(exception.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    public Integer countOfSpecializations() {
        try {
            return specializationRepository.countByStatus(Constants.ACTIVE);
        } catch (Exception exception) {
            throw new SqlException(exception.getMessage());
        }
    }
}
