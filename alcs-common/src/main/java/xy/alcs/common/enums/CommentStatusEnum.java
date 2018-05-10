package xy.alcs.common.enums;

/**
 * @Author:ju
 * @Description: 评分状态
 * @Date:Create in 22:17 2018-05-07
 */
public enum CommentStatusEnum {
    NOT(0, "未评分"),
    YES(1, "已评分");
    private Integer code;
    private String description;

    CommentStatusEnum(Integer code, String description) {
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
