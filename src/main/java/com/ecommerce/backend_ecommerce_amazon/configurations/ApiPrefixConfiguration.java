package com.ecommerce.backend_ecommerce_amazon.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

//Creating a configuration to add the prefix for all the APIs

@Configuration
public class ApiPrefixConfiguration implements WebMvcConfigurer{
//implements the interface WebMvcConfugurer so we can  define the method configurePatchMatch
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer)
    {
        configurer.addPathPrefix("ecommerce",c->c.isAnnotationPresent(RestController.class));
        //we are adding ecommerce as the prefix for all the APIs and made sure the annotation is present in our RestController class
    }
}
