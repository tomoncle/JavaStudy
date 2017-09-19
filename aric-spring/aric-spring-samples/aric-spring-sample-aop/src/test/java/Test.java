import com.aric.aop.annotations.Student;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2016/7/24.
 */
@Configuration
@ComponentScan(value = "com.aric.aop.annotations")
public class Test {

    public static void main(String[] args) throws Exception {
        //方法入参，需要启动的扫描类
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Test.class);
        //通过ApplicationContext获取Bean
         Student student=(Student)context.getBean("student");
         student.setName("alo");
         student.sayHello();
    }
}
