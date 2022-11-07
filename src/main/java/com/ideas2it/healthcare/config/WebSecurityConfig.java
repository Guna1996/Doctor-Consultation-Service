/**
 * <p>
 * This is the base package for all the configuration classes
 * which is for Swagger and Web security
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.config;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.filters.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * <p>
 * This WebSecurity Config class is a configuration class and this
 * class is used to manage access the urls
 * </p>
 *
 * @author Gunaseelan K
 * @version 1
 * @since 2022-10-10
 */
@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService myUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    /**
     * <p>
     * This method is used to create bean for authentication using
     * Authentication Manager
     * </p>
     *
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * <p>
     * This method is used to create bean for password encoding using
     * password Encoder
     * </p>
     *
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * <p>
     * This method is used to restrict and allow access for urls in controller
     * based on authentication
     * </p>
     *
     * @param httpSecurity {@link HttpSecurity} allows configuring web based security
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers(Constants.URL_LOGIN).permitAll()
                .antMatchers("/patient/**").permitAll()
                .antMatchers("/appointment/**").permitAll()
                .antMatchers("/feedback/**").permitAll()
                .antMatchers("/patientVital/patient/**").permitAll()
                .antMatchers("/swagger-ui.html/**").permitAll()
                .antMatchers("/clinic/**", "/doctor/**", "/doctor-clinic/**",
                        "/timeslot/**", "/specialization/**").hasAuthority("Admin")
                .anyRequest().authenticated().and()
                .exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

//    public void configure(WebSecurity webSecurity) {
//        webSecurity.ignoring().antMatchers("/clinic/**", "/doctor/**", "/doctor-clinic/**", "/appointment/**", "/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/security"
//                , "/swagger-ui.html", "/webjars/**");
//    }
}
