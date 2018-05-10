package xy.alcs.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import xy.alcs.domain.Student;
import xy.alcs.domain.StudentExample;
import xy.alcs.domain.StudentKey;
import xy.alcs.dto.StudentDto;

public interface StudentMapper {
    int countByExample(StudentExample example);

    int deleteByExample(StudentExample example);

    int deleteByPrimaryKey(StudentKey key);

    int insert(Student record);

    int insertSelective(Student record);

    List<Student> selectByExample(StudentExample example);

    Student selectByPrimaryKey(StudentKey key);

    int updateByExampleSelective(@Param("record") Student record, @Param("example") StudentExample example);

    int updateByExample(@Param("record") Student record, @Param("example") StudentExample example);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    StudentDto selectStuInfo(@Param("stuId") String stuId);

    /**
     * 查询学生列表
     *
     * @param queryMap 查询参数map
     * @return
     */
    List<StudentDto> selectStuList(Map<String, Object> queryMap);

    /**
     * 统计学生总数
     *
     * @param queryMap 查询参数map
     * @return
     */
    Integer countStuList(Map<String, Object> queryMap);

}