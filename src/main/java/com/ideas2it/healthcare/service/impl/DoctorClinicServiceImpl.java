/**
 * <p>
 * This package contains classes are DoctorClinicImpl,
 * PatientImpl, DoctorImpl, ClinicImpl,
 * AppointmentImpl, FeedbackImpl, SpecializationImpl,
 * TimeslotImpl, VitalsImpl
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.service.impl;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.common.ErrorConstants;
import com.ideas2it.healthcare.common.MessageConstants;
import com.ideas2it.healthcare.dto.DoctorClinicDto;
import com.ideas2it.healthcare.dto.TimeslotDto;
import com.ideas2it.healthcare.exception.NotFoundException;
import com.ideas2it.healthcare.exception.SqlException;
import com.ideas2it.healthcare.mapper.DoctorClinicMapper;
import com.ideas2it.healthcare.mapper.TimeslotMapper;
import com.ideas2it.healthcare.model.DoctorClinic;
import com.ideas2it.healthcare.model.Timeslot;
import com.ideas2it.healthcare.repository.DoctorClinicRepository;
import com.ideas2it.healthcare.service.DoctorClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * DoctorClinicServiceImpl class implements DoctorClinicService
 * and it contains methods and with helps of passing object to
 * DoctorClinicRepository interface
 * </p>
 *
 * @author Ramachandran
 * @version 1
 * @since 2022-10-10
 */
@Service
public class DoctorClinicServiceImpl implements DoctorClinicService {

    @Autowired
    private DoctorClinicRepository doctorClinicRepository;

    /**
     * {@inheritDoc}
     */
    public DoctorClinicDto assignDoctorToClinic(DoctorClinicDto doctorClinicDto) {
        if (!isDoctorAvailable(doctorClinicDto.getDoctor().getId(),
                doctorClinicDto.getClinic().getId(), doctorClinicDto.getTimeslots())) {
            throw new NotFoundException(ErrorConstants.DOCTOR_ALREADY_ASSIGNED_TO_THIS_CLINIC);
        } else {
            try {
                return DoctorClinicMapper
                        .toDto(doctorClinicRepository.save(DoctorClinicMapper.fromDto(doctorClinicDto)));
            } catch (DataAccessException exception) {
                throw new SqlException(exception.getMessage());
            }
        }
    }

    private boolean isDoctorAvailable(int id, int id1, List<TimeslotDto> timeslotsDto) {
        List<DoctorClinic> doctorClinics = doctorClinicRepository
                .findByDoctorIdAndStatus(id, Constants.ACTIVE);
        if (!doctorClinics.isEmpty()) {
            for (TimeslotDto timeslotDto: timeslotsDto) {
                for (DoctorClinic doctorClinic : doctorClinics) {
                    List<Timeslot> timeslots = doctorClinic.getTimeslots();
                    for (Timeslot timeslot : timeslots) {
                        if (timeslot.getId() == timeslotDto.getId())
                            return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public String removeDoctorFromClinic(Integer id) {
        try {
            if (1 <= doctorClinicRepository.removeDoctorClinicById(id)) {
                return MessageConstants.SUCCESSFULLY_REMOVED_DOCTOR_FROM_CLINIC;
            }
            throw new NotFoundException(ErrorConstants.DOCTOR_UNABLE_TO_REMOVE);
        } catch (DataAccessException exception) {
            throw new SqlException(ErrorConstants.DATABASE_NOT_FOUND);
        }
    }

    /**
     * {@inheritDoc}
     */
    public DoctorClinicDto getTimeslotsByDoctorIdAndClinicId(Integer doctorId, Integer clinicId) {
        try {
            return DoctorClinicMapper.toDto(doctorClinicRepository
                    .findByDoctorIdAndClinicIdAndStatus(doctorId, clinicId, Constants.ACTIVE)
                    .orElseThrow(() -> new NotFoundException(
                            MessageConstants.DOCTOR_ID_CLINIC_ID_NOT_FOUND)));
        } catch (DataAccessException exception) {
            throw new SqlException(ErrorConstants.DATABASE_NOT_FOUND);
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<DoctorClinicDto> getDoctorsByClinicId(Integer clinicId, Integer pageNumber,
                                                      Integer totalRows) {
        try {
            return doctorClinicRepository.findByClinicIdAndStatus(clinicId, Constants.ACTIVE,
                            PageRequest.of(pageNumber, totalRows)).toList().stream()
                    .map(DoctorClinicMapper::toDto).collect(Collectors.toList());
        } catch (DataAccessException exception) {
            throw new SqlException(ErrorConstants.DATABASE_NOT_FOUND);
        }
    }

    /**
     * {@inheritDoc}
     */
    public Integer countOfDoctorsByClinicId(Integer clinicId) {
        try {
            return doctorClinicRepository.countByClinicIdAndStatus(clinicId, Constants.ACTIVE);
        } catch (DataAccessException exception) {
            throw new SqlException(ErrorConstants.DATABASE_NOT_FOUND);
        }
    }
}

