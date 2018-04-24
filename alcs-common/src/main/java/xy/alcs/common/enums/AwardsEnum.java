package xy.alcs.common.enums;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 17:18 2018-04-15
 */
public enum AwardsEnum {
    FIRST(1, "一等奖"),
    SECOND(2, "二等奖"),
    THIRD(3, "三等奖"),
    EXCELLENCE(4, "优秀奖"),


    SPECIAL(666, "特别奖"),
    ENCOURAGEMENT(667, "鼓励奖");

    private Integer code;
    private String description;

    AwardsEnum(Integer code, String description) {
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

    public static AwardsEnum getEnumByCode(Integer code) {
        AwardsEnum[] values = AwardsEnum.values();
        for (AwardsEnum awardsEnum : values) {
           if(awardsEnum.getCode().equals(code)){
               return awardsEnum;
           }
        }
        return null;
    }
}
