package xy.alcs.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 16:56 2018-05-10
 */
public class AwardsDto {
    private Long cid;
    private String title;
    //比赛结束时间（数据库字段）
    private Date endDate;
    //比赛开始时间（数据库字段）
    private Date startDate;
    //分数
    private Float score;
    //学生学号
    private String studentId;
    //奖项
    private Integer prize;
    // ======= 前端显示字符串 =======
    private String startEndTime;
    private String prizeStr;


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

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Integer getPrize() {
        return prize;
    }

    public void setPrize(Integer prize) {
        this.prize = prize;
    }

    public String getPrizeStr() {
        if (this.prize == null) {
            return null;
        }
        /*FIRST(1, "一等奖"),
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

    public String getStartEndTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String start = format.format(this.startDate);
        String end = format.format(this.endDate);
        return start + " 至 " + end;
    }

    public void setStartEndTime(String startEndTime) {
        this.startEndTime = startEndTime;
    }
}
