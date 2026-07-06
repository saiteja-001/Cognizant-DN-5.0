package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class SpringLearnApplication {
    private static final Logger logger = LoggerFactory.getLogger(SpringLearnApplication.class);

    public static void main(String[] args) {
        logger.info("==================================================");
        logger.info("   Starting SpringLearnApplication main() method  ");
        logger.info("==================================================");

        SpringApplication.run(SpringLearnApplication.class, args);

        // Load country from XML configuration
        displayCountry();

        logger.info("==================================================");
        logger.info("   SpringLearnApplication is running successfully ");
        logger.info("==================================================");
    }

    public static void displayCountry() {
        logger.info("[Test] Loading country from country.xml...");
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        Country country = context.getBean("country", Country.class);
        logger.debug("Country : {}", country.toString());
    }
}
