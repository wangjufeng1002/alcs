package xy.alcs.manager;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 16:44 2018-03-24
 */

public interface ContestManager {
    /**
     * 根据id批量删除
     * @param cIds 主键集合
     * @return
     */
    Boolean batchDelete(List<Long> cIds);
}
