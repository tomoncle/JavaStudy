import com.aric.common.utils.DateUtils;
import com.aric.common.utils.PrinterUtils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by liyuanjun on 16-12-2.
 */
public class DateUtilsTest {

    public static void main(String[] args)throws Exception {

        PrinterUtils.printELog( DateUtils.dateFormat("2012-12-12"));

        DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        DateFormat formatter2 = new SimpleDateFormat("dd-MM-yyyy");
        PrinterUtils.printELog(formatter.parse("8-24-2013"));
        PrinterUtils.printELog(formatter2.format(formatter.parse("8-24-2013")));

        PrinterUtils.printELog( DateUtils.dateFormat("8-24-2013"));
    }

}
