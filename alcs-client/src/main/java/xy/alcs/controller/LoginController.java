package xy.alcs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xy.alcs.common.enums.AlcsErrorCode;
import xy.alcs.common.utils.Result;
import xy.alcs.dao.RaterMapper;
import xy.alcs.dao.StudentMapper;
import xy.alcs.domain.Student;
import xy.alcs.domain.StudentExample;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 16:48 2018-04-22
 */
@Controller
public class LoginController {

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private RaterMapper raterMapper;

    @RequestMapping("/stuLogin")
    @ResponseBody
    public Result login(HttpServletRequest request,String account, String password){

        StudentExample example = new StudentExample();
        example.createCriteria().andStuIdEqualTo(account);
        List<Student> students = studentMapper.selectByExample(example);
        if(students == null || students.size() == 0){
            return Result.buildErrorResult(AlcsErrorCode.USER_NOT_EXIST);
        }else{
            Student student = students.get(0);
            if(password.equals(student.getStuPassword())){
                request.getSession().setAttribute("stu_id",account);
                return Result.buildSuccessResult(AlcsErrorCode.SUCCESS,student);
            }else{
                return Result.buildErrorResult(AlcsErrorCode.PASSWORD_ERROR);
            }
        }
    }
}
