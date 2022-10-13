package com.ideas2it.healthCare.service.impl;

import com.ideas2it.healthCare.common.Constants;
import com.ideas2it.healthCare.dto.DoctorDto;
import com.ideas2it.healthCare.dto.SpecializationDto;
import com.ideas2it.healthCare.mapper.SpecializationMapper;
import com.ideas2it.healthCare.model.Specialization;
import com.ideas2it.healthCare.repo.SpecializationRepository;
import com.ideas2it.healthCare.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
        specializationRepository.save(SpecializationMapper.fromDto(specializationDto));
        return specializationDto;
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
        return (List<SpecializationDto>) SpecializationMapper.toDto((Specialization) specializationRepository.findAll());
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
        return SpecializationMapper.toDto(specializationRepository.findById(id).get());
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
        Specialization specialization = specializationRepository.findByIdAndStatus(id, Constants.ACTIVE);
        specialization.setStatus(Constants.INACTIVE);
        specializationRepository.save(specialization);
        return "Deleted Successfully";
    }
}
