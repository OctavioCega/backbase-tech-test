package com.octaviocenteno.technicaltest.configuration.security;

import com.octaviocenteno.technicaltest.configuration.AppConfig;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityInitializer
        extends AbstractSecurityWebApplicationInitializer {

    public SecurityInitializer() {
        super(AppConfig.class, WebSecurityConfig.class);
    }
}
