package xy.alcs.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xy.alcs.common.enums.AlcsErrorCode;
import xy.alcs.common.enums.CommentStatusEnum;
import xy.alcs.common.enums.WorkCommitEnum;
import xy.alcs.common.exception.BussinessException;
import xy.alcs.common.utils.WorksToWorkDto;
import xy.alcs.dao.*;
import xy.alcs.domain.*;
import xy.alcs.dto.RaterWorkDto;
import xy.alcs.dto.WorkDto;
import xy.alcs.dto.WorkListDto;
import xy.alcs.manager.WorkManager;
import xy.alcs.manager.impl.WorkManagerImpl;
import xy.alcs.service.WorksService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 18:38 2018-03-04
 */
@Service("worksService")
public class WorksServiceImpl implements WorksService {

    private static Logger logger = LoggerFactory.getLogger(WorkManagerImpl.class);

    @Value("${image.Web.Url}")
    String imageWebUrl;

    @Value("${file.Web.Url}")
    String fileWebUrl;


    @Value("${page.now}")
    private String pageNow;

    @Value("${page.limit}")
    private String rows;

    @Resource
    private WorksMapper worksMapper;

    @Resource
    private StudentCompetitionMapper studentCompetitionMapper;

    @Resource
    private WorkManager workManager;

    @Resource
    private RaterCompetitionMapper raterCompetitionMapper;
    @Resource
    private CommentMapper commentMapper;


    @Override
    public Boolean saveWork(WorkDto workDto) {

        StudentCompetitionExample studentCompetitionExample = new StudentCompetitionExample();
        studentCompetitionExample.createCriteria().andContestIdEqualTo(workDto.getCid()).andStudentIdEqualTo(workDto.getStuId());
        List<StudentCompetition> studentCompetitions = studentCompetitionMapper.selectByExample(studentCompetitionExample);
        if (CollectionUtils.isEmpty(studentCompetitions)) {
            throw BussinessException.asBussinessException(AlcsErrorCode.SYSTEM_ERROR);
        }
        if (studentCompetitions.size() > 1) {
            throw BussinessException.asBussinessException(AlcsErrorCode.SYSTEM_ERROR);
        }

        workDto.setTeamId(studentCompetitions.get(0).getTeamId());

        WorksExample worksExample = new WorksExample();
        worksExample.createCriteria().andTeamIdEqualTo(workDto.getTeamId()).andContestIdEqualTo(workDto.getCid());
        List<Works> worksList = worksMapper.selectByExample(worksExample);


        Works w = new Works();
        w.setImage(workDto.getImageName());
        w.setContestId(workDto.getCid());
        w.setThesis(workDto.getFileName());
        w.setTeamId(workDto.getTeamId());
        w.setCode(workDto.getWorkContent());
        w.setCommit(WorkCommitEnum.NOT_COIIMY_AND_SAVED.getCode());


        if (CollectionUtils.isEmpty(worksList)) {
            worksMapper.insert(w);
            return true;
        }
        if (worksList.size() > 1) {
            throw BussinessException.asBussinessException(AlcsErrorCode.SYSTEM_ERROR);
        }
        if (worksList.size() == 1) {
            w.setwId(worksList.get(0).getwId());
            worksMapper.updateByPrimaryKey(w);
        }

        //更新学生分配表的作品提交状态
        Map<String, Object> updateMap = new HashMap<>();
        updateMap.put("workcommit", WorkCommitEnum.NOT_COIIMY_AND_SAVED.getCode());
        updateMap.put("teamId", workDto.getTeamId());
        studentCompetitionMapper.updateCommitStatus(updateMap);

        return true;
    }

