package com.octaviocenteno.technicaltest.configuration.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.octaviocenteno.technicaltest.model.rest.LoginRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;

@Log4j2
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final String key;


    public AuthenticationFilter(AuthenticationManager authenticationManager, String key, String loginUrl) {
        this.authenticationManager = authenticationManager;
        this.key = key;
        super.setFilterProcessesUrl(loginUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Attempting authentication");

        try{
            LoginRequest loginRequest = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);

            UsernamePasswordAuthenticationToken authRequest =
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());

            return authenticationManager.authenticate(authRequest);

        } catch (IOException e) {
            log.error("Invalid request", e);
            throw  new BadCredentialsException("Bad request");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        log.debug("User " + authResult.getName() + " authenticated");

        Claims claims = Jwts.claims();
        claims.put("aut", authResult.getAuthorities().stream().findFirst().orElse(null).getAuthority());
        claims.setIssuedAt(new Date());
        claims.setExpiration(DateUtils.addMilliseconds(new Date(), 60 * 1000));
        claims.setSubject(((UserDetails) authResult.getPrincipal()).getUsername());
        response.addHeader(
                WebSecurityConfig.AUTHORIZATION_HEADER_NAME,
                "Bearer " +
                        Jwts.builder()
                                .setClaims(claims)
                                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encode(key.getBytes()))
                                .compact());

    }
}
