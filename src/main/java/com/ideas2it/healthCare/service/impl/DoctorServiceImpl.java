package com.ideas2it.healthCare.service.impl;

import com.ideas2it.healthCare.common.Constants;
import com.ideas2it.healthCare.dto.DoctorDto;
import com.ideas2it.healthCare.mapper.DoctorMapper;
import com.ideas2it.healthCare.model.Doctor;
import com.ideas2it.healthCare.repo.DoctorRepository;
import com.ideas2it.healthCare.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;


    /**
     * <p>
     * This method is used to create and update Doctor's record by
     * getting DoctorDto as an input and convert it in to
     * Doctor model with the help of mapper class
     * </p>
     *
     * @param doctorDto {@link DoctorDto}
     *
     */
    @Override
    public DoctorDto saveOrUpdate(DoctorDto doctorDto) {
        doctorRepository.save(DoctorMapper.fromDto(doctorDto));
        return doctorDto;
    }

    /**
     * <p>
     * This method is used to get all Doctor's record by
     * getting all Doctors from database and convert it in to
     * DoctorDto with the help of mapper class
     * </p>
     *
     * @return {@link List<DoctorDto>}
     */
    @Override
    public List<DoctorDto> getAllDoctors() {
        return (List<DoctorDto>) DoctorMapper.toDto((Doctor) doctorRepository.findAllAndStatus(Constants.ACTIVE));
    }

    /**
     * <p>
     * This method is used to get the Doctor by id and
     * convert it into DoctorDto
     * </p>
     *
     * @param id {@link int}
     *
     * @return {@link DoctorDto} the employee object
     */
    @Override
    public DoctorDto getDoctorById(int id) {
        return DoctorMapper.toDto(doctorRepository.findByIdAndStatus(id,Constants.ACTIVE));
    }

    /**
     * <p>
     * This method is used to delete the Doctor by id
     * </p>
     *
     * @param id {@link int}
     *
     */
    @Override
    public String deleteById(int id) {
        Doctor doctor = doctorRepository.findByIdAndStatus(id, Constants.ACTIVE);
        doctor.setStatus(Constants.INACTIVE);
        doctorRepository.save(doctor);
        return "Deleted Successfully";
    }
}
