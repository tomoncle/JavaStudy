package base;

import com.aric.annotations.started.config.SpringConfiguration;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by tom.lee on 2016/3/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class BaseTest {
    /**
     * 1.引用spring junit test maven库
     * @RunWith(SpringJUnit4ClassRunner.class)标注为spring测试框架
     * @ContextConfiguration(classes = SpringConfiguration.class)加载启动类
     * @ContextConfiguration("/beans.xml")加载配置文件
     * @
     *
     */

}
