package com.wang.dao;

import com.wang.entity.User;
import java.util.List;

public interface StudentDao
{

    //保存用户
    Integer saveStudent(User user);

    //根据id查找用户
    List <User> findStudent(Integer id);
}
