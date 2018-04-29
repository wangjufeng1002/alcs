package xy.alcs.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import xy.alcs.domain.Works;
import xy.alcs.domain.WorksExample;
import xy.alcs.dto.CommentAvgDto;
import xy.alcs.dto.MyContestDetailDto;

public interface WorksMapper {
    int countByExample(WorksExample example);

    int deleteByExample(WorksExample example);

    int deleteByPrimaryKey(Long wId);

    int insert(Works record);

    int insertSelective(Works record);

    List<Works> selectByExampleWithBLOBs(WorksExample example);

    List<Works> selectByExample(WorksExample example);

    Works selectByPrimaryKey(Long wId);

    int updateByExampleSelective(@Param("record") Works record, @Param("example") WorksExample example);

    int updateByExampleWithBLOBs(@Param("record") Works record, @Param("example") WorksExample example);

    int updateByExample(@Param("record") Works record, @Param("example") WorksExample example);

    int updateByPrimaryKeySelective(Works record);

    int updateByPrimaryKeyWithBLOBs(Works record);

    int updateByPrimaryKey(Works record);


    int updateScoreAvg(@Param("scoreList") List<CommentAvgDto> scoreList);

}