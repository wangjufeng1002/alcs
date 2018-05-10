package xy.alcs.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xy.alcs.common.enums.AlcsErrorCode;
import xy.alcs.common.exception.BussinessException;
import xy.alcs.common.utils.Result;
import xy.alcs.dao.*;
import xy.alcs.domain.*;
import xy.alcs.dto.ContestDto;
import xy.alcs.dto.RaterDto;
import xy.alcs.manager.RaterManager;
import xy.alcs.service.RaterService;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 15:52 2018-03-25
 */
@Service("raterService")
public class RaterServiceImpl implements RaterService {

    @Resource
    private RaterMapper raterMapper;
    @Resource
    private RaterCompetitionMapper raterCompetitionMapper;
    @Resource
    private ContestMapper contestMapper;
    @Resource
    private RaterManager raterManager;
    @Resource
    private CommentMapper commentMapper;

    @Resource
    private WorksMapper worksMapper;


    @Value("${page.now}")
    private String pageNow;

    @Value("${page.limit}")
    private String rows;

    @Override
    public List<RaterDto> queryRaterList(Map<String, Object> queryMap, Long cid) {

        if (MapUtils.isEmpty(queryMap)) {
            throw BussinessException.asBussinessException(AlcsErrorCode.PARAM_ISNULL);
        }
        Map map = this.buildQueryMap(queryMap);
        List<Integer> raterIds = raterCompetitionMapper.selectAllotedRaterId(cid);
        if (raterIds.size() == 0) {
            map.put("rids", raterIds);
        }
        List<RaterDto> raterDtos = raterMapper.selectRaterByParam(map);
        return raterDtos;
    }

    @Override
    public Integer countRater(Map<String, Object> queryMap, Long cid) {
        //  Map map =  this.buildQueryMap(queryMap);
        return raterMapper.countTotalByParam(queryMap);
    }

    @Override
    public List<RaterDto> listRater(Map<String, Object> queryMap, Long cid) {
        List<RaterDto> raterDtos = raterMapper.selectRaterByParam(queryMap);
        RaterCompetitionExample example = new RaterCompetitionExample();
        example.createCriteria().andContestIdIn(Arrays.asList(cid));
        List<RaterCompetition> raterCompetitions = raterCompetitionMapper.selectByExample(example);
        for (RaterDto raterDto : raterDtos) {
            for (RaterCompetition raterCompetition : raterCompetitions) {
                if (raterDto.getRid().equals(raterCompetition.getRaterId())) {
                    raterDto.setAlloted(true);
                }
            }
        }
        return raterDtos;
    }

    private Boolean removeAlloted(RaterDto raterDto, List<RaterCompetition> raterCompetitions) {
        for (RaterCompetition raterCompetition : raterCompetitions) {
            if (raterCompetition.getRaterId().equals(raterDto.getRid())) {
                return true;
            }
        }
        return false;
    }

