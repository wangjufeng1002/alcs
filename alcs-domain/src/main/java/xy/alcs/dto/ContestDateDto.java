package xy.alcs.dto;

/**
 * @Author:ju
 * @Description: 用于接收前端传来的时间字符串
 * @Date:Create in 15:59 2018-03-18
 */
public class ContestDateDto {

    private String startDateStr;
    private String endDateStr;
    private String enrollStartDateStr;
    private String enrollEndDateStr;
    private String worksEndDateStr;
    private String scoreStartDateStr;
    private String scoreEndDateStr;

    public String getStartDateStr() {
        return startDateStr;
    }

    public void setStartDateStr(String startDateStr) {
        this.startDateStr = startDateStr;
    }

    public String getEndDateStr() {
        return endDateStr;
    }

    public void setEndDateStr(String endDateStr) {
        this.endDateStr = endDateStr;
    }

    public String getEnrollStartDateStr() {
        return enrollStartDateStr;
    }

    public void setEnrollStartDateStr(String enrollStartDateStr) {
        this.enrollStartDateStr = enrollStartDateStr;
    }

    public String getEnrollEndDateStr() {
        return enrollEndDateStr;
    }

    public void setEnrollEndDateStr(String enrollEndDateStr) {
        this.enrollEndDateStr = enrollEndDateStr;
    }

    public String getWorksEndDateStr() {
        return worksEndDateStr;
    }

    public void setWorksEndDateStr(String worksEndDateStr) {
        this.worksEndDateStr = worksEndDateStr;
    }

    public String getScoreStartDateStr() {
        return scoreStartDateStr;
    }

    public void setScoreStartDateStr(String scoreStartDateStr) {
        this.scoreStartDateStr = scoreStartDateStr;
    }

    public String getScoreEndDateStr() {
        return scoreEndDateStr;
    }

    public void setScoreEndDateStr(String scoreEndDateStr) {
        this.scoreEndDateStr = scoreEndDateStr;
    }
}
