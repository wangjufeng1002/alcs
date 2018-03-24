package xy.alcs.common.task.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import xy.alcs.common.task.ContestTask;
import xy.alcs.dao.ContestMapper;

/**
 * @Author:ju
 * @Description:  定时任务，每天跟新
 * @Date:Create in 17:03 2018-03-03
 */
@Component
public class ContestTaskImpl implements ContestTask {

    Logger logger = LoggerFactory.getLogger(ContestTaskImpl.class);

    @Autowired
    private ContestMapper contestMapper;

    /**
     * 每天23:59:55 触发，将结束时间再此之前竞赛状态改为 0
     * （1：正在进行；0：已结束）
     */
    @Scheduled(cron="55 59 23 * * ? ")
    @Override
    public void updateContestStatus() {
        logger.info("===================定时任务开启==================");
        try {
            contestMapper.updateContestStatus();
        } catch (Exception e) {
           logger.error("定时任务出错：" + e);
        }
        logger.info("===================定时任务完成==================");
    }
}
