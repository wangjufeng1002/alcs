package xy.alcs.domain;

import java.util.Date;

public class Contest {
    private Long cid;

    private String title;

    private String summary;

    private Date endDate;

    private Date startDate;

    private Date enrollStartDate;

    private Date enrollEndDate;

    private Date worksEndDate;

    private Date timestamp;

    private Date scoreStartDate;

    private Date scoreEndDate;

    private Integer status;

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
        this.title = title == null ? null : title.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
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

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
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
}