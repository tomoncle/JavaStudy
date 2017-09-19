package com.aric.annotations.resource.properties;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by liyuanjun on 16-11-18.
 */
@Configuration
@PropertySource({
        "classpath:properties/app.properties",
        "classpath:properties/system.properties"
        })
@ComponentScan("com.aric.annotations.resource.properties")
public class SystemProperties extends PropertySourcesPlaceholderConfigurer{

//    /**
//     *<bean class="org.springframework.context.support.PropertySourcesPlaceholderConfigurer">
//         <property name="ignoreUnresolvablePlaceholders" value="true"/>
//         <property name="locations">
//             <list>
//                   <value>classpath:app.properties</value>
//             </list>
//         </property>
//      </bean>
//     * @return PropertySourcesPlaceholderConfigurer
//     */
//    @Bean
//    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
//        return new PropertySourcesPlaceholderConfigurer();
//    }


}
