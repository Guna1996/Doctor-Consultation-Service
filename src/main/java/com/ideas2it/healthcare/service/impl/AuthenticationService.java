/**
 * <p>
 * This package contains classes are DoctorClinicImpl,
 * PatientImpl, DoctorImpl, ClinicImpl,
 * AppointmentImpl, FeedbackImpl, SpecializationImpl,
 * TimeslotImpl, VitalsImpl
 * </p>
 * <p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * <p>
 * AuthenticationService class implements UserDetailsService
 * and it contains method which is used to authenticate user by loading his details
 * </p>
 *
 * @author Gunaseelan K
 * @version 1
 * @since 2022-10-10
 */
@Service
public class AuthenticationService implements UserDetailsService {

    /**
     * <p>
     * This method is used to load default username and password for authentication
     * and authorization
     * </p>
     *
     */
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return new User("admin", "ideas2it",
                new ArrayList<>());
    }
}