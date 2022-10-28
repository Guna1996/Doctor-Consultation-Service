/**
 * <p>
 * This is the base package for all the util classes
 * which is for jwt util
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * <p>
 * JwtUtil class is used for generate and validate token for authentication and authorization
 * </p>
 *
 * @author Gunaseelan K
 * @version 1
 * @since 2022-10-10
 */
@Service
public class JwtUtil {

    private final String SECRET_KEY = "secret";

    /**
     * <p>
     * This method is used to extract username
     * </p>
     *
     * @parm token is token for the session
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * <p>
     * This method is used to extract expiration
     * </p>
     *
     * @parm token is token for the session
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * <p>
     * This method is used to extract claim
     * </p>
     *
     * @parm token is token for the session
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * <p>
     * This method is used to extract all claims
     * </p>
     *
     * @parm token is token for the session
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    /**
     * <p>
     * This method is used to check wheather token is expired
     * </p>
     *
     * @parm token is token for the session
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    /**
     * <p>
     * This method is used to create token
     * </p>
     *
     * @parm claims
     * @parm subject
     */
    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    /**
     * <p>
     * This method is used to validate token
     * </p>
     *
     * @parm token is token for the session
     * @parm userDetails contains username and password of the user
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}