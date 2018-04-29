package xy.alcs.common.enums;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 21:08 2018-04-25
 */
public enum WorkCommitEnum {

    NOT_SAVE_COMIIT(0, "未保存也未提交"),
    NOT_COIIMY_AND_SAVED(1, "保存了，但未提交"),
    IS_COMMIT(2, "已提交");


    private Integer code;
    private String description;

    WorkCommitEnum(Integer code, String description) {
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
