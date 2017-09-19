import com.aric.common.utils.PrinterUtils;
import com.aric.zkclient.App;
import com.aric.zkclient.service.ZKClientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by tom.lee on 2016/3/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(App.class)
public class BaseTest {

    @Autowired
    ZKClientService zkClientService;

    @Test
    public void test(){
        zkClientService.createPersistentNode("/bbbb/ccccc");
    }



}
