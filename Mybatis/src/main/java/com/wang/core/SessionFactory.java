package com.wang.core;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.*;

public class SessionFactory
{
    private DataSource dataSource;

    private Map <String, Map <String, DaoWrapper>> env = new HashMap <>(8);

    public SessionFactory(String configPath)
    {
//        dataSource = DataSourceFactory.createDataSource(type);

        //解析配置文件
        parseConfigXml(configPath);
    }

    private void parseConfigXml(String configPath)
    {
        try
        {
            //解析数据源
            InputStream resource          = SessionFactory.class.getClassLoader().getResourceAsStream(configPath);
            SAXReader   reader            = new SAXReader();
            Document    read              = reader.read(resource);
            Element     ConfigRoot        = read.getRootElement();
            Element     dataSourceElement = ConfigRoot.element("datasource");
            dataSource = DataSourceFactory.createDataSource(dataSourceElement.getTextTrim());

            //获取所有的mapper文件
            List          mapperElements = ConfigRoot.elements("mapper");
            List <String> mapperPaths    = new ArrayList <>();
            for (Object mapperElement : mapperElements)
            {
                Element element = (Element) mapperElement;
                mapperPaths.add(element.getTextTrim());
            }
            for (String mapperPath : mapperPaths)
            {
                Map <String, DaoWrapper> wrapper   = new HashMap <>(8);
                Document                 document  = reader.read(Session.class.getClassLoader().getResourceAsStream(mapperPath));
                Element                  root      = document.getRootElement();
                String                   namespace = root.attribute("namespace").getValue();
                Iterator                 iterator  = root.elementIterator();
                while (iterator.hasNext())
                {
                    Element el         = (Element) iterator.next();
                    String  type       = el.getName();
                    String  id         = el.attribute("id").getValue();
                    String  resultType = el.attribute("resultType").getValue();
                    String  paramType  = el.attribute("paramType").getValue();
                    String  sqlstr     = el.getTextTrim();
                    wrapper.put(id, new DaoWrapper(type, resultType, paramType, sqlstr));
                }
                env.put(namespace, wrapper);

            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public Session openSession()
    {

        try
        {
            Session session = new Session(dataSource.getConnection(), env);
            return session;
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
