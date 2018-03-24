package xy.alcs.common.impl;

import xy.alcs.domain.Contest;
import xy.alcs.dto.ContestDto;

import java.util.List;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 1:18 2017-12-07
 */
public interface ChangeBean {
    public List<ContestDto> changeBean(List<Contest> contests);
}
