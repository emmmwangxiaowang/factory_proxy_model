package com.wang.dao;

import com.wang.entity.User;
import java.util.List;

public interface UserDao
{

    //保存用户
    Integer saveUser(User user);

    //根据id查找用户
    List <User> finduser(Integer id);

}
