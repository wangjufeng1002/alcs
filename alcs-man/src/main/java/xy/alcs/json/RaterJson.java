package xy.alcs.json;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xy.alcs.common.entity.PageData;
import xy.alcs.common.utils.Result;
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
    public PageData<RaterDto> listRater(Integer page,Integer rows,String queryParam,Long cid){
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
        List<RaterDto> raterDtos = raterService.listRater(map);
        Integer total = raterService.countRater(map);
        PageData<RaterDto> pageData = new PageData();
        pageData.setRows(raterDtos);
        pageData.setTotal(total);
        return pageData;
    }
}
