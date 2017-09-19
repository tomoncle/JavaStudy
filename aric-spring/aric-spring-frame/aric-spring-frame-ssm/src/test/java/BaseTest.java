//import com.aric.common.utils.PrinterUtils;
import com.aric.common.utils.PrinterUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/beans.xml")
public class BaseTest  {

    @Test
    public  void  aa(){
        PrinterUtils.printBefore();
    }
//    public static void main(String[] args) {
//        URLClassLoader classLoader = (URLClassLoader)Main.class.getClassLoader();
//        System.out.println(Arrays.toString(classLoader.getURLs()));
//    }
}