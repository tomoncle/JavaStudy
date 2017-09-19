import com.aric.common.utils.PrinterUtils;
import com.aric.redis.spring.RedisStartConfig;
import com.aric.redis.spring.entity.User;
import com.aric.redis.spring.service.UserService;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by Administrator on 2016/11/24.
 */
public class Test {
    @org.junit.Test
    public void test(){
        String  user_prefix="user_";
        User user=new User(1,"aric");
        ApplicationContext atc=new AnnotationConfigApplicationContext(RedisStartConfig.class);
        RedisTemplate<String,User> obj = (RedisTemplate) atc.getBean("redisTemplate");
        PrinterUtils.printILog(obj.getKeySerializer());
        obj.opsForValue().set(user_prefix+user.getId(),user);
        RedisSentinelConfiguration RedisSentinelConfiguration = (RedisSentinelConfiguration) atc.getBean("sentinelConfig");
        PrinterUtils.printILog(RedisSentinelConfiguration);
        User value=obj.opsForValue().get(user_prefix+1);
        PrinterUtils.printELog(value.getUsername());
    }


    @org.junit.Test
    public void testCustomKeyGenerator(){
        ApplicationContext atc=new AnnotationConfigApplicationContext(RedisStartConfig.class);
        KeyGenerator obj = (KeyGenerator) atc.getBean("customKeyGenerator");
        PrinterUtils.printILog(obj);

    }
    @org.junit.Test
    public void testCache1(){
        ApplicationContext atc=new AnnotationConfigApplicationContext(RedisStartConfig.class);
        UserService obj = (UserService) atc.getBean("userService");
        PrinterUtils.printILog(obj.getUsers());
        PrinterUtils.printILog(obj.getUsers());
        PrinterUtils.printILog(obj.getUsers());


    }

    @org.junit.Test
    public void testCache2(){
        ApplicationContext atc=new AnnotationConfigApplicationContext(RedisStartConfig.class);
        UserService obj = (UserService) atc.getBean("userService");
        PrinterUtils.printILog(obj.getUserById(1));
        PrinterUtils.printILog(obj.getUserById(1));
        PrinterUtils.printILog(obj.getUserById(3));
        PrinterUtils.printILog(obj.getUserById(2));


    }

    @org.junit.Test
    public void testCache3(){
        ApplicationContext atc=new AnnotationConfigApplicationContext(RedisStartConfig.class);
        UserService obj = (UserService) atc.getBean("userService");
        obj.deleteUser(1);
        PrinterUtils.printILog(obj.getUserById(1));
        PrinterUtils.printILog(obj.getUserById(3));
        PrinterUtils.printILog(obj.getUserById(2));
        PrinterUtils.printILog(obj.getUsers());


    }
}
