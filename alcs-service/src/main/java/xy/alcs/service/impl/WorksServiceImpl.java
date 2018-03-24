package xy.alcs.service.impl;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xy.alcs.dao.WorksMapper;
import xy.alcs.domain.Works;
import xy.alcs.service.WorksService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 18:38 2018-03-04
 */
@Service("worksService")
public class WorksServiceImpl implements WorksService {


    @Resource
    private WorksMapper worksMapper;


    @Override
    public List<Works> listWorksBySidAndCommitStatus(String sId,Integer commit) {

        return worksMapper.selectWorksBySIdAndCommitStatus(sId,commit);
    }
}
