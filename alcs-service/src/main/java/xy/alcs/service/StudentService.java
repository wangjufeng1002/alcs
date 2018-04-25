package xy.alcs.service;

import xy.alcs.dto.StudentDto;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 21:55 2018-04-23
 */
public interface StudentService {
    /**
     * 根据学号获取学生基本信息
     * @param stuId 学号
     * @return
     */
    StudentDto queryStudentDtoByStuId(String stuId);
}
