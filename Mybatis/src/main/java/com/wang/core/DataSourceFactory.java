package com.wang.core;

import com.alibaba.druid.pool.DruidDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

//数据源工厂
public class DataSourceFactory
{

    public static void main(String[] args)
    {
        DataSource druid = DataSourceFactory.createDataSource("druid");
        System.out.println(druid);
    }

    public static final String DATASOURCE_TYPE_HIKARI = "hikari";
    public static final String DATASOURCE_TYPE_DRUID  = "druid";

    public static DataSource createDataSource(String type)
    {
        DataSource dataSource = null;
        Properties properties = new Properties();
        if (DATASOURCE_TYPE_HIKARI.equals(type))
        {
            try
            {
                properties.load(DataSourceFactory.class.getClassLoader().getResourceAsStream("hikari.properties"));
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            HikariConfig hikariConfig = new HikariConfig(properties);
            dataSource = new HikariDataSource(hikariConfig);
        } else if (DATASOURCE_TYPE_DRUID.equals(type))
        {
            try
            {
                properties.load(DataSourceFactory.class.getClassLoader().getResourceAsStream("druid.properties"));
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            DruidDataSource druidDataSource = new DruidDataSource();
            druidDataSource.configFromPropety(properties);
            dataSource = druidDataSource;
        }

        return dataSource;

    }
}
