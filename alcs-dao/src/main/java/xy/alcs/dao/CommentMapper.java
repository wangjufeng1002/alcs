package xy.alcs.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import xy.alcs.domain.Comment;
import xy.alcs.domain.CommentExample;
import xy.alcs.dto.CommentAvgDto;

public interface CommentMapper {
    int countByExample(CommentExample example);

    int deleteByExample(CommentExample example);

    int deleteByPrimaryKey(Long comId);

    int insert(Comment record);

    int insertSelective(Comment record);

    List<Comment> selectByExampleWithBLOBs(CommentExample example);

    List<Comment> selectByExample(CommentExample example);

    Comment selectByPrimaryKey(Long comId);

    int updateByExampleSelective(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByExampleWithBLOBs(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByExample(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKeyWithBLOBs(Comment record);

    int updateByPrimaryKey(Comment record);

    /**
     * 查询竞赛下各小组的平均分数
     * @param contestId
     * @return
     */
    List<CommentAvgDto> selectAvgSorceByContest(@Param("contestId") Long contestId);

    /**
     * 更新评语 和 分数
     * @param updateMap
     * @return
     */
    Integer updateSoceAndContent(Map<String,Object> updateMap);
}