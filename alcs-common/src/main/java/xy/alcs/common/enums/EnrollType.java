package xy.alcs.common.enums;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 11:14 2018-05-31
 */
public enum EnrollType {
    ONLY(1,"个人参赛"),
    TEAM(2,"小组参赛")
    ;
    private Integer code;
    private String description;

    EnrollType(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
