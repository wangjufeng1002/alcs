package xy.alcs.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sun.xml.internal.bind.v2.model.core.MaybeElement;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xy.alcs.common.enums.AlcsErrorCode;
import xy.alcs.common.enums.TeamCaptainEnum;
import xy.alcs.common.enums.WorkCommitEnum;
import xy.alcs.common.exception.BussinessException;
import xy.alcs.common.utils.Result;
import xy.alcs.common.utils.Utils;
import xy.alcs.dao.ContestMapper;
import xy.alcs.dao.StudentCompetitionMapper;
import xy.alcs.dao.StudentMapper;
import xy.alcs.domain.*;
import xy.alcs.dto.*;
import xy.alcs.manager.ContestManager;
import xy.alcs.service.ContestService;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 1:12 2017-12-07
 */
@Service("contestService")
public class ContestServiceImpl implements ContestService {
    private static Logger logger = LoggerFactory.getLogger(ContestServiceImpl.class);


    @Resource
    private ContestMapper contestMapper;

    @Resource
    private StudentCompetitionMapper studentCompetitionMapper;
    @Resource
    private StudentMapper studentMapper;

    @Resource
    private ContestManager contestManager;

    @Value("${page.now}")
    private String pageNow;

    @Value("${page.limit}")
    private String rows;


    public List<ContestDto> listMyContestByParam(String sId, Integer page, Integer rows, Integer status) {
        Map queryParamMap = new HashMap();
        queryParamMap.put("sId", sId);
        queryParamMap.put("page", page);
        queryParamMap.put("rows", rows);
        queryParamMap.put("contestStatus", status);
        this.buildQueryMap(queryParamMap);
        return contestMapper.selectMyContestByParam(queryParamMap);
    }

    @Override
    public Integer countMyContestByParam(String sId, Integer page, Integer rows, Integer status) {
        Map queryParamMap = new HashMap();
        queryParamMap.put("sId", sId);
        queryParamMap.put("page", page);
        queryParamMap.put("rows", rows);
        queryParamMap.put("contestStatus", status);
        this.buildQueryMap(queryParamMap);
        return contestMapper.countMyContestByParam(queryParamMap);
    }

    @Override
    public List<ContestDto> listContest(Map<String, Object> queryMap) {

        if (MapUtils.isEmpty(queryMap)) {
            throw BussinessException.asBussinessException(AlcsErrorCode.PARAM_ISNULL);
        }
        Map map = this.buildQueryMap(queryMap);
        List<ContestDto> contests = contestMapper.selectContest(map);

        for (ContestDto dto : contests) {
            if (!StringUtils.isEmpty(dto.getExtendJson())) {
                dto.setRaters(JSONObject.parseArray(dto.getExtendJson(), RaterDto.class));
                dto.setRaterSize(dto.getRaters().size());
            } else {
                dto.setRaterSize(0);
                dto.setRaters(null);
            }
        }
        return contests;
    }

    @Override
    public Integer countTotal(Map<String, Object> queryMap) {
        if (MapUtils.isEmpty(queryMap)) {
            throw BussinessException.asBussinessException(AlcsErrorCode.PARAM_ISNULL);
        }
        Map map = this.buildQueryMap(queryMap);
        return contestMapper.countTotal(map);
    }

    @Override
    public MyContestDetailDto getMyContestDetail(String sId, Integer contestId) {
        MyContestDetailDto myContestDetailDto = studentCompetitionMapper.selectContestBySidAndCid(sId, contestId);
        String teamId = myContestDetailDto.getTeamId();

        StudentCompetitionExample example = new StudentCompetitionExample();
        example.createCriteria().andTeamIdEqualTo(teamId);
        List<StudentCompetition> studentCompetitions = studentCompetitionMapper.selectByExample(example);

        List<String> teamIds = new ArrayList<>();
        studentCompetitions.forEach(stu -> teamIds.add(stu.getStudentId()));
        myContestDetailDto.setTeammate(teamIds);
        return myContestDetailDto;
    }

    @Override
    public Result addOrUpdateContest(Contest contest) {
        try {
            if (contest.getCid() == null) {
                contestMapper.insert(contest);
            } else {
                contestMapper.updateByPrimaryKey(contest);
            }

        } catch (Exception e) {
            logger.error("#ContestServiceImpl addContest hadppend exception,error:{}", e);
            e.printStackTrace();
            throw BussinessException.asBussinessException(AlcsErrorCode.SYSTEM_ERROR);
        }
        return Result.buildSuccessResult(AlcsErrorCode.SUCCESS);
    }

