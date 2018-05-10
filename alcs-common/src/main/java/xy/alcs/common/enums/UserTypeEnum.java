package xy.alcs.common.enums;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 19:18 2018-05-10
 */
public enum UserTypeEnum {
    STUDENT(1, "学生"),
    RATER(2, "评委"),
    ADMIN(3, "管理员"),
    ;

    private Integer code;
    private String description;

    UserTypeEnum(Integer code, String description) {
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
