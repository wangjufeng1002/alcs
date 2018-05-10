package xy.alcs.common.enums;

import xy.alcs.domain.College;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 16:04 2018-05-09
 */
public enum DepartmentTypeEnum {

    COLLEGE(1, "学院"),
    MAJOR(2, "专业"),
    CLASS(3, "班级");
    private Integer code;
    private String description;

    DepartmentTypeEnum(Integer code, String description) {
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
