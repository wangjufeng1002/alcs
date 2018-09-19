package xy.alcs.service;

import xy.alcs.common.utils.Result;
import xy.alcs.domain.Student;
import xy.alcs.domain.StudentCompetition;
import xy.alcs.dto.StudentDto;

import java.util.List;
import java.util.Map;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 21:55 2018-04-23
 */
public interface StudentService {
    /**
     * 根据学号获取学生基本信息
     *
     * @param stuId 学号
     * @return
     */
    StudentDto queryStudentDtoByStuId(String stuId);

    /**
     * 查询学生列表
     *
     * @param queryMap 查询参数map
     * @return
     */
    List<StudentDto> queryStudentList(Map<String, Object> queryMap);

    /**
     * 统计学生总数
     *
     * @param queryMap 查询参数map
     * @return
     */
    Integer countStudentListTotal(Map<String, Object> queryMap);

    /**
     * 获取部门
     *
     * @param type
     * @param id
     * @return
     */
    Object queryDepartment(Integer type, Integer id);

    /**
     * 添加学生信息
     *
     * @param student
     * @return
     */
    Result addStudent(Student student);

    /**
     * 删除学生信息
     *
     * @param stuId 学号
     * @param sid   学生id
     * @return
     */
    Boolean delStudnet(List<String> stuId, List<Long> sid);


    Boolean updateStudent(Student student);


    Boolean changePassWord(String account, String password);

    /**
     * 查询学生参与竞赛信息
     *
     * @param stuId 学生ID
     * @return
     */
    StudentCompetition queryStudentCompetition(String stuId);

    Boolean canelEnroll(String stuId, Long cId);
}
