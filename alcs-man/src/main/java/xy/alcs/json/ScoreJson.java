package xy.alcs.json;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xy.alcs.common.enums.AlcsErrorCode;
import xy.alcs.common.exception.BussinessException;
import xy.alcs.common.utils.Result;
import xy.alcs.service.ScoreService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 17:51 2018-04-14
 */
@Controller
public class ScoreJson {

    @Resource
    private ScoreService scoreService;

    @ResponseBody
    @RequestMapping(value = "/score/count")
    public Result startStatistic(String cids) {
        if (StringUtils.isBlank(cids)) {
            throw BussinessException.asBussinessException(AlcsErrorCode.PARAM_EXCEPTION);
        }
        List<Long> cidList = JSON.parseObject(cids, List.class);
        Boolean res = scoreService.countContestScore(cidList);
        if (res) {
            return Result.buildSuccessResult();
        }
        return Result.buildErrorResult(AlcsErrorCode.SYSTEM_ERROR);
    }
}
