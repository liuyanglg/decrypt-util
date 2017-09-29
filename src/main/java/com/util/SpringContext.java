package com.util;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;

import java.util.Properties;

public class SpringContext {
    private static ApplicationContext context;

    public static void init(){
        if(null==context){
            context = new LoadXmlApplicationContext("classpath*:/spring/spring.xml");
        }
        initLog4j();
    }

    public static ApplicationContext getContext() {
        if(null==context){
            context = new LoadXmlApplicationContext("classpath*:/spring/spring.xml");
        }
        return context;
    }

    public static Object getBean(String beanName){
        return getContext().getBean(beanName);
    }

    public static void initLog4j(){
        Log4jPropertyConfigurer log4jPropertyConfigurer = (Log4jPropertyConfigurer) SpringContext.getBean("log4jPropertyConfigurer");
        Properties log4jProperties = log4jPropertyConfigurer.getProperties();
        PropertyConfigurator.configure(log4jProperties);//设置Log4j配置
    }
}
