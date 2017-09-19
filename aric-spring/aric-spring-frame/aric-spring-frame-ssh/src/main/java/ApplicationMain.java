import com.aric.common.utils.PrinterUtils;
import com.aric.xmls.ssh.entity.Account;
import com.aric.xmls.ssh.entity.User;
import com.aric.xmls.ssh.service.AccountService;
import com.aric.xmls.ssh.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by tom.lee on 2016/3/16.
 * 程序入口类
 */

public class ApplicationMain {

    public static void main(String[] args) {

        login();
    }

    static void addTest(){
        ApplicationContext atc=new ClassPathXmlApplicationContext("/springContext.xml");
        UserService userService1=(UserService)atc.getBean("userService");
        PrinterUtils.printILog(userService1);
        User user=new User("china");
        PrinterUtils.printILog("持久化前的User: "+user);
        String id = userService1.saveUser(user).toString();
        PrinterUtils.printELog("ID: "+id);
        PrinterUtils.printILog("持久化后的User: "+user);
        User use2=new User();
        use2.setId(1);
//        userService1.delUser(use2);
        userService1.delUser(1);
    }


    static void findTest(){
        ApplicationContext atc=new ClassPathXmlApplicationContext("/springContext.xml");
        UserService userService1=(UserService)atc.getBean("userService");
        atc.getBean("");
        PrinterUtils.printILog(userService1);
        User user =userService1.findUserById("1");
        PrinterUtils.printELog(user);
    }

    static void login(){
        ApplicationContext atc=new ClassPathXmlApplicationContext("/springContext.xml");
        AccountService userService1=(AccountService)atc.getBean("accountService");
        PrinterUtils.printILog(userService1);
        Account user =userService1.login("张三", "123");
        PrinterUtils.printELog(user);
    }


}
