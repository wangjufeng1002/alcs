package xy.alcs.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xy.alcs.domain.Clas;
import xy.alcs.domain.ClasExample;

public interface ClasMapper {
    int countByExample(ClasExample example);

    int deleteByExample(ClasExample example);

    int deleteByPrimaryKey(Integer claId);

    int insert(Clas record);

    int insertSelective(Clas record);

    List<Clas> selectByExample(ClasExample example);

    Clas selectByPrimaryKey(Integer claId);

    int updateByExampleSelective(@Param("record") Clas record, @Param("example") ClasExample example);

    int updateByExample(@Param("record") Clas record, @Param("example") ClasExample example);

    int updateByPrimaryKeySelective(Clas record);

    int updateByPrimaryKey(Clas record);
}