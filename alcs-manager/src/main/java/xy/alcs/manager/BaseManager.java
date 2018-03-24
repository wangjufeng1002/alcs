package xy.alcs.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 16:41 2018-03-24
 */

public class BaseManager {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private DataSourceTransactionManager transactionManager;


    protected TransactionTemplate getTransactionTemplate(){
        return new TransactionTemplate(transactionManager);
    }
    @Resource
    public void setTransactionManager(DataSourceTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }
}
