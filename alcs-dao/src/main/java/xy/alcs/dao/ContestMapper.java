package xy.alcs.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import xy.alcs.domain.Contest;
import xy.alcs.domain.ContestExample;
import xy.alcs.dto.ContestDto;
import xy.alcs.dto.MyContestDetailDto;

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


    ///////////////////////////
    //分页查询
    List<ContestDto> selectContest(Map<String, Object> queryMap);

    //查询总记录数
    Integer countTotal(Map<String, Object> queryMap);


    //根据参数进行查询我的竞赛
    List<ContestDto> selectMyContestByParam(Map queryParamMap);

    //根据参数进行统计我的竞赛
    Integer countMyContestByParam(Map queryParamMap);

    //定时更新比赛状态。每天更新
    void updateContestStatus();

    //批量删除
    int deleteContest(@Param("cIds") List<Long> cIds);

    /**
     * 根据id进行查询
     *
     * @param cid 竞赛主键
     * @return
     */
    ContestDto selectContestDtoById(@Param("cid") Integer cid);

    /**
     * 查询分配给评委的信息
     *
     * @param queryMap
     * @return
     */
    List<ContestDto> selectRaterContest(Map<String, Object> queryMap);

    /**
     * 统计分配给评委的信息总数
     *
     * @param queryMap
     * @return
     */
    Integer countRaterContest(Map<String, Object> queryMap);


}