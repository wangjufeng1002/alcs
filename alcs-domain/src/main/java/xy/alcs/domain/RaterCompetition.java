package xy.alcs.domain;

public class RaterCompetition {
    private Long rcId;

    private Integer raterId;

    private Long contestId;

    public Long getRcId() {
        return rcId;
    }

    public void setRcId(Long rcId) {
        this.rcId = rcId;
    }

    public Integer getRaterId() {
        return raterId;
    }

    public void setRaterId(Integer raterId) {
        this.raterId = raterId;
    }

    public Long getContestId() {
        return contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
    }
}