package xy.alcs.controller;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.executor.BaseExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;
import xy.alcs.common.enums.AlcsErrorCode;
import xy.alcs.common.exception.BussinessException;
import xy.alcs.common.utils.Result;
import xy.alcs.domain.Works;
import xy.alcs.dto.WorkDto;
import xy.alcs.service.WorksService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 报告控制器
 *
 * @Author:ju
 * @Description:
 * @Date:Create in 17:58 2018-03-04
 */
@Controller
public class WorksController {

    Logger logger = LoggerFactory.getLogger(WorksController.class);


    @Autowired
    private WorksService worksService;

    @RequestMapping("/work/save")
    @ResponseBody
    public Result saveWork(HttpServletRequest request, WorkDto workDto) {
        String stuId = (String) request.getSession().getAttribute("stu_id");
        workDto.setStuId(stuId);
        Boolean res = worksService.saveWork(workDto);
        if(res){
           return Result.buildSuccessResult();
        }
        return Result.buildErrorResult(AlcsErrorCode.SAVE_WORK_FAIL);
    }
    @RequestMapping("/work/submit")
    @ResponseBody
    public Result submitWork(HttpServletRequest request, WorkDto workDto) {
        String stuId = (String) request.getSession().getAttribute("stu_id");
        workDto.setStuId(stuId);
        Boolean res = worksService.submitWork(workDto);
        if(res){
           return   Result.buildSuccessResult();
        }
        return Result.buildErrorResult(AlcsErrorCode.SUBMIT_WORK_FAIL);
    }

    @RequestMapping("/work/get")
    @ResponseBody
    public Result<WorkDto> getDetail(HttpServletRequest request,Long wId,Long cId){
        if(wId != null){
           return Result.buildSuccessResult(worksService.queryWorkByWid(wId));
        }else{
            if(cId == null){
                throw  BussinessException.asBussinessException(AlcsErrorCode.PARAM_EXCEPTION);
            }
            String stuId = (String) request.getSession().getAttribute("stu_id");
            return Result.buildSuccessResult(worksService.queryWork(cId,stuId));
        }
    }

}
