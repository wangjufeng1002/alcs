package xy.alcs.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xy.alcs.domain.Student;
import xy.alcs.domain.StudentExample;
import xy.alcs.domain.StudentKey;

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
}