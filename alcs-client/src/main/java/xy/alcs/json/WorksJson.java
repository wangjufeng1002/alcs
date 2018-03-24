package xy.alcs.json;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xy.alcs.common.enums.AlcsErrorCode;
import xy.alcs.common.utils.Result;
import xy.alcs.domain.Works;
import xy.alcs.service.WorksService;

import java.util.List;

/**
 * 报告控制器
 * @Author:ju
 * @Description:
 * @Date:Create in 17:58 2018-03-04
 */
@Controller
public class WorksJson {

    Logger logger = LoggerFactory.getLogger(WorksJson.class);


    @Autowired
    private WorksService worksService;

    /**
     * 获取我的作品
     * @param sId      学号
     * @param status   作品状态
     * @return
     */
    @RequestMapping("/works/listMyWorks")
    @ResponseBody
    public Result<List<Works>> getMyWorksList(String sId,String status){

        if(StringUtils.isBlank(sId)){
            logger.error("ContestJson.getMyConetestDetail 参数异常，sId为空");
            return  Result.buildErrorResult(AlcsErrorCode.PARAM_EXCEPTION);
        }
        Integer commitStatus = null;
        try {
             commitStatus = Integer.parseInt(status);
        } catch (NumberFormatException e) {
            logger.error("ContestJson.getMyConetestDetail 参数异常，status，error{}",e);
            return  Result.buildErrorResult(AlcsErrorCode.PARAM_EXCEPTION);
        }
        List<Works> works = worksService.listWorksBySidAndCommitStatus(sId, commitStatus);
        return Result.buildSuccessResult(works);
    }

}
