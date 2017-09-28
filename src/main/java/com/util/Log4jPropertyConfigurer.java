package com.util;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Properties;

public class Log4jPropertyConfigurer extends PropertyPlaceholderConfigurer {
    private Properties properties;

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactory,
                                     Properties props) throws BeansException {
        super.processProperties(beanFactory, props);
        this.properties = props;
        initLog4j();
    }

    public Properties getProperties() {
        return properties;
    }

    public  void initLog4j(){
        Log4jPropertyConfigurer log4jPropertyConfigurer = (Log4jPropertyConfigurer) SpringContext.getBean("log4jPropertyConfigurer");
        Properties log4jProperties = log4jPropertyConfigurer.getProperties();
        PropertyConfigurator.configure(log4jProperties);//设置Log4j配置
    }
}
