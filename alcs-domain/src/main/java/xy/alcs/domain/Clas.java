package xy.alcs.domain;

public class Clas {
    private Integer claId;

    private String claName;

    private Integer majorId;

    public Integer getClaId() {
        return claId;
    }

    public void setClaId(Integer claId) {
        this.claId = claId;
    }

    public String getClaName() {
        return claName;
    }

    public void setClaName(String claName) {
        this.claName = claName == null ? null : claName.trim();
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }
}