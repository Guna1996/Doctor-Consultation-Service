/**
 * <p>
 * This is the base package for all the controller classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.controller;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.dto.AppointmentDto;
import com.ideas2it.healthcare.dto.DoctorDto;
import com.ideas2it.healthcare.dto.FeedbackDto;
import com.ideas2it.healthcare.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * This DoctorController class is used to get and store information from a
 * doctor, update and delete doctors and get appointment, feedback of a
 * doctor
 * </p>
 *
 * @author Mohamed Jubair
 * @version 1
 * @since 2022-10-10
 */

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    /**
     * <p>
     * This addDoctor method is used to get details of doctor
     * after validating it.
     * </p>
     *
     * @param doctorDto is details of doctor
     * @return DoctorDto
     */
    @PostMapping
    public DoctorDto addDoctor(@Valid @RequestBody DoctorDto doctorDto) {
        return doctorService.saveOrUpdateDoctor(doctorDto);
    }


    /**
     * <p>
     * This getAllDoctors method is used to get All the
     * details of doctors.
     * </p>
     *
     * @param pageNumber is page number to show
     * @param totalRows  is a set of rows to be shown
     * @return List<DoctorDto>
     */
    @GetMapping(Constants.PAGE_PATH)
    public List<DoctorDto> getAllDoctors(@PathVariable(Constants.PAGE_NUMBER) int pageNumber,
                                         @PathVariable(Constants.TOTAL_ROWS) int totalRows) {
        return doctorService.getAllDoctors(pageNumber, totalRows);
    }

    /**
     * <p>
     * This getDoctorById method is used to get details
     * of a particular doctor.
     * </p>
     *
     * @param id is id of doctor
     * @return DoctorDto
     */
    @GetMapping(Constants.ID)
    public DoctorDto getDoctorById(@PathVariable int id) {
        return doctorService.getDoctorById(id);
    }

    /**
     * <p>
     * This updateDoctor method is used to update the details of
     * doctor after validating it.
     * </p>
     *
     * @param doctorDto is details of doctor
     * @return DoctorDto
     */
    @PutMapping
    public DoctorDto updateDoctor(@Valid @RequestBody DoctorDto doctorDto) {
        return doctorService.saveOrUpdateDoctor(doctorDto);
    }

    /**
     * <p>
     * This deleteDoctorById method is used
     * to remove a doctor.
     * </p>
     *
     * @param id is id of doctor
     * @return String
     */
    @PutMapping(Constants.ID)
    public String deleteDoctorById(@PathVariable int id) {
        return doctorService.deleteDoctorById(id);
    }

    /**
     * <p>
     * This method is used to get appointments
     * of a doctor.
     * </p>
     *
     * @param doctorId
     * @param pageNumber
     * @param totalRows
     * @return List<AppointmentDto>
     */
    @GetMapping("/appointment/{doctorId}/{pageNumber}/{totalRows}")
    public List<AppointmentDto> getAppointmentsByDoctorId(@PathVariable(name = "doctorId") int doctorId,
                                                           @PathVariable(name = "pageNumber") int pageNumber,
                                                           @PathVariable(name = "totalRows") int totalRows) {
        return doctorService.getAppointmentsByDoctorId(doctorId, pageNumber, totalRows);
    }

    /**
     * <p>
     * This method is used to get feedbacks
     * of a doctor.
     * </p>
     *
     * @param doctorId
     * @param pageNumber
     * @param totalRows
     * @return List<FeedbackDto>
     */
    @GetMapping("/feedbacks/{doctorId}/{pageNumber}/{totalRows}")
    public List<FeedbackDto> getFeedbacksByDoctorId(@PathVariable(name = "doctorId") int doctorId,
                                                    @PathVariable(name = "pageNumber") int pageNumber,
                                                    @PathVariable(name = "totalRows") int totalRows){
        return doctorService.getFeedbacks(doctorId, pageNumber, totalRows);
    }
}