package com.wang;

public class MaleSinger implements Singer
{
    @Override
    public void sing()
    {
        System.out.println("男歌手唱歌");
    }

    @Override
    public void say()
    {
        System.out.println("说话 ");
    }
}
