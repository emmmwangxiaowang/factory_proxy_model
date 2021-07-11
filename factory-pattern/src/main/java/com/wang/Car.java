package com.wang;

/*
简单工厂设计模式：
    将创建对象过程封装
*/

public abstract class Car
{

    private String color;

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    /*
        汽车启动的方法
         */
    void run()
    {

    }
}
