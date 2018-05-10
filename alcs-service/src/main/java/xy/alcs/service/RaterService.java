package xy.alcs.service;

import xy.alcs.common.utils.Result;
import xy.alcs.domain.Admin;
import xy.alcs.domain.Rater;
import xy.alcs.dto.ContestDto;
import xy.alcs.dto.RaterDto;

import java.util.List;
import java.util.Map;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 15:52 2018-03-25
 */
public interface RaterService {

    /**
     * 分页查询评委列表
     *
     * @param queryMap
     * @return
     */
    List<RaterDto> listRater(Map<String, Object> queryMap, Long cid);

    /**
     * 统计总数
     *
     * @return
     */
    Integer countRater(Map<String, Object> queryMap, Long cid);


    /**
     * 查询比赛可分配的评委
     *
     * @return
     */
    List<RaterDto> queryRaterList(Map<String, Object> queryMap, Long cid);

    /**
     * 分配评委
     *
     * @param cid  比赛id
     * @param rids 评委id
     * @return
     */
    Boolean addRaterForContest(Long cid, List<Integer> rids);

    /**
     * 获取评委信息
     *
     * @param raterAccount
     * @return
     */
    Rater queryRaterInfo(String raterAccount);


    Boolean approvalWork(Map<String, Object> approvalParam);


    //评委信息管理所用函数

    /**
     * 查询评委信息
     * @param queryMap
     * @return
     */
    List<Rater> queryRaterList(Map<String, Object> queryMap);

    /**
     * 统计评委信息总数
     * @param queryMap
     * @return
     */
    Integer countRaterList(Map<String, Object> queryMap);

    /**
     * 更新评委信息
     * @param rater
     * @return
     */
    Boolean updateRater(Rater rater);

    /**
     * 添加评委信息
     * @param rater
     * @return
     */
    Result addRater(Rater rater);

    /**
     * 删除评委信息
     * @param rids
     * @return
     */
    Boolean delRater(List<Integer> rids);


    /**
     * 修改密码
     * @param account
     * @return
     */
    Boolean changePassWord(String account,String password);

}
