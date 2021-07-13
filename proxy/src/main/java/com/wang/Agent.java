package com.wang;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Agent implements InvocationHandler
{
    private Object object;

    public Agent(Object object)
    {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        System.out.println("协商");
        Object invoke = method.invoke(object, args);
        System.out.println("结算");
        return invoke;
    }
}
