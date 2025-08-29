package com.spring.boot.service.Contoller;

import com.spring.boot.service.ServiceApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class PublicController {
    private static final Logger logger = LoggerFactory.getLogger(ServiceApplication.class);
    @GetMapping("health-check")
    public String health(){
        logger.warn("Just Checking");
        return "It's working OK";
    }
}
