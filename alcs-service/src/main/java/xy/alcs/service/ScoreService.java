package xy.alcs.service;

import java.util.List;

/**
 * @Author:ju
 * @Description: 分数相关service
 * @Date:Create in 17:58 2018-04-14
 */
public interface ScoreService {
    /**
     * 统计竞赛成绩
     *
     * @param cids 竞赛id集合
     * @return 返回操作结果
     */
    Boolean countContestScore(List<Long> cids);

}
