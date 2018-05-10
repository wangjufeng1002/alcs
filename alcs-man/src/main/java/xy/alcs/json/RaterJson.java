package xy.alcs.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xy.alcs.common.entity.PageData;
import xy.alcs.common.enums.AlcsErrorCode;
import xy.alcs.common.errcode.ErrorCode;
import xy.alcs.common.utils.Result;
import xy.alcs.dao.RaterCompetitionMapper;
import xy.alcs.domain.Admin;
import xy.alcs.domain.Rater;
import xy.alcs.dto.ContestDto;
import xy.alcs.dto.RaterDto;
import xy.alcs.service.RaterService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 17:28 2018-03-25
 */
@Controller
public class RaterJson {
    @Resource
    private RaterService raterService;


    @Value("${page.limit}")
    private String PAGELIMIT;

    /**
     * 默认查询数据的起始下标
     */
    @Value("${page.offset}")
    private String PAGEOFFSET;

    @Value("${page.now}")
    private String PAGENOW;

    @RequestMapping("/rater/listByCid")
    @ResponseBody
    public PageData<RaterDto> listRater(String queryParam,Long cid){
        Map<String,Object> map = new HashMap<>();

        if(!StringUtils.isEmpty(queryParam)){
            map = JSONObject.parseObject(queryParam,Map.class);
        }
        List<RaterDto> raterDtos = raterService.listRater(map,cid);
        Integer total = raterService.countRater(map,cid);
        PageData<RaterDto> pageData = new PageData();
        pageData.setRows(raterDtos);
        pageData.setTotal(total);
        return pageData;
    }
    @RequestMapping("/rater/addRaterForContest")
    @ResponseBody
    public Result addRaterForContest(Long cid,String rids){
        if(cid == null || StringUtils.isEmpty(rids)){
            return Result.buildErrorResult(AlcsErrorCode.PARAM_EXCEPTION);
        }
        List<Integer> ridList = JSONObject.parseObject(rids, List.class);
        Boolean aBoolean = raterService.addRaterForContest(cid, ridList);
        if(aBoolean){
            return Result.buildSuccessResult(AlcsErrorCode.SUCCESS);
        }else{
            return Result.buildErrorResult(AlcsErrorCode.SYSTEM_ERROR);
        }

    }


    //管理 评委信息所用
    @ResponseBody
    @RequestMapping(value = "/rater/list")
    public PageData<Rater> getAdminList(Integer page, Integer rows, String queryParam) {
        Map<String, Object> map = new HashMap<>();
        if (rows == null || rows.intValue() <= 0) {
            rows = Integer.parseInt(PAGELIMIT);
        }
        if (page == null || page.intValue() < 0) {
            page = Integer.parseInt(PAGENOW);
        }
        if (!StringUtils.isEmpty(queryParam)) {
            map = JSONObject.parseObject(queryParam, Map.class);
        }
        map.put("rows", rows);
        map.put("page", page);
        List<Rater> raterList = raterService.queryRaterList(map);
        Integer total = raterService.countRaterList(map);
        PageData<Rater> pageData = new PageData();
        pageData.setRows(raterList);
        pageData.setTotal(total);
        pageData.setPage(page);
        return pageData;
    }

    @ResponseBody
    @RequestMapping(value = "/rater/add")
    public Result addRater(Rater rater) {
        if (rater == null) {
            return Result.buildErrorResult(AlcsErrorCode.PARAM_ISNULL);
        }
        Boolean result = this.verifyRater(rater);
        if (result) {
            return raterService.addRater(rater);
        }
        return Result.buildErrorResult(AlcsErrorCode.PARAM_ISNULL);

    }

    @ResponseBody
    @RequestMapping(value = "/rater/edit")
    public Result updateAdmin(Rater rater) {
        if (rater == null) {
            return Result.buildErrorResult(AlcsErrorCode.PARAM_ISNULL);
        }
        Boolean aBoolean = raterService.updateRater(rater);
        if (aBoolean) {
            return Result.buildSuccessResult();
        }
        return Result.buildErrorResult(AlcsErrorCode.SYSTEM_ERROR);
    }

    @ResponseBody
    @RequestMapping(value = "/rater/del")
    private Result delAdmin(String rids) {
        if (StringUtils.isBlank(rids)) {
            return Result.buildErrorResult(AlcsErrorCode.PARAM_ISNULL);
        }
        List ridList = JSONObject.parseObject(rids, List.class);
        Boolean res = raterService.delRater(ridList);
        if (res) {
            return Result.buildSuccessResult();
        }
        return Result.buildErrorResult(AlcsErrorCode.SYSTEM_ERROR);
    }

    private Boolean verifyRater(Rater rater) {
        if (StringUtils.isBlank(rater.getRatAccount())) {
            return false;
        }
        if (StringUtils.isBlank(rater.getRatName())) {
            return false;
        }
        if (StringUtils.isBlank(rater.getRatPassword())) {
            return false;
        }
        return true;
    }
}
