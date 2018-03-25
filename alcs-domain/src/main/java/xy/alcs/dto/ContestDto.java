package xy.alcs.dto;

import xy.alcs.domain.Rater;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 0:29 2017-12-07
 */
public class ContestDto {
    private Long cid;
    private String title;
    private String summary;        //简介

    private Date endDate;          //比赛结束时间（数据库字段）

    private Date startDate;        //比赛开始时间（数据库字段）

    private Date enrollStartDate;  //报名开始时间

    private Date enrollEndDate;    //报名结束时间

    private Date worksEndDate;     //作品提交结束时间

    private Date scoreStartDate;   //评分开始时间

    private Date scoreEndDate;     //评分结束时间

    private Integer status;           //状态

    private String extendJson;         //扩展字段

    private Integer raterSize = 0;     //分配了几个评委

    private List<RaterDto> raters;     //评委集合



    //显示属性

    private String scoreTime;     //评分时间段
    private String startEndTime;  //比赛起始时间
    private String enrollTime;    //报名起始时间
    private String nEnroll;       //是否可以报名  0不能报名（报名时间已过，或未到报名时间） 1可以报名  2比赛已结束 3比赛
    private String workEndTime ;  //作品提交截止时间


    private static Calendar today = Calendar.getInstance();
    static {
        today.setTime(new Date());
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.SECOND, 0);
    }
    public String getScoreTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String start = format.format(this.scoreStartDate);
        String end = format.format(this.scoreEndDate);
        return start + " 至 " + end;
    }

    public void setScoreTime(String scoreTime) {
        this.scoreTime = scoreTime;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
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

    //拼接比赛起始时间
    public String getStartEndTime() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String start = format.format(this.startDate);
        String end = format.format(this.endDate);
        return start + " 至 " + end;
    }

    public void setStartEndTime(String startEndTime) {
        this.startEndTime = startEndTime;
    }

    //拼接报名起始时间
    public String getEnrollTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String start = format.format(this.enrollStartDate);
        String end = format.format(this.enrollEndDate);
        return start + " 至 " + end;
    }

    public void setEnrollTime(String enrollTime) {
        this.enrollTime = enrollTime;
    }

    //判断是否可以报名的状态
    //是否可以报名  0不能报名（报名时间已过，或未到报名时间,在比赛有效期内 ） 1可以报名  2比赛已结束 3比赛未开始
    public String getnEnroll() {
        Calendar eStartDate  =Calendar.getInstance();  //报名开始时间
        eStartDate.setTime(this.enrollStartDate);
        Calendar eEndDate  =Calendar.getInstance();    //报名截止时间
        eEndDate.setTime(this.enrollEndDate);

        Calendar startDate  =Calendar.getInstance();  //比赛开始时间
        startDate.setTime(this.startDate);
        Calendar endDate  =Calendar.getInstance();    //比赛截止时间
        endDate.setTime(this.endDate);
        //可以报名
        if( today.equals(eEndDate) ||  today.equals(eStartDate)||(today.before(eEndDate) && today.after(eStartDate))){
            nEnroll = "1"; //可以报名
        }
        else if(today.after(endDate)){
            nEnroll = "2"; //比赛已结束
        } else if(today.before(startDate)){
            nEnroll = "3"; //比赛未开始
        }else{
            nEnroll = "0"; //不能报名
        }
        return nEnroll;
    }

    public String getWorkEndTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(worksEndDate);
    }

    public void setnEnroll(String nEnroll) {
        this.nEnroll = nEnroll;
    }

    public ContestDto() {
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
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String start = format.format(this.worksEndDate);
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

    public void setWorkEndTime(String workEndTime) {
        this.workEndTime = workEndTime;
    }


    public String getExtendJson() {
        return extendJson;
    }

    public void setExtendJson(String extendJson) {
        this.extendJson = extendJson;
    }

    public Integer getRaterSize() {
        return raterSize;
    }

    public void setRaterSize(Integer raterSize) {
        this.raterSize = raterSize;
    }

    public List<RaterDto> getRaters() {
        return raters;
    }

    public void setRaters(List<RaterDto> raters) {
        this.raters = raters;
    }
}
