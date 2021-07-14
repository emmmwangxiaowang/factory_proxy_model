package com.wang;

import com.wang.core.Session;
import com.wang.core.SessionFactory;
import com.wang.dao.UserDao;
import com.wang.entity.User;
import java.util.List;

public class Test
{
    public static void main(String[] args)
    {
        SessionFactory sessionFactory = new SessionFactory("mybatis-config.xml");
        Session        session        = sessionFactory.openSession();
        UserDao        userDao        = session.getMapper(UserDao.class);
        List <User>    user           = userDao.finduser(10);
        System.out.println(user);
    }
}

