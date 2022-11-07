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
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * This Specialization Service Impl class is a service class this class implements
 * Specialization Service which is an interface and get information from
 * the repository and provided to controller through
 * Doctor Dto
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
    public String addSpecialization(SpecializationDto specializationDto) {
        try {
            specializationRepository.save(SpecializationMapper.fromDto(specializationDto));
            return MessageConstants.SPECIALIZATION_ADDED_SUCCESSFULLY;
        } catch (DataIntegrityViolationException exception) {
            throw new NotFoundException(ErrorConstants.SPECIALIZATION_ALREADY_EXISTS);
        } catch (DataAccessException exception) {
            throw new SqlException(ErrorConstants.CANNOT_ACCESS_DATABASE);
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
        } catch (DataAccessException exception) {
            throw new SqlException(ErrorConstants.CANNOT_ACCESS_DATABASE);
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
        } catch (DataAccessException exception) {
            throw new SqlException(ErrorConstants.CANNOT_ACCESS_DATABASE);
        }
    }

    /**
     * {@inheritDoc}
     */
    public String updateSpecialization(SpecializationDto specializationDto) {
        addSpecialization(specializationDto);
        return MessageConstants.SPECIALIZATION_UPDATED_SUCCESSFULLY;
    }

    /**
     * {@inheritDoc}
     */
    public String removeSpecializationById(Integer id) {
        try {
            if (1 <= specializationRepository.removeSpecializationById(id)) {
                return MessageConstants.SPECIALIZATION_REMOVED_SUCCESSFULLY;
            }
            throw new NotFoundException(ErrorConstants.SPECIALIZATION_NOT_FOUND);
        } catch (DataAccessException exception) {
            throw new SqlException(ErrorConstants.CANNOT_ACCESS_DATABASE);
        }
    }

    /**
     * {@inheritDoc}
     */
    public Integer getSpecializationsCount() {
        try {
            return specializationRepository.countByStatus(Constants.ACTIVE);
        } catch (DataAccessException exception) {
            throw new SqlException(ErrorConstants.CANNOT_ACCESS_DATABASE);
        }
    }
}
