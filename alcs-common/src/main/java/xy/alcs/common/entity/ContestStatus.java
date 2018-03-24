package xy.alcs.common.entity;

/**
 * @Author:ju
 * @Description: 竞赛的三个状态
 * @Date:Create in 13:04 2018-03-03
 */
public interface ContestStatus {
    Integer  CONTEST_NOT_START = 0;    //未开始
    Integer  CONTEST_UNDERWAY  = 1;    //正在进行中
    Integer  CONTEST_END       = 2;    //已结束
}
