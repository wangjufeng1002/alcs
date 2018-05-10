package xy.alcs.service.impl;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xy.alcs.common.enums.AlcsErrorCode;
import xy.alcs.common.exception.BussinessException;
import xy.alcs.dao.AwardsMapper;
import xy.alcs.dto.AwardsDto;
import xy.alcs.service.AwardsService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 17:11 2018-05-10
 */
@Service("awardsService")
public class AwardsServiceImpl implements AwardsService {

    @Resource
    private AwardsMapper awardsMapper;

    @Value("${page.now}")
    private String pageNow;

    @Value("${page.limit}")
    private String rows;

    @Override
    public List<AwardsDto> queryMyAwardsList(Map<String, Object> queryMap) {
        if (MapUtils.isEmpty(queryMap)) {
            throw BussinessException.asBussinessException(AlcsErrorCode.PARAM_EXCEPTION);
        }
        Map<String, Object> resultMap = this.buildQueryMap(queryMap);
        return awardsMapper.selectMyAwardsList(resultMap);
    }

    @Override
    public Integer countMyAwardsTotal(Map<String, Object> queryMap) {
        if (MapUtils.isEmpty(queryMap)) {
            throw BussinessException.asBussinessException(AlcsErrorCode.PARAM_EXCEPTION);
        }
        Map<String, Object> resultMap = this.buildQueryMap(queryMap);
        return awardsMapper.countMyAwardsTotal(resultMap);
    }

    private Map<String, Object> buildQueryMap(Map<String, Object> queryMap) {
        Map<String, Object> resultMap = new HashMap<>();
        for (Map.Entry item : queryMap.entrySet()) {
            if("studentId".equals(item.getKey())){
                if(item.getValue() == null || StringUtils.isBlank(item.getValue().toString())){
                    resultMap.put("studentId",null);
                }else{
                    resultMap.put("studentId",item.getValue());
                }
            }
            if (item.getKey().toString().equals("page")) {
                if (item.getValue() == null) {
                    resultMap.put("page", Integer.parseInt(this.pageNow));
                }
                resultMap.put("page", queryMap.get("page"));
            } else if (item.getKey().toString().equals("rows")) {
                if (item.getValue() == null) {
                    resultMap.put("limit", Integer.parseInt(this.rows));
                }
                resultMap.put("limit", queryMap.get("rows"));
            }
        }
        Integer offset = ((Integer) resultMap.get("page") - 1) * (Integer) resultMap.get("limit");
        resultMap.put("offset", offset);
        return resultMap;
    }
}
