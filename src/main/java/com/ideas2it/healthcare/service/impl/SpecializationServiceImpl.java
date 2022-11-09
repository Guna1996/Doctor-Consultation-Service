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
import com.ideas2it.healthcare.exception.CustomException;
import com.ideas2it.healthcare.mapper.SpecializationMapper;
import com.ideas2it.healthcare.model.Specialization;
import com.ideas2it.healthcare.repository.SpecializationRepository;
import com.ideas2it.healthcare.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String addOrUpdateSpecialization(SpecializationDto specializationDto) {
        String response;
        specializationRepository.save(SpecializationMapper.fromDto(specializationDto));
        if (0 == specializationDto.getId()) {
            response = MessageConstants.SPECIALIZATION_ADDED_SUCCESSFULLY;
        } else {
            response = MessageConstants.SPECIALIZATION_UPDATED_SUCCESSFULLY;
        }
        return response;
    }

    /**
     * {@inheritDoc}
     */
    public List<SpecializationDto> getAllSpecializations(Integer pageNumber, Integer totalRows) {
        List<Specialization> specializations = specializationRepository
                .findAllByStatus(Constants.ACTIVE,
                        PageRequest.of(pageNumber, totalRows)).toList();
        return specializations.stream().map(SpecializationMapper::toDto).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    public SpecializationDto getSpecializationById(Integer id) {
        return specializationRepository
                .findByIdAndStatus(id, Constants.ACTIVE)
                .stream()
                .map(SpecializationMapper::toDto)
                .findFirst()
                .orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    public String removeSpecializationById(Integer id) {
        String response = null;
        if (1 <= specializationRepository.removeSpecializationById(id)) {
            response = MessageConstants.SPECIALIZATION_REMOVED_SUCCESSFULLY;
        }
        return response;
    }

    /**
     * {@inheritDoc}
     */
    public Integer getSpecializationsCount() {
        return specializationRepository.countByStatus(Constants.ACTIVE);
    }
}
