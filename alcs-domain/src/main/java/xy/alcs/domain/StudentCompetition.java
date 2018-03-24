package xy.alcs.domain;

import java.util.Date;

public class StudentCompetition {
    private Long scId;

    private String studentId;

    private Integer studentN;

    private Long contestId;

    private String teamId;

    private Date timestamp;

    private Integer workcommit;

    public Long getScId() {
        return scId;
    }

    public void setScId(Long scId) {
        this.scId = scId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public Integer getStudentN() {
        return studentN;
    }

    public void setStudentN(Integer studentN) {
        this.studentN = studentN;
    }

    public Long getContestId() {
        return contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId == null ? null : teamId.trim();
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getWorkcommit() {
        return workcommit;
    }

    public void setWorkcommit(Integer workcommit) {
        this.workcommit = workcommit;
    }
}