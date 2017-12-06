package xy.alcs.domain;

public class Student extends StudentKey {
    private String stuName;

    private Integer stuGender;

    private Integer stuColId;

    private Integer stuMajId;

    private Integer stuClaId;

    private String stuPassword;

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName == null ? null : stuName.trim();
    }

    public Integer getStuGender() {
        return stuGender;
    }

    public void setStuGender(Integer stuGender) {
        this.stuGender = stuGender;
    }

    public Integer getStuColId() {
        return stuColId;
    }

    public void setStuColId(Integer stuColId) {
        this.stuColId = stuColId;
    }

    public Integer getStuMajId() {
        return stuMajId;
    }

    public void setStuMajId(Integer stuMajId) {
        this.stuMajId = stuMajId;
    }

    public Integer getStuClaId() {
        return stuClaId;
    }

    public void setStuClaId(Integer stuClaId) {
        this.stuClaId = stuClaId;
    }

    public String getStuPassword() {
        return stuPassword;
    }

    public void setStuPassword(String stuPassword) {
        this.stuPassword = stuPassword == null ? null : stuPassword.trim();
    }
}