package com.wang.core;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class Session
{
    // 每个会话持有一个链接
    private Connection connection;

    private Map <String, Map <String, DaoWrapper>> env;

    public Session(Connection connection, Map <String, Map <String, DaoWrapper>> env)
    {
        this.env = env;
        this.connection = connection;
    }

    //开始会话
    public void begin()
    {
        try
        {
            connection.setAutoCommit(false);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    //提交
    public void commit()
    {
        try
        {
            connection.commit();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    //回滚
    public void rollback()
    {
        try
        {
            connection.rollback();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public Connection getConnection()
    {
        return connection;
    }

    //userDao
    public <T> T getMapper(Class clazz)
    {
        T t = (T) Proxy.newProxyInstance(Session.class.getClassLoader(), new Class[]{clazz}, new SqlInvocationHandler(connection, env.get(clazz.getName())));

        return t;
    }
}
