package com.cognizant.springlearn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller providing Hello World endpoints.
 */
@RestController
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    /**
     * GET endpoint returning a greeting string.
     * URL: /hello
     */
    @GetMapping("/hello")
    public String sayHello() {
        logger.info("[REST] HelloController.sayHello() execution started.");
        
        String response = "Hello World!!";
        
        logger.info("[REST] HelloController.sayHello() execution finished. Returning: {}", response);
        return response;
    }
}
