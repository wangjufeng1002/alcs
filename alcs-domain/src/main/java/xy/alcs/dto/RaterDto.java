package xy.alcs.dto;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 16:23 2018-03-25
 */
public class RaterDto {
    private Integer rid;

    private String ratName;

    private String ratAccount;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRatName() {
        return ratName;
    }

    public void setRatName(String ratName) {
        this.ratName = ratName;
    }

    public String getRatAccount() {
        return ratAccount;
    }

    public void setRatAccount(String ratAccount) {
        this.ratAccount = ratAccount;
    }
}
