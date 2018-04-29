package xy.alcs.json;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import xy.alcs.common.utils.Result;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 17:51 2018-04-14
 */
@Controller
public class ScoreJson {

    @RequestMapping(value = "/score/count")
    public Result startStatistic(String cids){

      return null;
    }
}
