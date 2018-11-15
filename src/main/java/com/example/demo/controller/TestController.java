package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



/**
 * Created by Administrator on 2018/3/6.
 */
@RestController
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping(value = "sayHello", method = RequestMethod.GET)
    public String sayhello() {
        logger.info("测试spring boot");
        return "Hello SpringBoot2 !";
    }


    public String test(){
        return "test";
    }
}
