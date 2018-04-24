package xy.alcs.common.enums;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 17:53 2018-04-14
 */
public enum ContestStatusEnum {
    /**
     *   Integer CONTEST_START     = 1;      //比赛已启动
     Integer CONTEST_ENROLL    = 2;      //可以报名
     Integer CONTEST_SCORE     = 3;      //正在打分（禁止报名，禁止提交作品，禁止分配评委）
     Integer CONTEST_SCORE_END = 4;      //打分结束
     Integer CONTEST_END       = 5;      //已结束

     */
    CONTEST_NOT_START(0,"比赛未开始"),
    CONTEST_START(1,"比赛启动"),
    CONTEST_ENROLL(2,"可报名"),
    CONTEST_SCORE(3,"正在打分"),
    CONTEST_SCORE_END(4,"打分结束"),
    CONTEST_END(5,"比赛结束")
    ;


    private Integer code;
    private String description;

    ContestStatusEnum(Integer code, String description) {
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
