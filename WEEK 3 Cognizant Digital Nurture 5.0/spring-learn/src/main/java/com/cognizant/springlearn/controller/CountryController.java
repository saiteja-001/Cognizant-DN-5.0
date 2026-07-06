package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller providing Country endpoints.
 */
@RestController
public class CountryController {
    private static final Logger logger = LoggerFactory.getLogger(CountryController.class);

    /**
     * GET endpoint returning country details loaded from country.xml.
     * URL: /country
     */
    @RequestMapping(value = "/country", method = RequestMethod.GET)
    public Country getCountryIndia() {
        logger.info("[REST] CountryController.getCountryIndia() execution started.");
        
        // Load India bean from Spring XML configuration
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        Country country = context.getBean("country", Country.class);
        
        logger.info("[REST] CountryController.getCountryIndia() execution finished. Returning: {}", country);
        return country;
    }
}
