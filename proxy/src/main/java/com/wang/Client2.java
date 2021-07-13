package com.wang;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import java.lang.reflect.Method;

public class Client2
{
    public static void main(String[] args)
    {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "C:\\Users\\Administrator\\Desktop");
        //增强
        Enhancer enhancer = new Enhancer();
        //设置超类,代理哪个类参数就是哪个类
        enhancer.setSuperclass(MaleSinger2.class);
        //方法拦截器拦截父类方法并增强该方法形成新方法
        enhancer.setCallback(new MethodInterceptor()
        {
            @Override
            //                      拦截后的对象    拦截后的方法    参数          拦截前的方法
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable
            {
                return null;
            }
        });

        //创建代理后的对象                      生成代理对象
        MaleSinger2 maleSinger2 = (MaleSinger2) enhancer.create();
    }

}
