package xy.alcs.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xy.alcs.common.enums.AlcsErrorCode;
import xy.alcs.common.enums.UserTypeEnum;
import xy.alcs.common.utils.Result;
import xy.alcs.domain.Rater;
import xy.alcs.dto.StudentDto;
import xy.alcs.service.RaterService;
import xy.alcs.service.StudentService;
import xy.alcs.service.WorksService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 21:30 2018-04-23
 */
@Controller
public class UserController {

    @Resource
    private StudentService studentService;
    @Resource
    private RaterService raterService;

    @RequestMapping(value = "/user/getStuInfo")
    @ResponseBody
    public Result<StudentDto> getStuUserInfo(HttpServletRequest request, HttpServletResponse response) {
        String stuId = (String) request.getSession().getAttribute("stu_id");
        StudentDto studentDto = studentService.queryStudentDtoByStuId(stuId);
        if (studentDto == null) {
            return Result.buildErrorResult(AlcsErrorCode.USER_NOT_EXIST);
        }
        return Result.buildSuccessResult(AlcsErrorCode.SUCCESS, studentDto);
    }

    @RequestMapping(value = "/user/getRaterInfo")
    @ResponseBody
    public Result<Rater> getRaterUserInfo(HttpServletRequest request, HttpServletResponse response) {
        String ratId = (String) request.getSession().getAttribute("rat_id");
        Rater rater = raterService.queryRaterInfo(ratId);
        if (rater == null) {
            return Result.buildErrorResult(AlcsErrorCode.USER_NOT_EXIST);
        }
        return Result.buildSuccessResult(AlcsErrorCode.SUCCESS, rater);
    }

    @RequestMapping(value = "/user/changePass")
    @ResponseBody
    public Result changePass(HttpServletRequest request, Integer type, String password) {
        if (type == null || StringUtils.isBlank(password)) {
            return Result.buildErrorResult(AlcsErrorCode.PARAM_ISNULL);
        }
        Boolean res = false;
        //更改学生密码
        if (type.equals(UserTypeEnum.STUDENT.getCode())) {
            String account = (String) request.getSession().getAttribute("stu_id");
            res = studentService.changePassWord(account, password);
        } else if (type.equals(UserTypeEnum.RATER.getCode())) {
            String account = (String) request.getSession().getAttribute("rat_id");
            res = raterService.changePassWord(account, password);
        }
        if(res){
            request.getSession().removeAttribute("stu_id");
            request.getSession().removeAttribute("rat_id");
            return Result.buildSuccessResult();
        }
        return  Result.buildErrorResult(AlcsErrorCode.SYSTEM_ERROR);
    }
}
