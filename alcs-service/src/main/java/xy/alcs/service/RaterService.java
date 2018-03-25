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
     *
     * @param queryMap
     * @return
     */
    public List<RaterDto> listRater(Map<String,Object> queryMap);

    /**
     * 统计总数
     * @return
     */
    public Integer  countRater(Map<String,Object> queryMap);



}