    @Override
    public Contest changeContestDateFormat(Contest contest, ContestDateDto contestDateDto) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            contest.setStartDate(dateFormat.parse(contestDateDto.getStartDateStr()));
            contest.setEndDate(dateFormat.parse(contestDateDto.getEndDateStr()));
            contest.setEnrollStartDate(dateFormat.parse(contestDateDto.getEnrollStartDateStr()));
            contest.setEnrollEndDate(dateFormat.parse(contestDateDto.getEnrollEndDateStr()));
            contest.setScoreStartDate(dateFormat.parse(contestDateDto.getScoreStartDateStr()));
            contest.setScoreEndDate(dateFormat.parse(contestDateDto.getScoreEndDateStr()));
            contest.setWorksEndDate(dateFormat.parse(contestDateDto.getWorksEndDateStr()));
        } catch (Exception e) {
            logger.error("#ContestServiceImpl changeContestDateFormat hadppend exception,error:{}", e);
            throw BussinessException.asBussinessException(AlcsErrorCode.SYSTEM_ERROR);
        }
        return contest;
    }

    @Override
    public Boolean delContestByIds(List<Long> cIds) {
        if (CollectionUtils.isEmpty(cIds)) {
            logger.error("#ContestServiceImpl delContestByIds param  cIds is empty");
            throw BussinessException.asBussinessException(AlcsErrorCode.PARAM_ISNULL);
        }
        return contestManager.batchDelete(cIds);
    }

    @Override
    public ContestDto queryContestDtoById(Integer cid) {
        if (cid == null) {
            logger.error("#ContestServiceImpl queryContestDtoById param  cid is empty");
        }
        return contestMapper.selectContestDtoById(cid);
    }

    @Override
    public List<MyContestWorkDto> listMyContestWork(Map<String, Object> queryMap) {
        return studentCompetitionMapper.selectContestByWorkCommitStatus(this.buildQueryMap(queryMap));
    }

    @Override
    public Integer countMyContestWork(Map<String, Object> queryMap) {
        return studentCompetitionMapper.countContestByWorkCommitStatus(queryMap);
    }

    @Override
    public Result enrollContest(Long cId, String sId) {
        try {
            if (StringUtils.isBlank(sId) || cId == null) {
                return Result.buildErrorResult(AlcsErrorCode.PARAM_EXCEPTION);
            }
            Contest contest = contestMapper.selectByPrimaryKey(cId);

            StudentExample studentExample = new StudentExample();
            studentExample.createCriteria().andStuIdEqualTo(sId);
            List<Student> students = studentMapper.selectByExample(studentExample);
            if (contest == null) {
                return Result.buildErrorResult(AlcsErrorCode.CONETST_NOT_EXIST);
            }
            if (CollectionUtils.isEmpty(students)) {
                return Result.buildErrorResult(AlcsErrorCode.USER_NOT_EXIST);
            }
            StudentCompetition studentCompetition = new StudentCompetition();
            studentCompetition.setStudentId(sId);
            studentCompetition.setContestId(cId);
            studentCompetition.setTeamId(Utils.getUUID());
            studentCompetition.setStudentN(TeamCaptainEnum.IS_CAPTAIN.getCode());
            studentCompetition.setTimestamp(new Date());
            studentCompetition.setWorkcommit(WorkCommitEnum.NOT_SAVE_COMIIT.getCode());

            if (studentCompetitionMapper.insert(studentCompetition) > 0) {
                return Result.buildSuccessResult(AlcsErrorCode.SUCCESS);
            } else {
                return Result.buildSuccessResult(AlcsErrorCode.ENROLL_FAIL);
            }
        } catch (Exception e) {
            return Result.buildErrorResult(AlcsErrorCode.DAO_EXCEPTION);
        }


    }

    @Override
    public List<ContestDto> listRaterContest(Map<String, Object> queryMap) {
        if (MapUtils.isEmpty(queryMap)) {
            throw BussinessException.asBussinessException(AlcsErrorCode.PARAM_EXCEPTION);
        }
        Map map = this.buildQueryMap(queryMap);
        return contestMapper.selectRaterContest(map);

    }

    @Override
    public Integer countRateContestTotal(Map<String, Object> queryMap) {
        if (MapUtils.isEmpty(queryMap)) {
            throw BussinessException.asBussinessException(AlcsErrorCode.PARAM_EXCEPTION);
        }
        return contestMapper.countRaterContest(this.buildQueryMap(queryMap));
    }

    private Map buildQueryMap(Map<String, Object> queryMap) {
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
            } else if (item.getKey().toString().equals("title")) {
                if (item.getValue() == null || StringUtils.isEmpty((String) item.getValue())) {
                    resultMap.put("title", null);
                } else {
                    resultMap.put("title", (String) item.getValue());
                }
            } else if (item.getKey().toString().equals("cid")) {
                if (item.getValue() == null || StringUtils.isEmpty((String) item.getValue())) {
                    resultMap.put("cid", null);
                } else {
                    resultMap.put("cid", Integer.parseInt((String) item.getValue()));
                }
            } else if (item.getKey().toString().equals("status")) {
                if (item.getValue() == null || StringUtils.isEmpty((String) item.getValue())) {
                    resultMap.put("status", null);
                } else {
                    resultMap.put("status", item.getValue());
                }
            } else if (item.getKey().toString().equals("scoreStatus")) {
                if (item.getValue() == null || StringUtils.isEmpty((String) item.getValue())) {
                    resultMap.put("scoreStatus", null);
                } else {
                    resultMap.put("scoreStatus", item.getValue());
                }
            } else if (item.getKey().toString().equals("workCommit")) {
                if (item.getValue() == null) {
                    resultMap.put("workCommit", null);
                } else {
                    resultMap.put("workCommit", item.getValue());
                }
            } else if (item.getKey().toString().equals("stuId")) {
                if (item.getValue() == null) {
                    resultMap.put("stuId", null);
                } else {
                    resultMap.put("stuId", item.getValue());
                }
            } else if (item.getKey().equals("raterAccount")) {
                if (StringUtils.isBlank((String) item.getValue())) {
                    throw BussinessException.asBussinessException(AlcsErrorCode.PARAM_EXCEPTION);
                } else {
                    resultMap.put("raterAccount", item.getValue());
                }
            }else if(item.getKey().equals("contestStatus")){
                if(StringUtils.isBlank(item.getValue().toString())){
                   resultMap.put("contestStatus",null);
                }else{
                    resultMap.put("contestStatus",item.getValue());
                }
            }
        }
        Integer offset = ((Integer) resultMap.get("page") - 1) * (Integer) queryMap.get("rows");
        resultMap.put("offset", offset);
        return resultMap;

    }


}
