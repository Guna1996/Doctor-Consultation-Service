/**
 * <p>
 * This is the base package for all the util classes
 * which is for simple calculations
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
     * @param token {@link String} is token for the session
     * @return {@link String}
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * <p>
     * This method is used to extract expiration
     * </p>
     *
     * @param token {@link String} is token for the session
     * @return {@link Date}
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * <p>
     * This method is used to extract claim
     * </p>
     *
     * @param token {@link String} is token for the session
     * @param claimsResolver {@link Function}
     * @return {@link T}
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
     * @param token {@link String} is token for the session
     * @return {@link Claims}
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    /**
     * <p>
     * This method is used to check whether token is expired
     * </p>
     *
     * @param token {@link String} is token for the session
     * @return {@link Boolean}
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * <p>
     *  This method to generate jwt token using
     *  user details header, payload and signature.
     * </p>
     * @param userDetails {@link UserDetails} it contains username and password
     * @return {@link String}
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername(), userDetails);
    }

    /**
     * <p>
     * This method is used to create token using
     * claims of payload.
     * </p>
     *
     * @parm claims {@link Map}
     * @parm subject {@link String}
     * @return {@link String}
     */
    private String createToken(Map<String, Object> claims, String subject, UserDetails userDetails) {
        claims.put("roles", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    /**
     * <p>
     * This method is used to validate token
     * </p>
     *
     * @param token {@link String} is token for the session
     * @param userDetails {@link UserDetails} contains username and password of the user
     * @return {@link Boolean}
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}