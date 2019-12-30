package com.brasajava.elk.com.brasajava.elk.api.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class PersonController {

    private static final Logger LOG = LoggerFactory.getLogger(PersonController.class);

    @GetMapping("/hello")
    public String hello(@RequestParam(required = false) String name){
        String msg = StringUtils.isEmpty(name)? "Hello world!!" : "Hello " + name + "!";
        LOG.info(msg);
        return msg;
    }

    @GetMapping("/error")
    public String error(){

        try {
            throw new RuntimeException("Error some errors occurs");
        } catch (Exception e){
            e.printStackTrace();
            LOG.error("Exception", e);
        }
        return "Error logged";
    }
}
