package com.example.demo.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/3/7.
 */

public class User implements Serializable{

    private Integer id;
    private String name;
    private Byte sex;

    private List<String> phoneList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public List<String> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(List<String> phoneList) {
        this.phoneList = phoneList;
    }
}
