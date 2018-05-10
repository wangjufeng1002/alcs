package xy.alcs.common.enums;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 0:11 2018-05-11
 */
public enum ScoreStatusEnum {
    YES(1,"已统计"),
    NO(0,"未统计");
    private Integer code;
    private String  description;

    ScoreStatusEnum(Integer code, String description) {
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
