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
import com.ideas2it.healthcare.dto.SpecializationDto;
import com.ideas2it.healthcare.exception.NotFoundException;
import com.ideas2it.healthcare.mapper.SpecializationMapper;
import com.ideas2it.healthcare.model.Specialization;
import com.ideas2it.healthcare.repo.SpecializationRepository;
import com.ideas2it.healthcare.service.SpecializationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
 * @author  Mohamed Jubair
 *
 * @version 1
 *
 * @since   2022-10-10
 */
@Service
public class SpecializationServiceImpl implements SpecializationService {

    @Autowired
    private SpecializationRepository specializationRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public SpecializationDto saveOrUpdateSpecialization(SpecializationDto specializationDto) {
        specializationDto.setStatus(Constants.ACTIVE);
        Specialization specialization =  specializationRepository.save(SpecializationMapper.fromDto(specializationDto));
        return SpecializationMapper.toDto(specialization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SpecializationDto> getAllSpecializations(int pageNumber, int totalRows) {
        List<Specialization> specializations = specializationRepository.findAllByStatus(Constants.ACTIVE,
                PageRequest.of(pageNumber, totalRows)).toList();
        if (specializations.isEmpty()) {
            throw new NotFoundException(UserConstants.NO_SPECIALIZATION_IS_PRESENT);

        }
        return specializations.stream().map(SpecializationMapper::toDto).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SpecializationDto getSpecializationById(int id) {
        Specialization specialization = specializationRepository
                .findByIdAndStatus(id, Constants.ACTIVE)
                .orElseThrow(()-> new NotFoundException(UserConstants.NO_SPECIALIZATION_IS_PRESENT));
        return SpecializationMapper.toDto(specialization);
    }

    /**
     * {@inheritDoc}
     */

    public String deleteSpecializationById(int id) {
        if (specializationRepository.deleteSpecializationById(id) == 1) {
            return UserConstants.DELETED_SUCCESSFULLY;
        }
        return UserConstants.NO_SPECIALIZATION_IS_PRESENT;
    }
}
