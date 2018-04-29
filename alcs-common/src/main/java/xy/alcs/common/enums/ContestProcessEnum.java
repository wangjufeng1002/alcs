package xy.alcs.common.enums;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 21:16 2018-04-24
 */
public enum  ContestProcessEnum {


    DOING(1,"正在进行"),
    END(2,"已结束");
    private Integer code;
    private String description;

    ContestProcessEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
