package com.octaviocenteno.technicaltest.configuration.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String AUTHORIZATION_HEADER_NAME = "Authorization";
    private final AuthorizationFilter authorizationFilter;


    @Value("${jwt.key}")
    private String key;

    private static final String[] SWAGGER_ENDPOINTS = {
            "/**/v2/api-docs",
            "/**/swagger-resources/configuration/ui",
            "/**/swagger-resources",
            "/**/swagger-resources/configuration/security",
            "/**/swagger-ui.html",
            "/**/webjars/**",
            "/**/swagger-ui/**"
    };

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}root")
                .roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().disable()
                .httpBasic().disable()
                .cors().and()
                .authorizeRequests()
                // Allow to access swagger to everyone.
                .antMatchers(SWAGGER_ENDPOINTS).permitAll()
                .anyRequest().hasRole("ADMIN")
                .and()
                .logout().disable()
                .formLogin().disable()
                .addFilterBefore(authorizationFilter, AuthenticationFilter.class)
                .addFilter(new AuthenticationFilter(authenticationManager(), key, "/login"));
    }
}
