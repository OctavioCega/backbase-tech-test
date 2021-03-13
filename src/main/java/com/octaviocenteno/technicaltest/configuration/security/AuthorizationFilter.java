package com.octaviocenteno.technicaltest.configuration.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Component
@Log4j2
public class AuthorizationFilter extends OncePerRequestFilter {

    @Value("${jwt.key}")
    private String key;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.debug("Entered JWTAuthorization filter");

        String jwt = request.getHeader(WebSecurityConfig.AUTHORIZATION_HEADER_NAME);

        try{

            Claims claims = (Claims) Jwts.parser().setSigningKey(Base64.getEncoder().encode(key.getBytes())).parse(jwt!=null?jwt.substring(7):"").getBody();

            log.debug("Token valid and authorized");

            Authentication authenticationResult =
                    new UsernamePasswordAuthenticationToken(claims.getSubject(),null, Arrays.asList(new SimpleGrantedAuthority((String)claims.get("aut"))));

            // Spring recommends to create a new SecurityContext instance to avoid
            // race conditions across multiple threads.
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authenticationResult);
            // Setting authorization in context so Spring Security will know that this request can proceed to servlet
            // after filter chain
            SecurityContextHolder.setContext(context);

            //Allowing filterchain to continue
            filterChain.doFilter(request,response);

        } catch(JwtException e){
            log.debug("Authorization token failed", e);
            //Setting unauthorized response
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        //Avoid filtering login and requests without tooken
        return request.getHeader(WebSecurityConfig.AUTHORIZATION_HEADER_NAME) == null;
    }
}
