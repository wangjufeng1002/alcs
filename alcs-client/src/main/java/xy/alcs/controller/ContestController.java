package xy.alcs.controller;

import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xy.alcs.common.entity.PageData;
import xy.alcs.common.enums.AlcsErrorCode;
import xy.alcs.common.enums.EnrollType;
import xy.alcs.common.enums.TeamCaptainEnum;
import xy.alcs.common.utils.Result;
import xy.alcs.domain.StudentCompetition;
import xy.alcs.dto.ContestDto;
import xy.alcs.dto.MyContestDetailDto;
import xy.alcs.dto.MyContestWorkDto;
import xy.alcs.dto.StudentDto;
import xy.alcs.service.ContestService;
import xy.alcs.service.StudentService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:ju
 * @Description: 竞赛相关json接口
 * @Date:Create in 21:33 2017-12-06
 */
@Controller
public class ContestController {

    Logger logger = LoggerFactory.getLogger(ContestController.class);
    @Autowired
    private ContestService contestService;
    @Autowired
    private StudentService studentService;


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
     * @param rows 偏移量
     * @return 竞赛信息集合
     */
    @RequestMapping(value = "/contest/list")
    @ResponseBody
    public PageData<ContestDto> listContest(Integer page, Integer rows, String queryParam) {
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

        List<ContestDto> contests = contestService.listContest(map);
        Integer total = contestService.countTotal(map);
        PageData<ContestDto> pageData = new PageData();
        pageData.setRows(contests);
        pageData.setTotal(total);
        pageData.setPage(page);
        pageData.setPageSize(rows);
        return pageData;

    }

    @RequestMapping(value = "/contest/getByid")
    @ResponseBody
    public Result<ContestDto> getContestById(Integer cid) {
        ContestDto dto = contestService.queryContestDtoById(cid);
        if (dto == null) {
            return Result.buildErrorResult(AlcsErrorCode.CONETST_NOT_EXIST);
        }
        return Result.buildSuccessResult(AlcsErrorCode.SUCCESS, dto);
    }

    /**
     * @param request       请求体
     * @param page          当前页
     * @param rows          页大小
     * @param contestStatus 状态 0正在进行，1已结束
     * @return
     */
    @RequestMapping(value = "/contest/listMyContest")
    @ResponseBody
    //TODO 修改分页查询
    public PageData<ContestDto> listContestUnderwayOrStop(HttpServletRequest request, Integer page, Integer rows, Integer contestStatus) {
        String stuId = (String) request.getSession().getAttribute("stu_id");
        if (stuId == null) {
            Result.buildErrorResult(AlcsErrorCode.PARAM_EXCEPTION);
        }
        if (rows == null || rows.intValue() <= 0) {
            rows = Integer.parseInt(PAGELIMIT);
        }
        if (page == null || page.intValue() < 0) {
            page = Integer.parseInt(PAGENOW);
        }
        if (contestStatus == null) {
            contestStatus = Integer.parseInt(CONTESTSTATUS);
        }
        Integer total = contestService.countMyContestByParam(stuId, page, rows, contestStatus);
        List<ContestDto> contestDtos = contestService.listMyContestByParam(stuId, page, rows, contestStatus);
        PageData<ContestDto> pageData = new PageData();
        pageData.setRows(contestDtos);
        pageData.setPage(page);
        pageData.setPageSize(rows);
        pageData.setTotal(total);
        return pageData;

    }

