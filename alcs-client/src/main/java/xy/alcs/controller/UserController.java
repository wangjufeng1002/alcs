package xy.alcs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xy.alcs.common.enums.AlcsErrorCode;
import xy.alcs.common.utils.Result;
import xy.alcs.dto.StudentDto;
import xy.alcs.service.StudentService;

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

    @RequestMapping(value = "/user/getStuInfo")
    @ResponseBody
    public Result<StudentDto> getStuUserInfo(HttpServletRequest request, HttpServletResponse response){
        String stuId =  (String)request.getSession().getAttribute("stu_id");
        StudentDto studentDto = studentService.queryStudentDtoByStuId(stuId);
        if(studentDto == null){
            return  Result.buildErrorResult(AlcsErrorCode.USER_NOT_EXIST);
        }
        return Result.buildSuccessResult(AlcsErrorCode.SUCCESS,studentDto);
    }
}
