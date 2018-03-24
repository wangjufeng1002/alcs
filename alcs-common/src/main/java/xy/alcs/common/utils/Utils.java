package xy.alcs.common.utils;

import java.util.UUID;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 14:48 2018-03-04
 */
public class Utils {
    /**
     * 创建32位seqid 采用uuid算法
     * @return
     */
    public static String getUUID(){
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        return str.replaceAll("-", "");
    }
}
