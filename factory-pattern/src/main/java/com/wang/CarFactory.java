package com.wang;

public class CarFactory
{

    public Car createCar(String type)
    {
        //如果将来传进来的是宝马，就创建一个宝马
        if ("BWM".equals(type))
        {
            Car car = new BMW();
            car.setColor("red");
            return car;
        } else if ("Benz".equals(type))
        {
            Car car = new Benz();
            car.setColor("blue");
            return car;
        }

        return null;
    }

}


