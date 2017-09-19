import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by tom.lee on 2016/3/16.
 * 程序入口类
 */
//@Import({ExtendedConfiguration.class})引入配置文件
@Configuration
@EnableAutoConfiguration(exclude = {JacksonAutoConfiguration.class})
@ComponentScan(basePackages = {"com.aric"})
public class ApplicationMain extends SpringBootServletInitializer {
    private static Logger logger = LoggerFactory.getLogger(ApplicationMain.class);

    public static void main(String[] args) throws Exception {
        ApplicationContext application = SpringApplication.run(ApplicationMain.class, args);
        if (logger.isDebugEnabled()) {
            String[] beanDefinitionNames = application.getBeanDefinitionNames();
            for (String beanName : beanDefinitionNames) {
                logger.debug(beanName);
            }
        }
    }

}
