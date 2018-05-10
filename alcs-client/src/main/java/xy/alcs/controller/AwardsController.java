package xy.alcs.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xy.alcs.common.entity.PageData;
import xy.alcs.dto.AwardsDto;
import xy.alcs.dto.ContestDto;
import xy.alcs.service.AwardsService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 18:44 2018-05-10
 */
@Controller
public class AwardsController {
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
    private AwardsService awardsService;

    @ResponseBody
    @RequestMapping(value = "/award/myAwards")
    public PageData<AwardsDto> getMyAwardsList(HttpServletRequest request, Integer page, Integer rows, String queryParam) {

        String stuId = (String) request.getSession().getAttribute("stu_id");
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
        map.put("studentId", stuId);
        map.put("page", page);
        map.put("rows", rows);
        List<AwardsDto> awardsDtos = awardsService.queryMyAwardsList(map);
        Integer total = awardsService.countMyAwardsTotal(map);
        PageData<AwardsDto> pageData = new PageData();
        pageData.setRows(awardsDtos);
        pageData.setTotal(total);
        pageData.setPage(page);
        pageData.setPageSize(rows);
        return pageData;
    }

}
