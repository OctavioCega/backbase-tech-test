package com.octaviocenteno.technicaltest.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class InitializerConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected String[] getServletMappings() {
        return new String[]{ "/"};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;

    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;

    }
}
