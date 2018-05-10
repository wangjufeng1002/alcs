package xy.alcs.service;


import xy.alcs.dto.RaterWorkDto;
import xy.alcs.dto.WorkDto;
import xy.alcs.dto.WorkListDto;

import java.util.List;
import java.util.Map;


/**
 * 作品service
 *
 * @Author:ju
 * @Description:
 * @Date:Create in 18:32 2018-03-04
 */
public interface WorksService {
    /**
     * 保存作品
     *
     * @param workDto
     * @return
     */
    Boolean saveWork(WorkDto workDto);

    /**
     * 提交作品
     *
     * @param workDto
     * @return
     */
    Boolean submitWork(WorkDto workDto);

    /**
     * 查询作品
     *
     * @param cid 竞赛id
     * @param sId 学生id
     * @return
     */
    WorkDto queryWork(Long cid, String sId);

    /**
     * 根据workId查询作品
     *
     * @param wId 作品id
     * @return
     */
    WorkDto queryWorkByWid(Long wId);

    /**
     * 查询作品列表
     *
     * @param queryParamMap 查询作品参数Map
     * @return
     */
    List<WorkListDto> listWork(Map<String, Object> queryParamMap);

    /**
     * 统计作品总数
     *
     * @param queryParamMap 查询作品参数Map
     * @return
     */
    Integer countWork(Map<String, Object> queryParamMap);

    /**
     * 批量删除
     *
     * @param wids
     * @return
     */
    Boolean deleteWork(List<Long> wids);

    /**
     * 查询作品列表
     *
     * @param queryParamMap 查询作品参数Map
     * @return
     */
    List<RaterWorkDto> listRaterWorks(Map<String, Object> queryParamMap);

    /**
     * 统计分配给评委的作品列表
     *
     * @param queryParamMap
     * @return
     */
    Integer countRaterWorks(Map<String, Object> queryParamMap);

    /**
     * 查询分配给评委的某比赛详情
     *
     * @param queryParamMap
     * @return
     */
    RaterWorkDto queryRaterWorkDetail(Map<String, Object> queryParamMap);

    /**
     * 查询分数统计完成列表
     * @param queryParamMap
     * @return
     */
    List<WorkListDto> queryWorkDtoByCid(Map<String, Object> queryParamMap);

    /**
     * 统计分数统计完成列表
     * @param queryParamMap
     * @return
     */
    Integer  countWorkDtoByCid(Map<String, Object> queryParamMap);

}
