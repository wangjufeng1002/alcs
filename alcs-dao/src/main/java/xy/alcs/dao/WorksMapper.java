package xy.alcs.dao;

import org.apache.ibatis.annotations.Param;
import xy.alcs.domain.Rater;
import xy.alcs.domain.Works;
import xy.alcs.domain.WorksExample;
import xy.alcs.dto.CommentAvgDto;
import xy.alcs.dto.RaterWorkDto;
import xy.alcs.dto.WorkListDto;

import java.util.List;
import java.util.Map;

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

    /**
     * 更分数
     *
     * @param scoreList
     * @return
     */
    int updateScoreAvg(@Param("scoreList") List<CommentAvgDto> scoreList);

    /**
     * 查询作品列表
     *
     * @param queryParam
     * @return
     */
    List<WorkListDto> selectWork(Map<String, Object> queryParam);

    /**
     * 统计作品总数
     *
     * @param queryParam
     * @return
     */
    List<Integer> countWork(Map<String, Object> queryParam);

    /**
     * 批量删除
     *
     * @param wids 作品id集合
     * @return
     */
    Integer batchDelete(List<Long> wids);

    /**
     * 查询评委分配的作品
     *
     * @param queryParam 查询参数
     * @return
     */
    List<RaterWorkDto> selectRaterWorks(Map<String, Object> queryParam);

    /**
     * 统计分配给评委的作品数量
     *
     * @param queryParam
     * @return
     */
    Integer countRaterWorks(Map<String, Object> queryParam);

    /**
     * 查询分配给评委的竞赛详情
     *
     * @param queryParam
     * @return
     */
    RaterWorkDto selectRaterWorkDetail(Map<String, Object> queryParam);

    /**
     * 更新分数
     *
     * @param updateParam
     * @return
     */
    Integer updateScore(Map<String, Object> updateParam);

    /**
     * 查询作品分数列表
     * @param queryParam
     * @return
     */
    List<WorkListDto> selectScoreList(Map<String, Object> queryParam);

    /**
     * 统计 作品分数列表
     * @param queryParam
     * @return
     */
    List<Integer> countScoreList(Map<String, Object> queryParam);


    List<Rater> selectRaterList(Map<String, Object> queryParam);

    Works selectWorkByTeamId(@Param("teamId") String teamId);
}