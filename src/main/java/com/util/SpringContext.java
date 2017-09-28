package com.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContext {
    private static ApplicationContext context;

    public static void init(){
        buildContext();
    }

    public static ApplicationContext buildContext() {
        if(null==context){
            context = new ClassPathXmlApplicationContext("classpath*:spring.xml");
        }
        return context;
    }

    public static Object getBean(String beanName){
        return buildContext().getBean(beanName);
    }
}
