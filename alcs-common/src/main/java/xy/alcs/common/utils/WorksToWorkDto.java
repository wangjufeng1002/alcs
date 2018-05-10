package xy.alcs.common.utils;

import xy.alcs.domain.Works;
import xy.alcs.dto.WorkDto;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 20:26 2018-05-01
 */
public class WorksToWorkDto {
    //http://localhost:8088/upload/
    public static WorkDto buildWorkDto(Works works, String imgResourcesUrl, String fileResourcesUrl) {
        WorkDto workDto = new WorkDto();
        workDto.setTeamId(works.getTeamId());
        workDto.setWorkContent(works.getCode());
        workDto.setCid(works.getContestId());
        workDto.setwId(works.getwId());
        //拼接图片链接
        workDto.setImageName(imgResourcesUrl + works.getImage());
        //拼接文件链接
        workDto.setFileName(fileResourcesUrl + works.getThesis());
        return workDto;
    }
}
