package xy.alcs.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xy.alcs.common.enums.AlcsErrorCode;
import xy.alcs.common.exception.BussinessException;
import xy.alcs.common.utils.Result;
import xy.alcs.dao.ContestMapper;
import xy.alcs.dao.StudentCompetitionMapper;
import xy.alcs.domain.Contest;
import xy.alcs.domain.StudentCompetition;
import xy.alcs.domain.StudentCompetitionExample;
import xy.alcs.dto.ContestDateDto;
import xy.alcs.dto.ContestDto;
import xy.alcs.dto.MyContestDetailDto;
import xy.alcs.manager.ContestManager;
import xy.alcs.service.ContestService;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 1:12 2017-12-07
 */
@Service("contestService")
public class ContestServiceImpl implements ContestService {
    private static  Logger logger = LoggerFactory.getLogger(ContestServiceImpl.class);


    @Resource
    private ContestMapper contestMapper;

    @Resource
    private StudentCompetitionMapper studentCompetitionMapper;

    @Resource
    private ContestManager contestManager;

    @Value("${page.now}")
    private String pageNow;

    @Value("${page.limit}")
    private String rows;


    public List<ContestDto> listMyContestByParam(String sId, Integer offset, Integer limit, Integer status) {
        Map queryParamMap = new HashMap();
        queryParamMap.put("sId", sId);
        queryParamMap.put("offset", offset);
        queryParamMap.put("limit", limit);
        queryParamMap.put("status", status);
        return contestMapper.selectMyContestByParam(queryParamMap);
    }
    @Override
    public Integer countMyContestByParam(String sId, Integer offset, Integer limit, Integer status) {
        Map queryParamMap = new HashMap();
        queryParamMap.put("sId", sId);
        queryParamMap.put("offset", offset);
        queryParamMap.put("limit", limit);
        queryParamMap.put("status", status);
        return contestMapper.countMyContestByParam(queryParamMap);
    }

    @Override
    public List<ContestDto> listContest(Integer page, Integer rows) {
        if(page == null || page <=0){
            page = Integer.parseInt(this.pageNow);
        }
        if(rows == null || rows <=0){
            rows = Integer.parseInt(this.rows);
        }
        Integer offset = (page - 1) * rows;
        List<ContestDto> contests = contestMapper.selectContest(offset, rows);
        return contests;
    }

    @Override
    public Integer countTotal() {
        return contestMapper.countTotal();
    }

    @Override
    public  MyContestDetailDto getMyContestDetail(String sId, Integer contestId){
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
    public  Result addOrUpdateContest(Contest contest){
        try {
            if(contest.getCid() == null){
                contestMapper.insert(contest);
            }else{
                contestMapper.updateByPrimaryKey(contest);
            }

        } catch (Exception e) {
            logger.error("#ContestServiceImpl addContest hadppend exception,error:{}" ,e);
            e.printStackTrace();
            throw BussinessException.asBussinessException(AlcsErrorCode.SYSTEM_ERROR);
        }
        return Result.buildSuccessResult(AlcsErrorCode.SUCCESS);
    }

    @Override
    public Contest changeContestDateFormat(Contest contest, ContestDateDto contestDateDto) {
        SimpleDateFormat dateFormat  = new SimpleDateFormat("yyyy-MM-dd");
        try {
            contest.setStartDate(dateFormat.parse(contestDateDto.getStartDateStr()));
            contest.setEndDate(dateFormat.parse(contestDateDto.getEndDateStr()));
            contest.setEnrollStartDate(dateFormat.parse(contestDateDto.getEnrollStartDateStr()));
            contest.setEnrollEndDate(dateFormat.parse(contestDateDto.getEnrollEndDateStr()));
            contest.setScoreStartDate(dateFormat.parse(contestDateDto.getScoreStartDateStr()));
            contest.setScoreEndDate(dateFormat.parse(contestDateDto.getScoreEndDateStr()));
            contest.setWorksEndDate(dateFormat.parse(contestDateDto.getWorksEndDateStr()));
        } catch (Exception e) {
           logger.error("#ContestServiceImpl changeContestDateFormat hadppend exception,error:{}" ,e);
           throw BussinessException.asBussinessException(AlcsErrorCode.SYSTEM_ERROR);
        }
        return contest;
    }

    @Override
    public Boolean delContestByIds(List<Long> cIds) {
        if(CollectionUtils.isEmpty(cIds)){
            logger.error("#ContestServiceImpl delContestByIds param  cIds is empty");
            throw BussinessException.asBussinessException(AlcsErrorCode.PARAM_ISNULL);
        }
        return contestManager.batchDelete(cIds);
    }
}
