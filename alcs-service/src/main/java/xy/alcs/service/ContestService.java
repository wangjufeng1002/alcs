package xy.alcs.service;

import xy.alcs.common.utils.Result;
import xy.alcs.domain.Contest;
import xy.alcs.dto.ContestDateDto;
import xy.alcs.dto.ContestDto;
import xy.alcs.dto.MyContestDetailDto;
import xy.alcs.dto.MyContestWorkDto;

import java.util.List;
import java.util.Map;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 0:35 2017-12-07
 */
public interface ContestService {
    /**
     *
     * @param queryMap
     * @return
     */
     List<ContestDto> listContest(Map<String,Object> queryMap);

    /**
     * 统计总数
     * @return
     */
     Integer  countTotal(Map<String,Object> queryMap);

    /**
     * 根据参数查询我的赛事
     * @param sId     学号
     * @param offset  起始下标
     * @param limit   下标
     * @param status  赛事状态
     * @return
     */
     List<ContestDto> listMyContestByParam(String sId,Integer offset,Integer limit,Integer status);

    /**
     * 统计我的赛事
     * @param sId     学号
     * @param offset  起始下标
     * @param limit   下标
     * @param status  赛事状态
     * @return
     */

    Integer countMyContestByParam(String sId,Integer offset,Integer limit,Integer status);


    /**
     * 查询我的比赛详细信息
     */
    /**
     *
     * @param sId          学生id
     * @param contestId    比赛id
     * @return             比赛详细信息
     */
    MyContestDetailDto getMyContestDetail(String sId, Integer contestId);

    /**
     * 增加竞赛
     * @param contest
     * @return
     */
    Result addOrUpdateContest(Contest contest);

    /**
     * 转换日期格式
     * @param contest
     * @param contestDateDto
     * @return
     */
    Contest changeContestDateFormat(Contest contest, ContestDateDto contestDateDto);

    /**
     * 删除竞赛信息
     * @param cIds
     * @return
     */
    Boolean delContestByIds(List<Long> cIds);

    /**
     * 根据id进行查询
     * @param cid
     * @return
     */

    ContestDto queryContestDtoById(Integer cid);

    /**
     * 报名
     * @param cId  竞赛id
     * @param sId  学生id
     * @return
     */
    Result enrollContest(Long cId,String sId);


    /**
     * 查看我的比和作品提交信息
     * @param queryMap 查询参数map
     * @return
     */
    List<MyContestWorkDto> listMyContestWork(Map<String,Object> queryMap);

    /**
     * 计算我的报告总数
     * @param queryMap 查询与参数map
     * @return
     */
    Integer countMyContestWork(Map<String,Object> queryMap);
}
