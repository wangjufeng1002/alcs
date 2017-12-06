package xy.alcs.domain;

public class StudentCompetition {
    private Long scId;

    private String studentId;

    private Integer studentN;

    private Long contestId;

    private Long teamId;

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

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
}