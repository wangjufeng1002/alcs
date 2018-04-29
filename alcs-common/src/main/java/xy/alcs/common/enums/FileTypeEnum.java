package xy.alcs.common.enums;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 20:56 2018-04-29
 */
public enum FileTypeEnum {
    IMAGE(1,"图片"),
    DOCUMENT(2,"文档"),

    ;

    private Integer code;
    private String  description;

    FileTypeEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
