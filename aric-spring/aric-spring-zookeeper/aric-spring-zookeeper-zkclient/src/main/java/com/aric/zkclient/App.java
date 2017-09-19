package com.aric.zkclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by aric on 2016/5/9.
 */

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.aric"})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }

}
