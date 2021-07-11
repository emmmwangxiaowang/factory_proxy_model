package com.wang;

public class Client
{
    public static void main(String[] args)
    {
        //创建对象的时候用接口创建--提高扩展性
        CarFactory carFactory = new CarFactory();
        Car        car        = carFactory.createCar("Benz");
        car.run();
    }
}
