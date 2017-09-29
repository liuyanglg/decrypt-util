package com.util;

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
//        initLog4j();
    }

    public Properties getProperties() {
        return properties;
    }

}
