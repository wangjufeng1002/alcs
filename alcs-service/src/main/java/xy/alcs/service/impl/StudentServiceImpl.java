package xy.alcs.service.impl;

import org.springframework.stereotype.Service;
import xy.alcs.common.enums.AlcsErrorCode;
import xy.alcs.common.exception.BussinessException;
import xy.alcs.dao.StudentMapper;
import xy.alcs.dto.StudentDto;
import xy.alcs.service.StudentService;

import javax.annotation.Resource;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 21:57 2018-04-23
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;

    @Override
    public StudentDto queryStudentDtoByStuId(String stuId) {
        if(stuId == null){
            throw BussinessException.asBussinessException(AlcsErrorCode.PARAM_ISNULL);
        }
       return studentMapper.selectStuInfo(stuId);
    }
}
