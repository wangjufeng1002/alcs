package xy.alcs.service.impl;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xy.alcs.common.enums.AlcsErrorCode;
import xy.alcs.common.exception.BussinessException;
import xy.alcs.dao.RaterMapper;
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
 * @Date:Create in 15:52 2018-03-25
 */
@Service("raterService")
public class RaterServiceImpl implements RaterService {

    @Resource
    private RaterMapper raterMapper;



    @Value("${page.now}")
    private String pageNow;

    @Value("${page.limit}")
    private String rows;


    @Override
    public Boolean addRater(Rater rater) {
        int insert = raterMapper.insert(rater);
        return insert > 0;
    }


    @Override
    public List<RaterDto> listRater(Map<String,Object> queryMap) {

        if(MapUtils.isEmpty(queryMap)){
            throw BussinessException.asBussinessException(AlcsErrorCode.PARAM_ISNULL);
        }
        Map map = this.buildQueryMap(queryMap);
        List<RaterDto> raterDtos = raterMapper.selectRaterByParam(map);
        return raterDtos;
    }

    @Override
    public Integer countRater(Map<String,Object> queryMap) {
        if(MapUtils.isEmpty(queryMap)){
            throw BussinessException.asBussinessException(AlcsErrorCode.PARAM_ISNULL);
        }
        Map map =  this.buildQueryMap(queryMap);
        return raterMapper.countTotalByParam(map);
    }



    private Map buildQueryMap(Map<String, Object> queryMap) {
        Map<String, Object>  resultMap = new HashMap<>();

        for(Map.Entry item : queryMap.entrySet()){
            if(item.getKey().toString().equals("page")){
                if(item.getValue() == null ){
                    resultMap.put("page",Integer.parseInt(this.pageNow));
                }
                resultMap.put("page",queryMap.get("page"));
            }
            else if(item.getKey().toString().equals("rows")){
                if(item.getValue() == null){
                    resultMap.put("limit",Integer.parseInt(this.rows));
                }
                resultMap.put("limit", queryMap.get("rows"));
            }
            else if(item.getKey().toString().equals("ratName")){
                if(item.getValue() == null || StringUtils.isEmpty((String) item.getValue())){
                    resultMap.put("ratName",null);
                }
            }
            else if(item.getKey().toString().equals("rid")){
                if(item.getValue() == null || StringUtils.isEmpty((String) item.getValue())){
                    resultMap.put("rid",null);
                }else{
                    resultMap.put("rid",Integer.parseInt((String) item.getValue()));
                }
            }
        }
        Integer offset = ((Integer) resultMap.get("page") - 1) * (Integer) queryMap.get("rows");
        resultMap.put("offset",offset);
        return resultMap;

    }
}
