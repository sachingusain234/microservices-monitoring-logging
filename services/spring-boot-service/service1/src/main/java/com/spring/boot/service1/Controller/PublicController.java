package com.spring.boot.service1.Controller;

import com.spring.boot.service1.Service1Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class PublicController {
    private static final Logger logger = LoggerFactory.getLogger(Service1Application.class);
    @GetMapping("health-check")
    public String health(){
        logger.warn("Just Checking");
        return "It is service 1";
    }
}