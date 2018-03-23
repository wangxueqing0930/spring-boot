package com.example.demo.dao;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2018/3/7.
 */
@Mapper
public interface UserMapper {

    List<User> getAllUser();

    User getById(Integer id);

    int batchInsert(@Param("list") List<User> list);
}
