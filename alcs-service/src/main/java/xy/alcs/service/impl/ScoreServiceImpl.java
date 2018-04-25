package xy.alcs.service.impl;

import org.springframework.stereotype.Service;
import xy.alcs.common.enums.AlcsErrorCode;
import xy.alcs.common.exception.BussinessException;
import xy.alcs.dao.AwardsMapper;
import xy.alcs.dao.CommentMapper;
import xy.alcs.dao.ContestMapper;
import xy.alcs.dao.WorksMapper;
import xy.alcs.manager.ScoreManager;
import xy.alcs.service.ScoreService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 17:39 2018-04-15
 */
@Service
public class ScoreServiceImpl implements ScoreService {

    @Resource
    private ContestMapper contestMapper;
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private WorksMapper worksMapper;
    @Resource
    private AwardsMapper awardsMapper;
    @Resource
    private ScoreManager scoreManager;


    @Override
    public Boolean countContestScore(List<Long> cids) {
        if (cids.size() > 3) {
            throw BussinessException.asBussinessException(AlcsErrorCode.PARAM_SIZE_BIG);
        }
       return  scoreManager.batchCountScore(cids);
    }
}
