package xy.alcs.domain;

public class Rater {
    private Integer rid;

    private String ratName;

    private String ratAccount;

    private String ratPassword;

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
        this.ratName = ratName == null ? null : ratName.trim();
    }

    public String getRatAccount() {
        return ratAccount;
    }

    public void setRatAccount(String ratAccount) {
        this.ratAccount = ratAccount == null ? null : ratAccount.trim();
    }

    public String getRatPassword() {
        return ratPassword;
    }

    public void setRatPassword(String ratPassword) {
        this.ratPassword = ratPassword == null ? null : ratPassword.trim();
    }
}