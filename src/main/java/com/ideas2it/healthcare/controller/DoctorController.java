/**
 * <p>
 * This is the base package for all the controller classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.controller;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.dto.DoctorDto;
import com.ideas2it.healthcare.response.Response;
import com.ideas2it.healthcare.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

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
    public ResponseEntity<Map<String,Object>> addDoctor(@Valid @RequestBody DoctorDto doctorDto) {
        return Response.responseEntity("Success",
                doctorService.saveDoctor(doctorDto),
                HttpStatus.OK);
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
    public ResponseEntity<Map<String, Object>> getAllDoctors(@PathVariable(Constants.PAGE_NUMBER) int pageNumber,
                                                @PathVariable(Constants.TOTAL_ROWS) int totalRows) {
        return Response.responseEntity("Success",
                doctorService.getAllDoctors(pageNumber, totalRows),
                HttpStatus.OK);
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
    @GetMapping(Constants.PATH_ID)
    public ResponseEntity<Map<String, Object>> getDoctorById(@PathVariable int id) {
        return Response.responseEntity("Success",
                doctorService.getDoctorById(id),
                HttpStatus.OK);
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
    public ResponseEntity<Map<String,Object>> updateDoctor(@Valid @RequestBody DoctorDto doctorDto) {
        return Response.responseEntity("Success",
                doctorService.updateDoctor(doctorDto),
                HttpStatus.OK);
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
    @PutMapping(Constants.PATH_ID)
    public ResponseEntity<String> deleteDoctorById(@PathVariable int id) {
        return new ResponseEntity<>(doctorService.deleteDoctorById(id), HttpStatus.OK);
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
    @GetMapping(Constants.PATH_APPOINTMENT_ID)
    public ResponseEntity<Map<String, Object>> getAppointmentsByDoctorId(@PathVariable(name = Constants.PATH_DOCTOR_ID) int doctorId,
                                                          @PathVariable(name = Constants.PAGE_NUMBER) int pageNumber,
                                                          @PathVariable(name = Constants.TOTAL_ROWS) int totalRows) {
        return Response.responseEntity("Success",
                doctorService.getAppointmentsByDoctorId(doctorId, pageNumber, totalRows),
                HttpStatus.OK);
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
    @GetMapping(Constants.PATH_FEEDBACK_ID)
    public ResponseEntity<Map<String, Object>> getFeedbacksByDoctorId(@PathVariable(name = Constants.PATH_DOCTOR_ID) int doctorId,
                                                    @PathVariable(name = Constants.PAGE_NUMBER) int pageNumber,
                                                    @PathVariable(name = Constants.TOTAL_ROWS) int totalRows){
        return Response.responseEntity("Success",
                doctorService.getFeedbacks(doctorId, pageNumber, totalRows),
                HttpStatus.OK);
    }
}