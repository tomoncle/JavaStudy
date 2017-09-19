package com.aric.zkapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2016/5/7.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.aric"})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }

}
