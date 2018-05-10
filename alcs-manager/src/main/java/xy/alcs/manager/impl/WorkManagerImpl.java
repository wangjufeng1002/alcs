package xy.alcs.manager.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import xy.alcs.dao.WorksMapper;
import xy.alcs.manager.BaseManager;
import xy.alcs.manager.WorkManager;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 16:17 2018-05-07
 */
@Repository("workManager")
public class WorkManagerImpl extends BaseManager implements WorkManager {

    private static Logger logger = LoggerFactory.getLogger(WorkManagerImpl.class);


    @Resource
    private WorksMapper worksMapper;

    @Override
    public Boolean batchDelWork(List<Long> wids) {

        return this.getTransactionTemplate().execute(transactionStatus -> {
            try {
                Integer integer = worksMapper.batchDelete(wids);
                if (integer > 0) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                logger.error("#WorkManagerImpl batchDelWork happend error:{}", e);
                transactionStatus.setRollbackOnly();
            }
            return true;
        });
    }
}
