package xy.alcs.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import xy.alcs.domain.StudentCompetition;
import xy.alcs.domain.StudentCompetitionExample;
import xy.alcs.dto.MyContestDetailDto;
import xy.alcs.dto.MyContestWorkDto;

public interface StudentCompetitionMapper {
    int countByExample(StudentCompetitionExample example);

    int deleteByExample(StudentCompetitionExample example);

    int deleteByPrimaryKey(Long scId);

    int insert(StudentCompetition record);

    int insertSelective(StudentCompetition record);

    List<StudentCompetition> selectByExample(StudentCompetitionExample example);

    StudentCompetition selectByPrimaryKey(Long scId);

    int updateByExampleSelective(@Param("record") StudentCompetition record, @Param("example") StudentCompetitionExample example);

    int updateByExample(@Param("record") StudentCompetition record, @Param("example") StudentCompetitionExample example);

    int updateByPrimaryKeySelective(StudentCompetition record);

    int updateByPrimaryKey(StudentCompetition record);
    /**

     * 查询某比赛的详细信息
     * @param sId   学号id
     * @param cId   竞赛id
     * @return
     */
    MyContestDetailDto selectContestBySidAndCid(@Param("sId") String sId,@Param("cId") Integer cId);

    /**
     * 查询报告信息
     * @param queryMap 参数map
     * @return
     */
    List<MyContestWorkDto> selectContestByWorkCommitStatus(Map<String,Object> queryMap);

    /**
     * 计算报告总数
     * @param queryMap
     * @return
     */
    Integer countContestByWorkCommitStatus(Map<String,Object> queryMap);



    Integer updateCommitStatus(Map<String,Object> updateMap);
}