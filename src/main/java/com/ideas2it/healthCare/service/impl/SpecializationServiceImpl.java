/**
 * <p>
 * This is the base package for all the service implement classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthCare.service.impl;

import com.ideas2it.healthCare.common.Constants;
import com.ideas2it.healthCare.common.UserConstants;
import com.ideas2it.healthCare.dto.DoctorDto;
import com.ideas2it.healthCare.dto.SpecializationDto;
import com.ideas2it.healthCare.exception.NotFoundException;
import com.ideas2it.healthCare.mapper.SpecializationMapper;
import com.ideas2it.healthCare.model.Specialization;
import com.ideas2it.healthCare.repo.SpecializationRepository;
import com.ideas2it.healthCare.service.SpecializationService;

import org.springframework.beans.factory.annotation.Autowired;
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
     * <p>
     * This method is used to create and Update Specialization's record by
     * getting SpecializationDto as an input and convert it in to
     * Specialization model with the help of mapper class
     * </p>
     *
     * @param specializationDto {@link SpecializationDto}
     */
    @Override
    public SpecializationDto saveOrUpdate(SpecializationDto specializationDto) {
        Specialization specialization =  specializationRepository.save(SpecializationMapper.fromDto(specializationDto));
        return SpecializationMapper.toDto(specialization);
    }

    /**
     * <p>
     * This method is used to get all Specialization's record by
     * getting all Specializations from database and convert it in to
     * SpecializationDto with the help of mapper class
     * </p>
     *
     * @return {@link List <DoctorDto>}
     */
    @Override
    public List<SpecializationDto> getAllSpecializations() {
        List<Specialization> specializations = specializationRepository.findAllByStatus(Constants.ACTIVE);
        if (specializations.isEmpty()) {
            throw new NotFoundException(UserConstants.NO_SPECIALIZATION_IS_PRESENT);

        }
        return specializations.stream().map(SpecializationMapper::toDto).collect(Collectors.toList());
    }

    /**
     * <p>
     * This method is used to get the Specialization by id and
     * convert it into SpecializationDto
     * </p>
     *
     * @param id {@link int}
     * @return {@link DoctorDto}
     */
    @Override
    public SpecializationDto getSpecializationById(int id) {
        Specialization specialization = specializationRepository
                .findByIdAndStatus(id, Constants.ACTIVE)
                .orElseThrow(()-> new NotFoundException(UserConstants.NO_SPECIALIZATION_IS_PRESENT));
        return SpecializationMapper.toDto(specialization);
    }

    /**
     * <p>
     * This method is used to delete the Specialization by id
     * </p>
     *
     * @param id {@link int}
     * @return {@link String}
     */
    @Override
    public String deleteById(int id) {
        Specialization specialization = specializationRepository
                .findByIdAndStatus(id, Constants.ACTIVE)
                .orElseThrow(() -> new NotFoundException(UserConstants.NO_SPECIALIZATION_IS_PRESENT));
        specialization.setStatus(Constants.INACTIVE);
        specializationRepository.save(specialization);
        return UserConstants.DELETED_SUCCESSFULLY;
    }
}
