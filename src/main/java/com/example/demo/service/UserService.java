package com.example.demo.service;

import com.example.demo.model.User;

/**
 * Created by Administrator on 2018/3/7.
 */
public interface UserService {
    User getUserById(Integer id);

    User testTransaction();
}

