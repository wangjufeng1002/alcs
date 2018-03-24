package xy.alcs.dto;

import java.util.Date;
import java.util.List;

/**
 * @Author:ju 我的比赛详细信息
 * @Description:
 * @Date:Create in 13:24 2018-03-04
 */
public class MyContestDetailDto {
    private String cid;             //比赛id
    private String title;           //标题
    private String summary;         //简介

    private Date endDate;           //比赛结束时间（数据库字段）

    private Date startDate;        //比赛开始时间（数据库字段）

    private Date enrollStartDate;  //报名开始时间

    private Date enrollEndDate;    //报名结束时间

    private Date worksEndDate;     //作品提交结束时间

    private Date scoreStartDate;   //评分开始时间

    private Date scoreEndDate;     //评分结束时间

    private Integer status;        //状态 0,1 (0：已结束  ；  1：正在进行)

    private String teamId ;           //小组id,唯一；

    private Integer workcommit;       //作品是否提交

    private Float score;           //分数

    private List<String>  teammate;  //队友列表   ； 若是个人赛，则只显示本人学号

    public Integer getWorkcommit() {
        return workcommit;
    }

    public void setWorkcommit(Integer workcommit) {
        this.workcommit = workcommit;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEnrollStartDate() {
        return enrollStartDate;
    }

    public void setEnrollStartDate(Date enrollStartDate) {
        this.enrollStartDate = enrollStartDate;
    }

    public Date getEnrollEndDate() {
        return enrollEndDate;
    }

    public void setEnrollEndDate(Date enrollEndDate) {
        this.enrollEndDate = enrollEndDate;
    }

    public Date getWorksEndDate() {
        return worksEndDate;
    }

    public void setWorksEndDate(Date worksEndDate) {
        this.worksEndDate = worksEndDate;
    }

    public Date getScoreStartDate() {
        return scoreStartDate;
    }

    public void setScoreStartDate(Date scoreStartDate) {
        this.scoreStartDate = scoreStartDate;
    }

    public Date getScoreEndDate() {
        return scoreEndDate;
    }

    public void setScoreEndDate(Date scoreEndDate) {
        this.scoreEndDate = scoreEndDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<String> getTeammate() {
        return teammate;
    }

    public void setTeammate(List<String> teammate) {
        this.teammate = teammate;
    }
}