    private Map buildQueryMap(Map<String, Object> queryMap) {
        if (MapUtils.isEmpty(queryMap)) {
            throw BussinessException.asBussinessException(AlcsErrorCode.PARAM_ISNULL);
        }
        Map<String, Object> resultMap = new HashMap<>();

        for (Map.Entry item : queryMap.entrySet()) {
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
            } else if (item.getKey().toString().equals("ratName")) {
                if (item.getValue() == null || StringUtils.isEmpty((String) item.getValue())) {
                    resultMap.put("ratName", null);
                }
            } else if (item.getKey().toString().equals("rid")) {
                if (item.getValue() == null || StringUtils.isEmpty((String) item.getValue())) {
                    resultMap.put("rid", null);
                } else {
                    resultMap.put("rid", Integer.parseInt((String) item.getValue()));
                }
            }
        }
        if (resultMap.get("page") != null && queryMap.get("rows") != null) {
            Integer offset = ((Integer) resultMap.get("page") - 1) * (Integer) queryMap.get("rows");
            resultMap.put("offset", offset);
        }
        return resultMap;
    }

    @Override
    public Boolean addRaterForContest(Long cid, List<Integer> rids) {

        Boolean aBoolean = null;
        try {
            aBoolean = raterManager.batchInsertRaterContest(rids, cid);
        } catch (Exception e) {
            return false;
        }
        return aBoolean;
    }

    @Override
    public Rater queryRaterInfo(String raterAccount) {
        if (StringUtils.isBlank(raterAccount)) {
            throw BussinessException.asBussinessException(AlcsErrorCode.PARAM_EXCEPTION);
        }
        RaterExample raterExample = new RaterExample();
        raterExample.createCriteria().andRatAccountEqualTo(raterAccount);
        List<Rater> raterList = raterMapper.selectByExample(raterExample);
        if (CollectionUtils.isEmpty(raterList)) {
            throw BussinessException.asBussinessException(AlcsErrorCode.SYSTEM_ERROR);
        }
        return raterList.get(0);
    }

    @Override
    public Boolean approvalWork(Map<String, Object> approvalParam) {
        Map<String, Object> map = this.buildApprovalParam(approvalParam);
        Integer res = commentMapper.updateSoceAndContent(map);

        return res >= 1;
    }


    public Map<String, Object> buildApprovalParam(Map<String, Object> approvalParam) {
        Map<String, Object> resultMap = new HashMap();
        for (Map.Entry item : approvalParam.entrySet()) {
            if ("wid".equals(item.getKey())) {
                if (StringUtils.isBlank(item.getValue().toString())) {
                    resultMap.put("wid", null);
                } else {
                    resultMap.put("wid", item.getValue());
                }
            }
            if ("cid".equals(item.getKey())) {
                if (StringUtils.isBlank(item.getValue().toString())) {
                    resultMap.put("cid", null);
                } else {
                    resultMap.put("cid", item.getValue());
                }
            }
            if ("teamId".equals(item.getKey())) {
                if (StringUtils.isBlank(item.getValue().toString())) {
                    resultMap.put("teamId", null);
                } else {
                    resultMap.put("teamId", item.getValue());
                }
            }
            if ("score".equals(item.getKey())) {
                if (StringUtils.isBlank(item.getValue().toString())) {
                    resultMap.put("score", null);
                } else {
                    resultMap.put("score", item.getValue());
                }
            }
            if ("content".equals(item.getKey())) {
                if (StringUtils.isBlank(item.getValue().toString())) {
                    resultMap.put("content", null);
                } else {
                    resultMap.put("content", item.getValue());
                }
            }
        }
        return resultMap;
    }

    //管理评委信息所用 开始==============
    @Override
    public List<Rater> queryRaterList(Map<String, Object> queryMap) {
        if (MapUtils.isEmpty(queryMap)) {
            throw BussinessException.asBussinessException(AlcsErrorCode.PARAM_EXCEPTION);
        }
        Map<String, Object> map = this.buildQueryRaterListMap(queryMap);
        List<Rater> raterList = raterMapper.selectRaterList(map);
        return raterList;

    }

    @Override
    public Integer countRaterList(Map<String, Object> queryMap) {
        if (MapUtils.isEmpty(queryMap)) {
            throw BussinessException.asBussinessException(AlcsErrorCode.PARAM_EXCEPTION);
        }
        Map<String, Object> map = this.buildQueryRaterListMap(queryMap);
        return raterMapper.countRaterList(map);
    }

    @Override
    public Boolean updateRater(Rater rater) {
        if (rater == null) {
            throw BussinessException.asBussinessException(AlcsErrorCode.PARAM_EXCEPTION);
        }
        return raterMapper.updateByPrimaryKeySelective(rater) >= 1;
    }

    @Override
    public Result addRater(Rater rater) {
        if (rater == null) {
            throw BussinessException.asBussinessException(AlcsErrorCode.PARAM_EXCEPTION);
        }
        RaterExample raterExample = new RaterExample();
        raterExample.createCriteria().andRatAccountEqualTo(rater.getRatAccount());
        List<Rater> admins = raterMapper.selectByExample(raterExample);
        if (CollectionUtils.isNotEmpty(admins)) {
            return Result.buildErrorResult(AlcsErrorCode.USER_IS_EXIST);
        }
        int insert = raterMapper.insert(rater);
        if (insert >= 1) {
            return Result.buildSuccessResult();
        }
        return Result.buildErrorResult(AlcsErrorCode.SYSTEM_ERROR);
    }

    @Override
    public Boolean delRater(List<Integer> rids) {
        if (CollectionUtils.isEmpty(rids)) {
            throw BussinessException.asBussinessException(AlcsErrorCode.PARAM_EXCEPTION);
        }
        RaterExample raterExample = new RaterExample();
        raterExample.createCriteria().andRidIn(rids);
        return raterMapper.deleteByExample(raterExample) >= 1;
    }

    private Map<String, Object> buildQueryRaterListMap(Map<String, Object> queryMap) {
        Map<String, Object> resultMap = new HashMap<>();
        for (Map.Entry item : queryMap.entrySet()) {
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
            } else if (item.getKey().toString().equals("ratName")) {
                if (item.getValue() == null || StringUtils.isEmpty(item.getValue().toString())) {
                    resultMap.put("ratName", null);
                } else {
                    resultMap.put("ratName", (String) item.getValue());
                }
            } else if (item.getKey().toString().equals("ratAccount")) {
                if (item.getValue() == null || StringUtils.isEmpty(item.getValue().toString())) {
                    resultMap.put("ratAccount", null);
                } else {
                    resultMap.put("ratAccount", Integer.parseInt(item.getValue().toString()));
                }
            }
        }
        Integer offset = ((Integer) resultMap.get("page") - 1) * (Integer) resultMap.get("limit");
        resultMap.put("offset", offset);
        return resultMap;
    }
    //管理评委信息所用 结束==============


    @Override
    public Boolean changePassWord(String account,String password) {
        RaterExample raterExample = new RaterExample();
        raterExample.createCriteria().andRatAccountEqualTo(account);
        Rater rater  = new Rater();
        rater.setRatPassword(password);
        int update = raterMapper.updateByExampleSelective(rater, raterExample);
        return update >= 1;
    }
}
