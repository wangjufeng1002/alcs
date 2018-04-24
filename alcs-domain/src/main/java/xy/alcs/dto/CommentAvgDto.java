package xy.alcs.dto;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 19:46 2018-04-15
 */
public class CommentAvgDto {

    private String teamId;
    private Integer size;
    private Float   avg;
    private Long contestId;
    private  Long workId;

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Float getAvg() {
        return avg;
    }

    public void setAvg(Float avg) {
        this.avg = avg;
    }

    public Long getContestId() {
        return contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
    }

    public Long getWorkId() {
        return workId;
    }

    public void setWorkId(Long workId) {
        this.workId = workId;
    }
}
