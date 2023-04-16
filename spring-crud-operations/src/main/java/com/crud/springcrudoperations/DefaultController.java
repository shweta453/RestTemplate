package com.crud.springcrudoperations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {
    @Autowired
    private Environment environment;

    @GetMapping("/msg")
    public String getWelcomeMessage(){

        return environment.getProperty("vehicle.name");
    }

}
