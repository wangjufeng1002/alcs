package xy.alcs.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xy.alcs.domain.Awards;
import xy.alcs.domain.AwardsExample;
import xy.alcs.dto.CommentAvgDto;

public interface AwardsMapper {
    int countByExample(AwardsExample example);

    int deleteByExample(AwardsExample example);

    int deleteByPrimaryKey(Long awardId);

    int insert(Awards record);

    int insertSelective(Awards record);

    List<Awards> selectByExample(AwardsExample example);

    Awards selectByPrimaryKey(Long awardId);

    int updateByExampleSelective(@Param("record") Awards record, @Param("example") AwardsExample example);

    int updateByExample(@Param("record") Awards record, @Param("example") AwardsExample example);

    int updateByPrimaryKeySelective(Awards record);

    int updateByPrimaryKey(Awards record);

    int batchInsertAward(@Param("scoreList") List<CommentAvgDto> scoreList);
}