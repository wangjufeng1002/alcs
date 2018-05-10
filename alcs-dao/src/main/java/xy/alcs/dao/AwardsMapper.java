package xy.alcs.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import xy.alcs.domain.Awards;
import xy.alcs.domain.AwardsExample;
import xy.alcs.dto.AwardsDto;
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

    /**
     * 查询我的奖项
     * @param queryMap
     * @return
     */
    List<AwardsDto> selectMyAwardsList(Map<String, Object> queryMap);
    /**
     * 统计我的奖项个数
     * @param queryMap
     * @return
     */
    Integer countMyAwardsTotal(Map<String, Object> queryMap);
}