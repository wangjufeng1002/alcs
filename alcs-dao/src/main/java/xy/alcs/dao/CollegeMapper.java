package xy.alcs.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xy.alcs.domain.College;
import xy.alcs.domain.CollegeExample;

public interface CollegeMapper {
    int countByExample(CollegeExample example);

    int deleteByExample(CollegeExample example);

    int deleteByPrimaryKey(Integer colId);

    int insert(College record);

    int insertSelective(College record);

    List<College> selectByExample(CollegeExample example);

    College selectByPrimaryKey(Integer colId);

    int updateByExampleSelective(@Param("record") College record, @Param("example") CollegeExample example);

    int updateByExample(@Param("record") College record, @Param("example") CollegeExample example);

    int updateByPrimaryKeySelective(College record);

    int updateByPrimaryKey(College record);
}