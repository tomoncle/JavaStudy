import base.BaseTest;
import com.aric.annotations.started.config.StringConfig;
import com.aric.common.utils.PrinterUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by tom.lee on 2016/3/16.
 */
public class TestA extends BaseTest {

    @Autowired
    private StringConfig stringConfig;

    @Qualifier("king")
    private String string;

    @Test
    public  void  test1(){
        PrinterUtils.printJun();
        PrinterUtils.printELog(stringConfig);
        PrinterUtils.printELog(string);
    }

}
