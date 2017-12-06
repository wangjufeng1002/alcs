package xy.alcs.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xy.alcs.domain.RaterCompetition;
import xy.alcs.domain.RaterCompetitionExample;

public interface RaterCompetitionMapper {
    int countByExample(RaterCompetitionExample example);

    int deleteByExample(RaterCompetitionExample example);

    int deleteByPrimaryKey(Long rcId);

    int insert(RaterCompetition record);

    int insertSelective(RaterCompetition record);

    List<RaterCompetition> selectByExample(RaterCompetitionExample example);

    RaterCompetition selectByPrimaryKey(Long rcId);

    int updateByExampleSelective(@Param("record") RaterCompetition record, @Param("example") RaterCompetitionExample example);

    int updateByExample(@Param("record") RaterCompetition record, @Param("example") RaterCompetitionExample example);

    int updateByPrimaryKeySelective(RaterCompetition record);

    int updateByPrimaryKey(RaterCompetition record);
}