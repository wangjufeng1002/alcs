package xy.alcs.common.enums;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 21:04 2018-04-25
 */
public enum  TeamCaptainEnum {

    NOT_CAPTAIN(0,"不是队长"),
    IS_CAPTAIN(1,"是队长")
    ;

    private Integer code;
    private String description;

    TeamCaptainEnum(Integer code, String description) {
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
