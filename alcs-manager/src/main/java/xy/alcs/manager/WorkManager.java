package xy.alcs.manager;

import sun.rmi.runtime.Log;

import java.util.List;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 16:16 2018-05-07
 */
public interface WorkManager {

    Boolean batchDelWork(List<Long> wids);
}
