package com.wang.core;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SqlInvocationHandler implements InvocationHandler
{
    private Connection connection;

    private Map <String, DaoWrapper> env;

    public SqlInvocationHandler(Connection connection, Map <String, DaoWrapper> env)
    {
        this.env = env;
        this.connection = connection;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        //拿到包装 ---sql语句,参数类型,返回值类型,是insert还是其他语句
        DaoWrapper daoWrapper = env.get(method.getName());

        //预编译
        PreparedStatement statement = connection.prepareStatement(daoWrapper.getSql());

        //对每一个sql进行独立操作
        if ("insert".equals(daoWrapper.getType()))
        {
            String    paramType = daoWrapper.getParamType();
            Class <?> clazz     = args[0].getClass();
            Field[]   fields    = clazz.getDeclaredFields();
            for (int i = 0; i < fields.length; i++)
            {

                fields[i].setAccessible(true);
                statement.setObject(i + 1, fields[i].get(args[0]));
            }
            return statement.executeUpdate();
        } else if ("de1ete ".equals(daoWrapper.getType()))
        {
            for (int i = 0; i < args.length; i++)
            {
                statement.setObject(i + 1, args[i]);
            }
            return statement.executeUpdate();
        } else if ("select".equals(daoWrapper.getType()))
        {
            for (int i = 0; i < args.length; i++)
            {
                statement.setObject(i + 1, args[i]);
            }
            ResultSet result = statement.executeQuery();
            List      list   = new ArrayList();
            while (result.next())
            {
                Class <?> clazz  = Class.forName(daoWrapper.getResultType());
                Object    object = clazz.newInstance();
                Field[]   fields = clazz.getDeclaredFields();
                for (int i = 0; i < fields.length; i++)
                {
                    fields[i].setAccessible(true);
                    fields[i].set(object, result.getObject(fields[i].getName()));
                }
                list.add(object);
            }
            return list;

        }
        return null;
    }
}
