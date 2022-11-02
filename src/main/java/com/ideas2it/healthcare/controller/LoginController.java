/**
 * <p>
 * This is the base package for all the controller classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.controller;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.common.ErrorConstants;
import com.ideas2it.healthcare.dto.AuthenticationRequest;
import com.ideas2it.healthcare.dto.AuthenticationResponse;
import com.ideas2it.healthcare.exception.NotFoundException;
import com.ideas2it.healthcare.service.impl.AuthenticationService;
import com.ideas2it.healthcare.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * This Login Controller class is used to Authenticate
 * the user and allow to access other operations.
 * </p>
 *
 * @author Gunaseelan K
 * @version 1
 * @since 2022-10-10
 */
@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private AuthenticationService authenticationService;

    /**
     * <p>
     * This method is used to perform authentication by generating token while the user login
     * by username and password. This token can be further used for authorization
     * </p>
     *
     * @parm authenticationRequest {@link AuthenticationRequest} contains username and password
     */
    @RequestMapping(value = Constants.URL_LOGIN, method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody AuthenticationRequest authenticationRequest)
            throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                            authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException badCredentialsException) {
            throw new NotFoundException(ErrorConstants.INCORRECT_USERNAME_OR_PASSWORD);
        }
        final UserDetails userDetails = authenticationService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
