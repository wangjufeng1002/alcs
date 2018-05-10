package xy.alcs.dto;

import java.util.List;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 21:20 2018-04-30
 */
public class WorkDto {
    private String stuId;   //学号
    private Long cid;     //比赛id
    private String teamId ; //小组id,唯一；
    private String imageName;
    private String fileName;
    private String workContent;
    private Long   wId;


    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getWorkContent() {
        return workContent;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent;
    }

    public Long getwId() {
        return wId;
    }

    public void setwId(Long wId) {
        this.wId = wId;
    }
}
