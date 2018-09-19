package xy.alcs.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author:ju
 * @Description: 用于man端展示作品列表实体类
 * @Date:Create in 20:19 2018-05-06
 */
public class WorkListDto {
    private String imageWebUrl = "http://localhost:8088/upload/";
    private String fileWebUrl = "http://localhost:8088/file/";

    private Long cId;                //比赛id
    private String title;            //竞赛题目
    private String summary;
    private Date worksEndDate;       //作品提交结束时间
    private Date scoreStartDate;   //评分开始时间
    private Date scoreEndDate;     //评分结束时间


    private Long wId;
    private String image;
    private String thesis;
    private Integer commit;          //作品是否提交
    private String code;             //代码
    private Float score;

    //学生学号
    private String studentId;
    //小组Id
    private String teamId;

    //奖项
    private Integer prize;
    private String prizeStr;

    //前端显示字符串
    private String workSatus;        //作品状态（字符串）
    private String workEndTime;      //作品提交截止时间
    private String scoreTime;        //评分时间段


    public String getWorkSatus() {
        /**
         * NOT_SAVE_COMIIT(0, "未保存也未提交"),
         NOT_COIIMY_AND_SAVED(1, "保存了，但未提交"),
         IS_COMMIT(3, "已提交");
         */
        if (commit == null) {
            return "未提交";
        } else if (commit == 0) {
            return "未提交";
        } else if (commit == 1) {
            return "已保存";
        } else if (commit == 2) {
            return "已提交";
        } else {
            return "未提交";
        }
    }

    public Long getcId() {
        return cId;
    }

    public void setcId(Long cId) {
        this.cId = cId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getWorksEndDate() {
        return worksEndDate;
    }

    public void setWorksEndDate(Date worksEndDate) {
        this.worksEndDate = worksEndDate;
    }

    public String getImage() {
        return imageWebUrl + this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getThesis() {
        return fileWebUrl + this.thesis;
    }

    public void setThesis(String thesis) {
        this.thesis = thesis;
    }

    public Integer getCommit() {
        return commit;
    }

    public void setCommit(Integer commit) {
        this.commit = commit;
    }

    public void setWorkSatus(String workSatus) {
        this.workSatus = workSatus;
    }

    public Long getwId() {
        return wId;
    }

    public void setwId(Long wId) {
        this.wId = wId;
    }

    public String getWorkEndTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(worksEndDate);
    }

    public void setWorkEndTime(String workEndTime) {
        this.workEndTime = workEndTime;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getScoreEndDate() {
        return scoreEndDate;
    }

    public void setScoreEndDate(Date scoreEndDate) {
        this.scoreEndDate = scoreEndDate;
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

    public Date getScoreStartDate() {
        return scoreStartDate;
    }

    public void setScoreStartDate(Date scoreStartDate) {
        this.scoreStartDate = scoreStartDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Integer getPrize() {
        return prize;
    }

    public void setPrize(Integer prize) {
        this.prize = prize;
    }

    public String getPrizeStr() {
        if(this.prize == null){
            return null;
        }
        /*       FIRST(1, "一等奖"),
                SECOND(2, "二等奖"),
                THIRD(3, "三等奖"),
                EXCELLENCE(4, "优秀奖"),


                SPECIAL(666, "特别奖"),
                ENCOURAGEMENT(667, "鼓励奖")
                */
        String prizeStr = null;
        switch (this.prize) {
            case 1:
                prizeStr = "一等奖";
                break;
            case 2:
                prizeStr = "二等奖";
                break;
            case 3:
                prizeStr = "三等奖";
                break;
            case 4:
                prizeStr = "优秀奖";
                break;
            case 666:
                prizeStr = "特别奖";
                break;
            case 667:
                prizeStr = "鼓励奖";
                break;
            default:
                prizeStr = "优秀奖";
                break;
        }
        return prizeStr;
    }

    public void setPrizeStr(String prizeStr) {
        this.prizeStr = prizeStr;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }
}
