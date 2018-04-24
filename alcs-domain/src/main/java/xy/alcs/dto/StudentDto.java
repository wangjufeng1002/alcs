package xy.alcs.dto;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 21:33 2018-04-23
 */
public class StudentDto {
    private String stuId;   //学号
    private String stuName; //学生姓名

    private Integer colId;   //学院id
    private String colName; //学院名称
    private Integer majId;   //专业id
    private String majName; //专业名称
    private Integer claId;   //班级id
    private String claName; //班级名称


    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Integer getColId() {
        return colId;
    }

    public void setColId(Integer colId) {
        this.colId = colId;
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

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
        this.majName = majName;
    }

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
        this.claName = claName;
    }
}