    /**
     * @param sId 学生Id
     * @param cId 竞赛Id
     * @return
     */
    @RequestMapping(value = "/contest/getMyContestDetail")
    @ResponseBody
    public Result<MyContestDetailDto> getMyConetestDetail(String sId, String cId) {
        Integer contestId = null;
        if (StringUtils.isBlank(sId)) {
            logger.error("ContestJson.getMyConetestDetail 参数异常，sId为空");
            return Result.buildErrorResult(AlcsErrorCode.PARAM_EXCEPTION);
        }
        if (StringUtils.isBlank(cId)) {
            logger.error("ContestJson.getMyConetestDetail 参数异常，cId为空");
            return Result.buildErrorResult(AlcsErrorCode.PARAM_EXCEPTION);
        }

        try {
            contestId = Integer.parseInt(cId);
            MyContestDetailDto myContestDetail = contestService.getMyContestDetail(sId, contestId);
            return Result.buildSuccessResult(myContestDetail);
        } catch (Exception e) {
            logger.error("ContestJson.getMyConetestDetail 参数异常，cId格式错误，error{}", e);
            return Result.buildErrorResult(AlcsErrorCode.PARAM_EXCEPTION);
        }
    }

    /**
     * 报名controller
     *
     * @param request 请求体
     * @param cId     竞赛id
     *
     * @return
     */

    @RequestMapping("/contest/enroll")
    @ResponseBody
    public Result enrollContest(HttpServletRequest request, Integer cId,String leaderId,Integer nTeam) {
        String stuId = (String) request.getSession().getAttribute("stu_id");
        if (stuId == null) {
            Result.buildErrorResult(AlcsErrorCode.NOT_LOGIN);
        }
        if (cId == null) {
            Result.buildErrorResult(AlcsErrorCode.PARAM_EXCEPTION);
        }


        //个人参赛
        if(nTeam.equals(EnrollType.ONLY.getCode())){
            return contestService.enrollContest(new Long(cId), stuId,null);
        }
        //小组参赛
        if(nTeam.equals(EnrollType.TEAM.getCode())){
            if(StringUtils.isNotEmpty(leaderId)){
                return contestService.enrollContest(new Long(cId), stuId,leaderId);
            }else {
                return Result.buildErrorResult(AlcsErrorCode.ENROLL_FAIL);
            }

        }
        else{
            return Result.buildErrorResult(AlcsErrorCode.ENROLL_FAIL);
        }

    }

    @RequestMapping("/contest/works")
    @ResponseBody
    public PageData<MyContestWorkDto> listMyContestWork(HttpServletRequest request, Integer workCommit, Integer page, Integer rows) {
        String stuId = (String) request.getSession().getAttribute("stu_id");
        if (stuId == null) {
            Result.buildErrorResult(AlcsErrorCode.NOT_LOGIN);
        }
        if (workCommit == null) {
            Result.buildErrorResult(AlcsErrorCode.PARAM_EXCEPTION);
        }
        Map queryMap = new HashMap();
        queryMap.put("page", page);
        queryMap.put("rows", rows);
        queryMap.put("stuId", stuId);
        queryMap.put("workCommit", workCommit);

        PageData<MyContestWorkDto> pageData = new PageData();
        pageData.setRows(contestService.listMyContestWork(queryMap));
        pageData.setPage(page);
        pageData.setPageSize(rows);
        pageData.setTotal(contestService.countMyContestWork(queryMap));
        return pageData;
    }


    @ResponseBody
    @RequestMapping("/contest/rater/list")
    public PageData<ContestDto> listRaterConetst(HttpServletRequest request, Integer page, Integer rows, String queryParam) {
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

        List<ContestDto> contests = contestService.listRaterContest(map);
        Integer total = contestService.countRateContestTotal(map);
        PageData<ContestDto> pageData = new PageData();
        pageData.setRows(contests);
        pageData.setTotal(total);
        pageData.setPage(page);
        pageData.setPageSize(rows);
        return pageData;
    }

    @ResponseBody
    @RequestMapping("/contest/cancelEnroller")
    public Result cancelEnroll(HttpServletRequest request, String cId) {
        String stuId = (String) request.getSession().getAttribute("stu_id");
        Boolean res = studentService.canelEnroll(stuId, Long.parseLong(cId));
        if (res) {
            return Result.buildSuccessResult();
        } else {
            return Result.buildErrorResult(AlcsErrorCode.CANCEL_FAIL);
        }
    }


}
