/**
 * <p>
 * This package contains classes are Doctor Clinic Impl,
 * Patient Impl, Doctor Impl, Clinic Impl,
 * Appointment Impl, Feedback Impl, Specialization Impl,
 * Timeslot Impl, Patient Vital Impl
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.service.impl;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.common.ErrorConstants;
import com.ideas2it.healthcare.common.MessageConstants;
import com.ideas2it.healthcare.dto.DoctorClinicDto;
import com.ideas2it.healthcare.dto.TimeslotDto;
import com.ideas2it.healthcare.exception.CustomException;
import com.ideas2it.healthcare.exception.DataBaseException;
import com.ideas2it.healthcare.mapper.DoctorClinicMapper;
import com.ideas2it.healthcare.model.DoctorClinic;
import com.ideas2it.healthcare.repository.DoctorClinicRepository;
import com.ideas2it.healthcare.service.DoctorClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * Doctor Clinic Service Impl class implements Doctor Clinic Service
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
    public String assignDoctorToClinic(DoctorClinicDto doctorClinicDto) {
        if (!isDoctorTimeslotAvailable(doctorClinicDto.getDoctor().getId(), doctorClinicDto.getTimeslots())) {
            return ErrorConstants.DOCTOR_ALREADY_ASSIGNED_TO_SOME_OTHER_CLINIC_AT_THIS_TIMESLOT;
        }
        try {
            doctorClinicRepository.save(DoctorClinicMapper.fromDto(doctorClinicDto));
            return MessageConstants.DOCTOR_ASSIGNED_TO_CLINIC_SUCCESSFULLY;
        } catch (DataIntegrityViolationException exception) {
            throw new CustomException(ErrorConstants.DOCTOR_ALREADY_ASSIGNED_TO_THIS_CLINIC);
        } catch (DataAccessException exception) {
            throw new DataBaseException(ErrorConstants.CANNOT_ACCESS_DATABASE);
        }
    }

    /**
     * <p>
     * This method is used to check whether a
     * doctor is assigned to different clinic in the
     * same timeslot and return boolean
     * </p>
     *
     * @param doctorId     {@link Integer}
     * @param timeslotsDto {@link List<TimeslotDto>}
     */
    private Boolean isDoctorTimeslotAvailable(Integer doctorId, List<TimeslotDto> timeslotsDto) {
        List<Integer> allTimeslotsOfDoctor = doctorClinicRepository.getTimeslots(doctorId);
        if (!allTimeslotsOfDoctor.isEmpty()) {
            for (TimeslotDto timeslotDto : timeslotsDto) {
                if (allTimeslotsOfDoctor.contains(timeslotDto.getId())) {
                    return false;
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
            throw new CustomException(ErrorConstants.DOCTOR_UNABLE_TO_REMOVE);
        } catch (DataAccessException exception) {
            throw new DataBaseException(ErrorConstants.CANNOT_ACCESS_DATABASE);
        }
    }

    /**
     * {@inheritDoc}
     */
    public DoctorClinicDto getTimeslotsByDoctorIdAndClinicId(Integer doctorId, Integer clinicId) {
        try {
            return DoctorClinicMapper.toDto(doctorClinicRepository
                    .findByDoctorIdAndClinicIdAndStatus(doctorId, clinicId, Constants.ACTIVE)
                    .orElseThrow(() -> new CustomException(
                            MessageConstants.DOCTOR_ID_CLINIC_ID_NOT_FOUND)));
        } catch (DataAccessException exception) {
            throw new DataBaseException(ErrorConstants.CANNOT_ACCESS_DATABASE);
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
            throw new DataBaseException(ErrorConstants.CANNOT_ACCESS_DATABASE);
        }
    }

    /**
     * {@inheritDoc}
     */
    public Integer getCountOfDoctorsByClinicId(Integer clinicId) {
        try {
            return doctorClinicRepository.countByClinicIdAndStatus(clinicId, Constants.ACTIVE);
        } catch (DataAccessException exception) {
            throw new DataBaseException(ErrorConstants.CANNOT_ACCESS_DATABASE);
        }
    }

    /**
     * {@inheritDoc}
     */
    public String updateDoctorTimeslotsInThatClinic(DoctorClinicDto doctorClinicDto) {
        if (!isDoctorTimeslotAvailable(doctorClinicDto.getDoctor().getId(), doctorClinicDto.getTimeslots())) {
            return ErrorConstants.DOCTOR_ALREADY_ASSIGNED_TO_SOME_OTHER_CLINIC_AT_THIS_TIMESLOT;
        }
        try {
            DoctorClinic doctorClinic = doctorClinicRepository.findByDoctorIdAndClinicIdAndStatus(doctorClinicDto.getDoctor().getId(),
                    doctorClinicDto.getClinic().getId(), Constants.ACTIVE).orElseThrow(()
                    -> new CustomException(ErrorConstants.DOCTOR_IS_NOT_PRESENT_IN_THIS_CLINIC));
            doctorClinic.getTimeslots().addAll(DoctorClinicMapper.fromDto(doctorClinicDto).getTimeslots());
            doctorClinicRepository.save(doctorClinic);
            return MessageConstants.DOCTOR_ASSIGNED_TO_CLINIC_SUCCESSFULLY;
        } catch (DataAccessException exception) {
            throw new DataBaseException(ErrorConstants.CANNOT_ACCESS_DATABASE);
        }
    }
}

