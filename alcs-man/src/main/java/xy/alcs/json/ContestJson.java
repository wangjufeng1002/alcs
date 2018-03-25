package xy.alcs.json;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import xy.alcs.common.entity.PageData;
import xy.alcs.common.enums.AlcsErrorCode;
import xy.alcs.common.utils.Result;
import xy.alcs.domain.Contest;
import xy.alcs.dto.ContestDateDto;
import xy.alcs.dto.ContestDto;
import xy.alcs.service.ContestService;

import java.util.*;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 20:22 2018-03-17
 */
@Controller
public class ContestJson {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 默认的每页显示的数据
     */
    @Value("${page.limit}")
    private String PAGELIMIT;

    /**
     * 默认查询数据的起始下标
     */
    @Value("${page.offset}")
    private String PAGEOFFSET;

    @Value("${page.now}")
    private String PAGENOW;


    @Autowired
    private ContestService contestService;

    /**
     * @param page 起始下标
     * @param rows   偏移量
     * @return 竞赛信息集合
     */
    @RequestMapping(value = "/contest/list")
    @ResponseBody
    public PageData<ContestDto> listContest(Integer page, Integer rows,String queryParam) {
        Map<String,Object> map = new HashMap<>();
        if (rows == null || rows.intValue() <= 0) {
            rows = Integer.parseInt(PAGELIMIT);
        }
        if (page == null || page.intValue() < 0) {
            page = Integer.parseInt(PAGENOW);
        }
        if(!StringUtils.isEmpty(queryParam)){
            map = JSONObject.parseObject(queryParam,Map.class);
        }
        map.put("rows",rows);
        map.put("page",page);
        List<ContestDto> contests = contestService.listContest(map);
        Integer total = contestService.countTotal(map);
        PageData<ContestDto> pageData = new PageData();
        pageData.setRows(contests);
        pageData.setTotal(total);
        pageData.setPage(page);
        return pageData;

    }

    @RequestMapping(value = "/contest/addOrUpdate",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Result addContest(Contest contest, ContestDateDto contestDateDto) {
        if (contest == null || contestDateDto == null) {
            return Result.buildErrorResult(AlcsErrorCode.PARAM_ISNULL);
        }
        Result verify = this.verifyContest(contest, contestDateDto);
        if (AlcsErrorCode.SUCCESS.getCode().equals(verify.getCode())) {
            return contestService.addOrUpdateContest(contestService.changeContestDateFormat(contest, contestDateDto));
        } else {
            return verify;
        }
    }
    @RequestMapping(value = "/contest/delete",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Result delContest(String cIds){
        try {
            List<String> list = JSONObject.parseObject(cIds, List.class);
            List<Long> cids = new ArrayList<>();
            for(String id : list){
                cids.add(Long.parseLong(id));
            }
            if(contestService.delContestByIds(cids)){
                return Result.buildSuccessResult(AlcsErrorCode.SUCCESS);
            }
            return Result.buildSuccessResult(AlcsErrorCode.SYSTEM_ERROR);
        } catch (NumberFormatException e) {
            logger.error("#ContestJson delContest happend error:{}",e);
            return Result.buildSuccessResult(AlcsErrorCode.SYSTEM_ERROR);
        }
    }















    /**
     * 校验参数
     * @param contest
     * @param contestDateDto
     * @return
     */
    public Result verifyContest(Contest contest, ContestDateDto contestDateDto) {
        if (StringUtils.isBlank(contest.getTitle()) || StringUtils.isBlank(contest.getSummary())) {
            return Result.buildErrorResult(AlcsErrorCode.PARAM_ISNULL);
        }
        if (StringUtils.isEmpty(contestDateDto.getStartDateStr()) || StringUtils.isEmpty(contestDateDto.getEndDateStr())) {
            return Result.buildErrorResult(AlcsErrorCode.PARAM_ISNULL);
        }
        if (StringUtils.isEmpty(contestDateDto.getEnrollEndDateStr()) || StringUtils.isEmpty(contestDateDto.getEnrollStartDateStr())) {
            return Result.buildErrorResult(AlcsErrorCode.PARAM_ISNULL);
        }
        if (StringUtils.isEmpty(contestDateDto.getScoreStartDateStr()) || StringUtils.isEmpty(contestDateDto.getScoreEndDateStr())) {
            return Result.buildErrorResult(AlcsErrorCode.PARAM_ISNULL);
        }
        if (StringUtils.isEmpty(contestDateDto.getWorksEndDateStr())) {
            return Result.buildErrorResult(AlcsErrorCode.PARAM_ISNULL);
        }
        return Result.buildSuccessResult(AlcsErrorCode.SUCCESS);
    }
}
