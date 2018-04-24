package xy;

import xy.alcs.common.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 14:05 2017-12-07
 */
public class Test {
    public static void main(String[] args) {
       /* String date = "2017-12-07";
        String replace = date.replace("-", "");
        System.out.println(replace);*/
     /*   int dateTime =1512629890;
        Date d2  = new Date(Long.parseLong(String.valueOf(dateTime)) * 1000);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(dateFormat.format(d2));*/

        System.out.println(Utils.getUUID());
    }

}
