package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/9/29.
 */
@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public User test(@RequestParam(value = "id") Integer id){
        User user = userService.getUserById(id);
        logger.info(user.getName());
        return user;
    }

    @RequestMapping(value = "/test1",method = RequestMethod.GET)
    public User testTransaction(){
        User user = userService.testTransaction();
        return user;
    }
}