    @Override
    public Boolean submitWork(WorkDto workDto) {
        StudentCompetitionExample studentCompetitionExample = new StudentCompetitionExample();
        studentCompetitionExample.createCriteria().andContestIdEqualTo(workDto.getCid()).andStudentIdEqualTo(workDto.getStuId());
        List<StudentCompetition> studentCompetitions = studentCompetitionMapper.selectByExample(studentCompetitionExample);
        if (CollectionUtils.isEmpty(studentCompetitions)) {
            throw BussinessException.asBussinessException(AlcsErrorCode.SYSTEM_ERROR);
        }
        if (studentCompetitions.size() > 1) {
            throw BussinessException.asBussinessException(AlcsErrorCode.SYSTEM_ERROR);
        }

        workDto.setTeamId(studentCompetitions.get(0).getTeamId());

        WorksExample worksExample = new WorksExample();
        worksExample.createCriteria().andTeamIdEqualTo(workDto.getTeamId());
        List<Works> worksList = worksMapper.selectByExample(worksExample);
        Works w = new Works();
        w.setImage(workDto.getImageName());
        w.setContestId(workDto.getCid());
        w.setThesis(workDto.getFileName());
        w.setTeamId(workDto.getTeamId());
        w.setCode(workDto.getWorkContent());
        w.setCommit(WorkCommitEnum.IS_COMMIT.getCode());


        if (CollectionUtils.isEmpty(worksList)) {
            worksMapper.insert(w);
        }
        if (worksList.size() > 1) {
            throw BussinessException.asBussinessException(AlcsErrorCode.SYSTEM_ERROR);
        }
        if (worksList.size() == 1) {
            w.setwId(worksList.get(0).getwId());
            worksMapper.updateByPrimaryKey(w);
        }

        //更新学生分配表的作品提交状态
        Map<String, Object> updateMap = new HashMap<>();
        updateMap.put("workcommit", WorkCommitEnum.IS_COMMIT.getCode());
        updateMap.put("teamId", workDto.getTeamId());
        studentCompetitionMapper.updateCommitStatus(updateMap);

        //查出比赛分配的评委
        RaterCompetitionExample raterCompetitionExample = new RaterCompetitionExample();
        raterCompetitionExample.createCriteria().andContestIdEqualTo(workDto.getCid());
        List<RaterCompetition> raterCompetitionList = raterCompetitionMapper.selectByExample(raterCompetitionExample);

        if (CollectionUtils.isEmpty(worksList)) {
            WorksExample worksExample1 = new WorksExample();
            worksExample1.createCriteria().andTeamIdEqualTo(workDto.getTeamId()).andContestIdEqualTo(workDto.getCid());
            worksList = worksMapper.selectByExample(worksExample1);
        }


        //插入评语表信息
        Comment comment = new Comment();
        comment.setStatus(CommentStatusEnum.NOT.getCode());
        comment.setContestId(workDto.getCid());
        comment.setTeamId(workDto.getTeamId());
        comment.setWorkId(worksList.get(0).getwId());
        for (RaterCompetition raterCompetition : raterCompetitionList) {
            comment.setRaterId(raterCompetition.getRaterId());
            commentMapper.insert(comment);
        }
        return true;
    }

    @Override
    public WorkDto queryWork(Long cid, String sId) {
        StudentCompetitionExample studentCompetitionExample = new StudentCompetitionExample();
        studentCompetitionExample.createCriteria().andContestIdEqualTo(cid).andStudentIdEqualTo(sId);
        List<StudentCompetition> studentCompetitions = studentCompetitionMapper.selectByExample(studentCompetitionExample);
        if (CollectionUtils.isEmpty(studentCompetitions)) {
            throw BussinessException.asBussinessException(AlcsErrorCode.SYSTEM_ERROR);
        }
        if (studentCompetitions.size() > 1) {
            throw BussinessException.asBussinessException(AlcsErrorCode.SYSTEM_ERROR);
        }
        String teamId = studentCompetitions.get(0).getTeamId();
        WorksExample worksExample = new WorksExample();
        worksExample.createCriteria().andTeamIdEqualTo(teamId);
        List<Works> worksList = worksMapper.selectByExample(worksExample);
        if (CollectionUtils.isEmpty(worksList)) {
            return null;
        }
        if (worksList.size() > 1) {
            throw BussinessException.asBussinessException(AlcsErrorCode.SYSTEM_ERROR);
        }

        Works work = worksList.get(0);
        WorkDto workDto = WorksToWorkDto.buildWorkDto(work, imageWebUrl, fileWebUrl);
        return workDto;
    }

    @Override
    public WorkDto queryWorkByWid(Long wId) {
        Works works = worksMapper.selectByPrimaryKey(wId);
        WorkDto workDto = WorksToWorkDto.buildWorkDto(works, imageWebUrl, fileWebUrl);
        return workDto;
    }

    @Override
    public List<WorkListDto> listWork(Map<String, Object> queryParamMap) {
        if (MapUtils.isEmpty(queryParamMap)) {
            throw BussinessException.asBussinessException(AlcsErrorCode.PARAM_EXCEPTION);
        }
        Map<String, Object> queryMap = this.buildQueryMap(queryParamMap);
        return worksMapper.selectWork(queryMap);
    }

    @Override
    public Integer countWork(Map<String, Object> queryParamMap) {
        if (MapUtils.isEmpty(queryParamMap)) {
            throw BussinessException.asBussinessException(AlcsErrorCode.PARAM_EXCEPTION);
        }
        Map<String, Object> queryMap = this.buildQueryMap(queryParamMap);
        return worksMapper.countWork(queryMap);
    }

    @Override
    public List<RaterWorkDto> listRaterWorks(Map<String, Object> queryParamMap) {
        if (MapUtils.isEmpty(queryParamMap)) {
            throw BussinessException.asBussinessException(AlcsErrorCode.PARAM_EXCEPTION);
        }
        Map<String, Object> queryMap = this.buildQueryMap(queryParamMap);
        return worksMapper.selectRaterWorks(queryMap);
    }

