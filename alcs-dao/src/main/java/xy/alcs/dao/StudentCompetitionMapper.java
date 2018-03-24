package xy.alcs.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xy.alcs.domain.StudentCompetition;
import xy.alcs.domain.StudentCompetitionExample;
import xy.alcs.dto.MyContestDetailDto;

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
}