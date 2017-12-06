package xy.alcs.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xy.alcs.domain.Rater;
import xy.alcs.domain.RaterExample;

public interface RaterMapper {
    int countByExample(RaterExample example);

    int deleteByExample(RaterExample example);

    int deleteByPrimaryKey(Integer rid);

    int insert(Rater record);

    int insertSelective(Rater record);

    List<Rater> selectByExample(RaterExample example);

    Rater selectByPrimaryKey(Integer rid);

    int updateByExampleSelective(@Param("record") Rater record, @Param("example") RaterExample example);

    int updateByExample(@Param("record") Rater record, @Param("example") RaterExample example);

    int updateByPrimaryKeySelective(Rater record);

    int updateByPrimaryKey(Rater record);
}