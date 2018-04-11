package xy.alcs.service;

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
    Boolean addRater(Rater rater);

    /**
     * 分页查询评委列表
     * @param queryMap
     * @return
     */
     List<RaterDto> listRater(Map<String,Object> queryMap,Long cid);

    /**
     * 统计总数
     * @return
     */
     Integer  countRater(Map<String,Object> queryMap,Long cid);


    /**
     * 查询比赛可分配的评委
     * @return
     */
     List<RaterDto> queryRaterList(Long cid);

    /**
     * 分配评委
     * @param cid  比赛id
     * @param rids 评委id
     * @return
     */
     Boolean addRaterForContest(Long  cid,List<Integer> rids);

}
