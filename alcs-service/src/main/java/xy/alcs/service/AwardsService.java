package xy.alcs.service;

import xy.alcs.dto.AwardsDto;

import java.util.List;
import java.util.Map;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 17:10 2018-05-10
 */
public interface AwardsService {

    List<AwardsDto> queryMyAwardsList(Map<String, Object> queryMap);

    Integer countMyAwardsTotal(Map<String, Object> queryMap);
}
