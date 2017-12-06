package xy.alcs.domain;

public class Major {
    private Integer majId;

    private String majName;

    private Integer collegeId;

    public Integer getMajId() {
        return majId;
    }

    public void setMajId(Integer majId) {
        this.majId = majId;
    }

    public String getMajName() {
        return majName;
    }

    public void setMajName(String majName) {
        this.majName = majName == null ? null : majName.trim();
    }

    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }
}