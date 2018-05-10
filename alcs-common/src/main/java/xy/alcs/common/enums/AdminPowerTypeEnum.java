package xy.alcs.common.enums;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 11:12 2018-05-10
 */
public enum AdminPowerTypeEnum {
    SUPER_ADMIN(1, "超级管理员"),
    ORDINARY_ADMIN(2, "普通管理员"),
    ;
    private Integer code;
    private String description;


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

    AdminPowerTypeEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
