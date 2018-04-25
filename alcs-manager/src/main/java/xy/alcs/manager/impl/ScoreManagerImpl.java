package xy.alcs.manager.impl;

import org.springframework.stereotype.Repository;
import xy.alcs.common.enums.AlcsErrorCode;
import xy.alcs.common.exception.BussinessException;
import xy.alcs.dao.*;
import xy.alcs.domain.RaterCompetition;
import xy.alcs.domain.RaterCompetitionExample;
import xy.alcs.dto.CommentAvgDto;
import xy.alcs.manager.BaseManager;
import xy.alcs.manager.ScoreManager;
import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;


/**
 * @Author:ju
 * @Description:
 * @Date:Create in 17:43 2018-04-15
 */
@Repository("scoreManager")
public class ScoreManagerImpl  extends BaseManager implements ScoreManager {
    @Resource
    private ContestMapper contestMapper;
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private WorksMapper worksMapper;
    @Resource
    private AwardsMapper awardsMapper;
    @Resource
    private RaterCompetitionMapper raterCompetitionMapper;




    @Override
    public Boolean batchCountScore(List<Long> cids) {
        return this.getTransactionTemplate().execute(transactionStatus -> {
            try {
                for (Long cid : cids) {
                    RaterCompetitionExample example = new RaterCompetitionExample();
                    example.createCriteria().andContestIdEqualTo(cid);
                    List<RaterCompetition> raterCompetitions = raterCompetitionMapper.selectByExample(example);
                    if (raterCompetitions.size() < 1) {
                        throw BussinessException.asBussinessException(AlcsErrorCode.SYSTEM_ERROR);
                    }
                    Integer size = raterCompetitions.size();
                    List<CommentAvgDto> commentAvgDtos = commentMapper.selectAvgSorceByContest(cid);
                    for (CommentAvgDto dto : commentAvgDtos) {
                        if (!dto.getSize().equals(size)) {
                            throw BussinessException.asBussinessException(AlcsErrorCode.SYSTEM_ERROR);
                        }
                    }
                    //更新作品表中的分数字段
                    worksMapper.updateScoreAvg(commentAvgDtos);
                    //插入获奖表中
                    //进行排序
                    commentAvgDtos.stream().sorted(Comparator.comparing(CommentAvgDto::getAvg).reversed());
                    //插入操作
                    awardsMapper.batchInsertAward(commentAvgDtos);

                }
            } catch (Exception e) {
                if (e instanceof BussinessException){
                    transactionStatus.setRollbackOnly();
                    throw  e;
                }
                else{
                    logger.error("#ScoreManagerImpl batchCountScore  error:{}",e);
                    transactionStatus.setRollbackOnly();
                    throw  e;
                }
            }
            return true;
        });

    }
}
