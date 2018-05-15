package xy.alcs.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xy.alcs.common.entity.PageData;
import xy.alcs.common.enums.AlcsErrorCode;
import xy.alcs.common.exception.BussinessException;
import xy.alcs.common.utils.Result;
import xy.alcs.dto.ContestDto;
import xy.alcs.dto.RaterWorkDto;
import xy.alcs.dto.WorkListDto;
import xy.alcs.service.RaterService;
import xy.alcs.service.WorksService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 16:48 2018-05-07
 */
@Controller
public class RaterController {
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

    @Resource
    private WorksService worksService;
    @Resource
    private RaterService raterService;

    @RequestMapping(value = "/rater/works")
    @ResponseBody
    public PageData<RaterWorkDto> getWorkList(HttpServletRequest request, Integer page, Integer rows, String queryParam) {
        String ratId = (String) request.getSession().getAttribute("rat_id");
        Map<String, Object> map = new HashMap<>();
        if (page == null || page.intValue() <= 0) {
            page = Integer.parseInt(PAGENOW);
        }
        if (rows == null || rows.intValue() < 0) {
            rows = Integer.parseInt(PAGELIMIT);
        }
        if (!StringUtils.isEmpty(queryParam)) {
            map = JSONObject.parseObject(queryParam, Map.class);
        }
        map.put("page", page);
        map.put("rows", rows);
        map.put("raterAccount", ratId);

        List<RaterWorkDto> raterWorkDtos = worksService.listRaterWorks(map);
        Integer total = worksService.countRaterWorks(map);
        PageData<RaterWorkDto> pageData = new PageData();
        pageData.setRows(raterWorkDtos);
        pageData.setTotal(total);
        pageData.setPage(page);
        pageData.setPageSize(rows);
        return pageData;
    }

    @ResponseBody
    @RequestMapping("/rater/workDetail")
    public Result<RaterWorkDto> getRaterWorkDetail(HttpServletRequest request, Long wid) {
        String ratId = (String) request.getSession().getAttribute("rat_id");

        Map<String, Object> map = new HashMap<>();
        map.put("raterAccount", ratId);
        map.put("wid", wid);
        RaterWorkDto raterWorkDto = worksService.queryRaterWorkDetail(map);
        return Result.buildSuccessResult(raterWorkDto);
    }

    @ResponseBody
    @RequestMapping("/rater/approval")
    public Result approvalWork(HttpServletRequest request, String approvalPram) {
        if (StringUtils.isBlank(approvalPram)) {
            throw BussinessException.asBussinessException(AlcsErrorCode.PARAM_EXCEPTION);
        }
        String ratId = (String) request.getSession().getAttribute("rat_id");
        Map map = JSONObject.parseObject(approvalPram, Map.class);
        map.put("raterId", ratId);
        Boolean res = raterService.approvalWork(map);
        if (res) {
            return Result.buildSuccessResult();
        }
        return Result.buildSuccessResult(AlcsErrorCode.SYSTEM_ERROR);
    }

}