    @Override
    public Integer countRaterWorks(Map<String, Object> queryParamMap) {
        if (MapUtils.isEmpty(queryParamMap)) {
            throw BussinessException.asBussinessException(AlcsErrorCode.PARAM_EXCEPTION);
        }
        Map<String, Object> queryMap = this.buildQueryMap(queryParamMap);
        return worksMapper.countRaterWorks(queryMap);
    }

    @Override
    public RaterWorkDto queryRaterWorkDetail(Map<String, Object> queryParamMap) {
        if (MapUtils.isEmpty(queryParamMap)) {
            throw BussinessException.asBussinessException(AlcsErrorCode.PARAM_EXCEPTION);
        }
        return worksMapper.selectRaterWorkDetail(queryParamMap);
    }

    @Override
    public List<WorkListDto> queryWorkDtoByCid(Map<String, Object> queryParamMap) {
        if (MapUtils.isEmpty(queryParamMap)) {
            throw BussinessException.asBussinessException(AlcsErrorCode.PARAM_EXCEPTION);
        }
        Map<String, Object> map = this.buildQueryMap(queryParamMap);
        return worksMapper.selectScoreList(map);
    }

    @Override
    public Integer countWorkDtoByCid(Map<String, Object> queryParamMap) {
        if (MapUtils.isEmpty(queryParamMap)) {
            throw BussinessException.asBussinessException(AlcsErrorCode.PARAM_EXCEPTION);
        }
        Map<String, Object> map = this.buildQueryMap(queryParamMap);
        Integer total = worksMapper.countScoreList(map);
        return total;
    }

    @Override
    public Boolean deleteWork(List<Long> wids) {
        if (CollectionUtils.isEmpty(wids)) {
            throw BussinessException.asBussinessException(AlcsErrorCode.PARAM_EXCEPTION);
        }
        try {
            if (workManager.batchDelWork(wids)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.error("#WorksServiceImpl deleteQWork error:{}", e);
            return false;
        }
    }

    private Map<String, Object> buildQueryMap(Map<String, Object> queryParamMap) {
        Map<String, Object> resultMap = new HashMap<>();

        for (Map.Entry item : queryParamMap.entrySet()) {
            if (item.getKey().toString().equals("page")) {
                if (item.getValue() == null) {
                    resultMap.put("page", Integer.parseInt(this.pageNow));
                }
                resultMap.put("page", queryParamMap.get("page"));
            } else if (item.getKey().toString().equals("rows")) {
                if (item.getValue() == null) {
                    resultMap.put("limit", Integer.parseInt(this.rows));
                }
                resultMap.put("limit", queryParamMap.get("rows"));
            } else if (item.getKey().toString().equals("title")) {
                if (item.getValue() == null || StringUtils.isEmpty (item.getValue().toString())) {
                    resultMap.put("title", null);
                } else {
                    resultMap.put("title", (String) item.getValue());
                }
            } else if (item.getKey().toString().equals("cid")) {
                if (item.getValue() == null || StringUtils.isEmpty(item.getValue().toString())) {
                    resultMap.put("cid", null);
                } else {
                    resultMap.put("cid", Integer.parseInt(item.getValue().toString()));
                }
            } else if (item.getKey().toString().equals("status")) {
                if (item.getValue() == null || StringUtils.isEmpty(item.getValue().toString())) {
                    resultMap.put("status", null);
                } else {
                    resultMap.put("status", item.getValue());
                }
            } else if (item.getKey().toString().equals("commit")) {
                if (item.getValue() == null) {
                    resultMap.put("commit", null);
                } else {
                    resultMap.put("commit", item.getValue());
                }
            } else if (item.getKey().toString().equals("sid")) {
                if (StringUtils.isBlank((String) item.getValue())) {
                    resultMap.put("sid", null);
                } else {
                    resultMap.put("sid", item.getValue());
                }
            } else if (item.getKey().toString().equals("wid")) {
                if (StringUtils.isBlank(item.getValue().toString())) {
                    resultMap.put("wid", null);
                } else {
                    resultMap.put("wid", item.getValue());
                }
            } else if (item.getKey().toString().equals("title")) {
                if (StringUtils.isBlank((String) item.getValue())) {
                    resultMap.put("title", null);
                } else {
                    resultMap.put("title", item.getValue());
                }
            } else if (item.getKey().toString().equals("raterAccount")) {
                if (StringUtils.isBlank((String) item.getValue())) {
                    resultMap.put("raterAccount", null);
                } else {
                    resultMap.put("raterAccount", item.getValue());
                }
            }
        }
        Integer offset = ((Integer) resultMap.get("page") - 1) * (Integer) resultMap.get("limit");
        resultMap.put("offset", offset);
        return resultMap;
    }
}
