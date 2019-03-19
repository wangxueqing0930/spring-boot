package com.example.demo.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.demo.dao.UserMapper;
import com.example.demo.model.User;
import com.example.demo01.api.UserDubboService;
import com.example.demo01.model.UserEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/7.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Reference
    private UserDubboService userDubboService;

    @Resource
    private RedisTemplate<String,Object> redissTemplate;

    @Override
    @Cacheable(value="thisredis",key="'user_'+#id")
    public User getUserById(Integer id) {
        UserEntity userEntity = userDubboService.getUserById(id);
        User user = new User();
        if(null != userEntity){
            BeanUtils.copyProperties(userEntity,user);
        }else{
            user = userMapper.getById(id);
        }
        redissTemplate.opsForValue().set("user_"+id,user);
        return user;
    }

    @Override
    @Transactional
    public User testTransaction() {
        User user = new User();
        user.setName("呼呼");
        user.setSex((byte)1);
        List<User> list = new ArrayList<>();
        list.add(user);
        userMapper.batchInsert(list);

        User user1 = userMapper.getById(100);
        String name = user1.getName();

        return user;
    }


}
