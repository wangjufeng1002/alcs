package xy.alcs.manager;

import java.util.List;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 21:00 2018-04-11
 */
public interface RaterManager {
    Boolean batchInsertRaterContest(List<Integer> rids,Long cid);
}
