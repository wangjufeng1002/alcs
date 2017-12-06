package xy.alcs.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xy.alcs.domain.Major;
import xy.alcs.domain.MajorExample;

public interface MajorMapper {
    int countByExample(MajorExample example);

    int deleteByExample(MajorExample example);

    int deleteByPrimaryKey(Integer majId);

    int insert(Major record);

    int insertSelective(Major record);

    List<Major> selectByExample(MajorExample example);

    Major selectByPrimaryKey(Integer majId);

    int updateByExampleSelective(@Param("record") Major record, @Param("example") MajorExample example);

    int updateByExample(@Param("record") Major record, @Param("example") MajorExample example);

    int updateByPrimaryKeySelective(Major record);

    int updateByPrimaryKey(Major record);
}