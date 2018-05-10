package xy.alcs.common.task.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import xy.alcs.common.enums.ContestStatusEnum;
import xy.alcs.common.task.ContestTask;
import xy.alcs.dao.ContestMapper;
import xy.alcs.domain.Contest;
import xy.alcs.domain.ContestExample;
import xy.alcs.dto.ContestDto;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Author:ju
 * @Description:  定时任务，每天跟新
 * @Date:Create in 17:03 2018-03-03
 */
@Component
public class ContestTaskImpl implements ContestTask {

    Logger logger = LoggerFactory.getLogger(ContestTaskImpl.class);

    @Resource
    private ContestMapper contestMapper;

    /**
     * 每天23:59:55 触发，将结束时间再此之前竞赛状态改为 0
     * （1：正在进行；0：已结束）
     */
    @Scheduled(cron="00 00 00 * * ? ")
    @Override
    public void updateContestStatus() {
        logger.info("===================定时任务开启==================");
        try {
            logger.info("定时任务进行中");
            Map<String,Object> queryMap = new HashMap<>();
            for(int i= 0;;i+=1000){
                queryMap.put("offset",i);
                queryMap.put("limit",1000);
                List<ContestDto> contestDtos = contestMapper.selectContest(queryMap);
                this.updateContestStatusHandler(contestDtos);
            }
        } catch (Exception e) {
           logger.error("定时任务出错：" + e);
        }
        logger.info("===================定时任务完成==================");
    }

    private void updateContestStatusHandler(List<ContestDto> contestDtos){
        Contest contest = new Contest();
        for(ContestDto contestDto : contestDtos){
            //先判断竞赛是否结束
            Calendar startDate = Calendar.getInstance();
            startDate.setTime(contestDto.getStartDate());
            Calendar endDate = Calendar.getInstance();
            endDate.setTime(contestDto.getEndDate());
            Calendar nowDate = Calendar.getInstance();
            if(endDate.before(nowDate)){
                this.updateStatusHandler(contestDto.getCid(),ContestStatusEnum.CONTEST_END);
                continue;
            }


            //判断竞赛是否在报名时间内
            Calendar enrollStartDate = Calendar.getInstance();
            Calendar enrollEndDate   = Calendar.getInstance();
            enrollStartDate.setTime(contestDto.getEnrollStartDate());
            enrollEndDate.setTime(contestDto.getEnrollEndDate());
            if((nowDate.after(enrollStartDate) || nowDate.equals(enrollStartDate))
                    && (nowDate.before(enrollEndDate ) || nowDate.equals(enrollEndDate))
            ) { //报名时间内
                this.updateStatusHandler(contestDto.getCid(),ContestStatusEnum.CONTEST_ENROLL);
                continue;
            }

            Calendar scoreStartDate = Calendar.getInstance();
            Calendar scoreEndDate   = Calendar.getInstance();
            enrollStartDate.setTime(contestDto.getScoreStartDate());
            enrollEndDate.setTime(contestDto.getScoreEndDate());
            //报名时间内
            if((nowDate.after(scoreStartDate) || nowDate.equals(scoreStartDate))
                    && (nowDate.before(scoreEndDate ) || nowDate.equals(scoreEndDate))
                    ) { //报名时间内
                this.updateStatusHandler(contestDto.getCid(),ContestStatusEnum.CONTEST_SCORE);
                continue;
            }
            //打分结束
            if(nowDate.after(scoreEndDate)){
                this.updateStatusHandler(contestDto.getCid(),ContestStatusEnum.CONTEST_SCORE_END);
                continue;
            }
            //比赛启动
            if(nowDate.after(startDate) && nowDate.before(enrollStartDate)){
                this.updateStatusHandler(contestDto.getCid(),ContestStatusEnum.CONTEST_START);
                continue;
            }
            //比赛未开始
            if(nowDate.before(startDate)){
                this.updateStatusHandler(contestDto.getCid(),ContestStatusEnum.CONTEST_NOT_START);
                continue;
            }
            this.updateStatusHandler(contestDto.getCid(),ContestStatusEnum.CONTEST_END);

        }
    }
    public void updateStatusHandler(Long cid,ContestStatusEnum contestStatusEnum){
         ContestExample contestExample = new ContestExample();
         contestExample.createCriteria().andCidEqualTo(cid);

         Contest contest = new Contest();
         contest.setStatus(contestStatusEnum.getCode());

         contestMapper.updateByExampleSelective(contest,contestExample);
    }
}
