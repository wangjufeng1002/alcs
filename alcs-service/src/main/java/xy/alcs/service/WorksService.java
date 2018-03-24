package xy.alcs.service;

import xy.alcs.domain.Works;

import java.util.List;

/**作品service
 * @Author:ju
 * @Description:
 * @Date:Create in 18:32 2018-03-04
 */
public interface WorksService {
    /**
     *
     * @param sId      学号
     * @param commit   作品状态（0，未填写  1：已保存但未提交 2 已提交）
     * @return
     */
    public List<Works> listWorksBySidAndCommitStatus(String sId,Integer commit);
}
