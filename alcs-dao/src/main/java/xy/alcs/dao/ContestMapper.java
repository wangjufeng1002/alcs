package xy.alcs.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xy.alcs.domain.Contest;
import xy.alcs.domain.ContestExample;

public interface ContestMapper {
    int countByExample(ContestExample example);

    int deleteByExample(ContestExample example);

    int deleteByPrimaryKey(Long cid);

    int insert(Contest record);

    int insertSelective(Contest record);

    List<Contest> selectByExample(ContestExample example);

    Contest selectByPrimaryKey(Long cid);

    int updateByExampleSelective(@Param("record") Contest record, @Param("example") ContestExample example);

    int updateByExample(@Param("record") Contest record, @Param("example") ContestExample example);

    int updateByPrimaryKeySelective(Contest record);

    int updateByPrimaryKey(Contest record);

    List<Contest> selectList();
}