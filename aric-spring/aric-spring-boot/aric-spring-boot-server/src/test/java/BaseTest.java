import com.aric.boot.serever.ServerApplication;
import com.aric.boot.serever.entity.User;
import com.aric.boot.serever.mapper.UserMapper;
import com.aric.boot.serever.service.UserService;
import com.aric.common.utils.BaseEnum;
import com.aric.common.utils.PrinterUtils;
import com.aric.common.utils.UUIDGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by tom.lee on 2016/3/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ServerApplication.class)
public class BaseTest {

    @Autowired
    UserService userService;

    @Test
    public void getUserList(){
        List<User> userList=userService.getUserList();
        PrinterUtils.printILog(userList);
    }


    @Test
    public void addUser(){
        User user=new User();
        user.setUsername("sande");
        user.setPassword("123456");
        userService.addUser(user);


    }

}
