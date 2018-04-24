package xy.alcs.common.entity;

/**
 * @Author:ju
 * @Description: 竞赛状态
 * @Date:Create in 13:04 2018-03-03
 */
public interface ContestStatus {
    Integer CONTEST_NOT_START = 0;      //未开始
    Integer CONTEST_START     = 1;      //比赛已启动
    Integer CONTEST_ENROLL    = 2;      //可以报名
    Integer CONTEST_SCORE     = 3;      //正在打分（禁止报名，禁止提交作品，禁止分配评委）
    Integer CONTEST_SCORE_END = 4;      //打分结束
    Integer CONTEST_END       = 5;      //已结束

}
