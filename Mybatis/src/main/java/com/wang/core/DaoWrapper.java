package com.wang.core;

import java.io.Serializable;

//保存sql语句各种参数
public class DaoWrapper implements Serializable
{
    // 类型，insert | update | delete
    private String type;
    /*
    返回值的类型*/
    private String resultType;
    /*
    参数的类型*/
    private String paramType;
    /*sq1语句" */
    private String sql;

    public DaoWrapper(String type, String resultType, String paramType, String sql)
    {
        this.type = type;
        this.resultType = resultType;
        this.paramType = paramType;
        this.sql = sql;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getResultType()
    {
        return resultType;
    }

    public void setResultType(String resultType)
    {
        this.resultType = resultType;
    }

    public String getParamType()
    {
        return paramType;
    }

    public void setParamType(String paramType)
    {
        this.paramType = paramType;
    }

    public String getSql()
    {
        return sql;
    }

    public void setSql(String sql)
    {
        this.sql = sql;
    }

    public String getType()
    {
        return type;
    }

    @Override
    public String toString()
    {
        return "DaoWrapper{" +
                "type='" + type + '\'' +
                ", resultType='" + resultType + '\'' +
                ", paramType='" + paramType + '\'' +
                ", sql='" + sql + '\'' +
                '}';
    }
}


