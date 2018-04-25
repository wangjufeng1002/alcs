package xy.alcs.manager.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import xy.alcs.common.enums.AlcsErrorCode;
import xy.alcs.common.exception.BussinessException;
import xy.alcs.dao.ContestMapper;
import xy.alcs.dao.RaterCompetitionMapper;
import xy.alcs.dao.RaterMapper;
import xy.alcs.domain.Contest;
import xy.alcs.domain.Rater;
import xy.alcs.domain.RaterCompetitionExample;
import xy.alcs.domain.RaterExample;
import xy.alcs.manager.BaseManager;
import xy.alcs.manager.ContestManager;
import xy.alcs.manager.RaterManager;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 21:01 2018-04-11
 */
@Repository("raterManager")
public class RaterManagerImpl extends BaseManager implements RaterManager {

    @Resource
    private RaterMapper raterMapper;
    @Resource
    private RaterCompetitionMapper raterCompetitionMapper;
    @Resource
    private ContestMapper contestMapper;

    @Override
    public Boolean batchInsertRaterContest(List<Integer> rids, Long cid) {
        return this.getTransactionTemplate().execute(transactionStatus -> {
            try {
                if(CollectionUtils.isEmpty(rids) || cid == null){
                    logger.error("#RaterManagerImpl batchInsertRaterContest param  cIds is empty");
                    throw BussinessException.asBussinessException(AlcsErrorCode.PARAM_ISNULL);
                }

                //删出之前的分配关系
                RaterCompetitionExample raterCompetitionExample = new RaterCompetitionExample();
                raterCompetitionExample.createCriteria().andContestIdEqualTo(cid);
                raterCompetitionMapper.deleteByExample(raterCompetitionExample);

                RaterExample example = new RaterExample();
                example.createCriteria().andRidIn(rids);
                List<Rater> list = raterMapper.selectByExample(example);

                Contest contest = new Contest();
                contest.setCid(cid);
                contest.setExtendJson(JSON.toJSONString(list));
                int update = contestMapper.updateByPrimaryKeySelective(contest);
                if(update < 1){
                    throw BussinessException.asBussinessException(AlcsErrorCode.DAO_EXCEPTION);
                }
                Map<String,Object> addMap = new HashMap<>();
                addMap.put("rids",rids);
                addMap.put("cid",cid);
                int insert = raterCompetitionMapper.insertRaterForContest(addMap);

                if(insert < 1){
                    throw BussinessException.asBussinessException(AlcsErrorCode.DAO_EXCEPTION);
                }
            } catch (BussinessException e) {
                logger.error("#RaterManagerImpl batchInsertRaterContest error:{}",e);
                transactionStatus.setRollbackOnly();
            }catch (Exception e){
                logger.error("#RaterManagerImpl batchInsertRaterContest error:{}",e);
                transactionStatus.setRollbackOnly();
            }
            return true;
        });
    }
}
