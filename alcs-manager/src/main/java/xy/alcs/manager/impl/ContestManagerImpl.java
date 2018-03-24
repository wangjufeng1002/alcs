package xy.alcs.manager.impl;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import xy.alcs.common.enums.AlcsErrorCode;
import xy.alcs.common.exception.BussinessException;
import xy.alcs.dao.ContestMapper;
import xy.alcs.manager.BaseManager;
import xy.alcs.manager.ContestManager;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 16:47 2018-03-24
 */
@Repository("contestManager")
public class ContestManagerImpl extends BaseManager implements ContestManager {

    @Resource
    private ContestMapper contestMapper;

    @Override
    public Boolean batchDelete(List<Long> cIds) {
       return this.getTransactionTemplate().execute(transactionStatus -> {
           if(CollectionUtils.isEmpty(cIds)){
               logger.error("#ContestManagerImpl batchDelete param  cIds is empty");
               throw BussinessException.asBussinessException(AlcsErrorCode.PARAM_ISNULL);
           }
            try {
                contestMapper.deleteContest(cIds);
            } catch (Exception e) {
                logger.error("#ContestManagerImpl batchDelete happend errror:{}",e);
                throw BussinessException.asBussinessException(AlcsErrorCode.DAO_EXCEPTION);
            }
            return true;
        });
    }
}
