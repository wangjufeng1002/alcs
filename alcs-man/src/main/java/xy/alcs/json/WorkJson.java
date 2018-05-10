package xy.alcs.json;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xy.alcs.common.entity.PageData;
import xy.alcs.common.enums.AlcsErrorCode;
import xy.alcs.common.utils.Result;
import xy.alcs.dto.WorkListDto;
import xy.alcs.service.WorksService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 20:54 2018-05-06
 */
@Controller
public class WorkJson {
    @Resource
    private WorksService worksService;
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

    @RequestMapping("/work/list")
    @ResponseBody
    public PageData<WorkListDto> getWorkList(Integer page, Integer rows, String queryParam) {
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
        List<WorkListDto> workListDtos = worksService.listWork(map);
        Integer total = worksService.countWork(map);
        PageData<WorkListDto> pageData = new PageData();
        pageData.setRows(workListDtos);
        pageData.setTotal(total);
        pageData.setPage(page);
        return pageData;
    }

    @ResponseBody
    @RequestMapping("/work/delete")
    public Result deleteWork(String wIds) {
        if (StringUtils.isBlank(wIds)) {
            return Result.buildErrorResult(AlcsErrorCode.PARAM_ISNULL);
        }
        List<Long> wids = new ArrayList<>();
        List<String> widList = JSONObject.parseObject(wIds, List.class);
        for (String id : widList) {
            wids.add(Long.parseLong(id));
        }
        if (!worksService.deleteWork(wids)) {
            return Result.buildCustomizeResult(AlcsErrorCode.SYSTEM_ERROR.getCode(), "删除失败，请重试");
        } else {
            return Result.buildCustomizeResult(AlcsErrorCode.SUCCESS.getCode(), "删除成功");
        }
    }
    @ResponseBody
    @RequestMapping("/work/scoreList")
    public PageData<WorkListDto> getWorkAndScore(Integer page, Integer rows, Long cid){
        Map<String, Object> map = new HashMap<>();
        if (rows == null || rows.intValue() <= 0) {
            rows = Integer.parseInt(PAGELIMIT);
        }
        if (page == null || page.intValue() < 0) {
            page = Integer.parseInt(PAGENOW);
        }
        map.put("rows", rows);
        map.put("page", page);
        map.put("cid",cid);
        List<WorkListDto> workListDtos = worksService.queryWorkDtoByCid(map);
        Integer total = worksService.countWorkDtoByCid(map);
        PageData<WorkListDto> pageData = new PageData();
        pageData.setRows(workListDtos);
        pageData.setTotal(total);
        pageData.setPage(page);
        return pageData;
    }
}
