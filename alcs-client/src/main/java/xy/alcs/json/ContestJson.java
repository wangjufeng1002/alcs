package xy.alcs.json;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.security.krb5.internal.PAData;
import xy.alcs.common.entity.PageData;
import xy.alcs.common.enums.AlcsErrorCode;
import xy.alcs.common.utils.Result;
import xy.alcs.dto.ContestDto;
import xy.alcs.dto.MyContestDetailDto;
import xy.alcs.service.ContestService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:ju
 * @Description: 竞赛相关json接口
 * @Date:Create in 21:33 2017-12-06
 */
@Controller
public class ContestJson {

    Logger logger = LoggerFactory.getLogger(ContestJson.class);
    @Autowired
    private ContestService contestService;


    /**
     * 默认的每页显示的数据
     */
    @Value("${page.limit}")
    private String PAGELIMIT;

    /**
     * 默认显示的当前页
     */
    @Value("${page.now}")
    private String PAGENOW;
    /**
     * 赛事默认状态
     */
    @Value("${contest.status}")
    private String CONTESTSTATUS;

    /**
     * @param page 当前页
     * @param rows  偏移量
     * @return 竞赛信息集合
     */
    @RequestMapping(value = "/contest/list")
    @ResponseBody
    public PageData<ContestDto> listContest(Integer page, Integer rows,String queryParam) {
        Map<String,Object> map = new HashMap<>();
        if (page == null || page.intValue() <= 0) {
            page = Integer.parseInt(PAGENOW);
        }
        if (rows == null || rows.intValue() < 0) {
            rows = Integer.parseInt(PAGELIMIT);
        }
        if(!StringUtils.isEmpty(queryParam)){
             map = JSONObject.parseObject(queryParam, Map.class);
        }

        map.put("page",page);
        map.put("rows",rows);

        List<ContestDto> contests = contestService.listContest(map);
        Integer total = contestService.countTotal();
        PageData<ContestDto> pageData = new PageData();
        pageData.setRows(contests);
        pageData.setTotal(total);
        return pageData;

    }

    /**
     * @param sId    学生id
     * @param offset 起始下标
     * @param limit  偏移量
     * @param status 状态 0正在进行，1已结束
     * @return
     */
    @RequestMapping(value = "/contest/listMyContest")
    @ResponseBody
    //TODO 修改分页查询
    public PageData<ContestDto> listContestUnderwayOrStop(String sId, Integer offset, Integer limit, Integer status) {
        if (sId == null) {
            Result.buildErrorResult(AlcsErrorCode.PARAM_EXCEPTION);
        }
        if (limit == null || limit.intValue() <= 0) {
            System.out.println("PAGELIMIT == " + PAGELIMIT);
            limit = Integer.parseInt(PAGELIMIT);
        }
        if (offset == null || offset.intValue() < 0) {
            offset = Integer.parseInt(PAGENOW);
        }
        if (status == null) {
            status = Integer.parseInt(CONTESTSTATUS);
        }
        Integer total = contestService.countMyContestByParam(sId, offset, limit, status);
        List<ContestDto> contestDtos = contestService.listMyContestByParam(sId, offset, limit, status);
        PageData<ContestDto> pageData = new PageData();
        pageData.setRows(contestDtos);
        pageData.setTotal(total);
        return pageData;

    }

    /**
     *
     * @param sId  学生Id
     * @param cId  竞赛Id
     * @return
     */
    @RequestMapping(value = "/contest/getMyContestDetail")
    @ResponseBody
    public Result<MyContestDetailDto> getMyConetestDetail(String sId,String cId){
        Integer contestId = null;
        if(StringUtils.isBlank(sId) ){
            logger.error("ContestJson.getMyConetestDetail 参数异常，sId为空");
            return  Result.buildErrorResult(AlcsErrorCode.PARAM_EXCEPTION);
        }
        if(StringUtils.isBlank(cId)){
            logger.error("ContestJson.getMyConetestDetail 参数异常，cId为空");
            return  Result.buildErrorResult(AlcsErrorCode.PARAM_EXCEPTION);
        }

        try {
            contestId = Integer.parseInt(cId);
            MyContestDetailDto myContestDetail = contestService.getMyContestDetail(sId, contestId);
            return Result.buildSuccessResult(myContestDetail);
        } catch (Exception e) {
            logger.error("ContestJson.getMyConetestDetail 参数异常，cId格式错误，error{}",e);
            return  Result.buildErrorResult(AlcsErrorCode.PARAM_EXCEPTION);
        }

}


}
