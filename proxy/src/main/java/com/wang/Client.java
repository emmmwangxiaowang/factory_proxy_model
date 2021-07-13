package com.wang;

import java.lang.reflect.Proxy;

public class Client
{
    public static void main(String[] args)
    {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        Singer maleSinger = new MaleSinger();

        //新建一个代理实例-----基于JDK的动态代理
        Singer singer = (Singer) Proxy.newProxyInstance(
                Client.class.getClassLoader(),
                new Class[]{Singer.class},
                new Agent(maleSinger));

        singer.say();
    }
}
