package xy.alcs.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import xy.alcs.dao.StudentMapper;
import xy.alcs.domain.Student;
import xy.alcs.manager.BaseManager;
import xy.alcs.manager.StudentManager;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 22:22 2018-05-17
 */
@Repository("studentManager")
public class StudentManagerImpl extends BaseManager implements StudentManager {

    @Resource
    private StudentMapper studentMapper;

    @Override
    public Boolean batchInsertStu(List<Student> studentList) {
        return this.getTransactionTemplate().execute(transactionStatus -> {
            try {
                Integer integer = studentMapper.batchAddStu(studentList);
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
