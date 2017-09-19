package com.aric.boot.serever;

import com.aric.common.utils.PrinterUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by tom.lee on 2016/3/16.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.aric"})
public class ServerApplication extends SpringBootServletInitializer {

    private static Logger logger = LoggerFactory.getLogger(ServerApplication.class);

    public static void main(String[] args) throws Exception {
        ApplicationContext application = SpringApplication.run(ServerApplication.class, args);
        if(logger.isDebugEnabled()) {
            String[] beanDefinitionNames = application.getBeanDefinitionNames();
            for (String beanName : beanDefinitionNames) {
                logger.debug(beanName);
            }
        }
    }
}




