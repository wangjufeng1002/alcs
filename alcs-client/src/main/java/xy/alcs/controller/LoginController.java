package xy.alcs.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xy.alcs.common.enums.AlcsErrorCode;
import xy.alcs.common.utils.Result;
import xy.alcs.dao.RaterMapper;
import xy.alcs.dao.StudentMapper;
import xy.alcs.domain.Rater;
import xy.alcs.domain.RaterExample;
import xy.alcs.domain.Student;
import xy.alcs.domain.StudentExample;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @RequestMapping("/login")
    @ResponseBody
    public Result login(HttpServletRequest request, HttpServletResponse response, String account, String password) {

        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)) {
            return Result.buildSuccessResult(AlcsErrorCode.PARAM_EXCEPTION);
        }

        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andStuIdEqualTo(account);
        List<Student> students = studentMapper.selectByExample(studentExample);
        if (students != null && students.size() == 1) {
            Student student = students.get(0);
            if (password.equals(student.getStuPassword())) {
                Cookie cookie = new Cookie("user_id", account);
                cookie.setMaxAge(3600 * 24 * 3);
                cookie.setPath("/");
                response.addCookie(cookie);
                request.getSession().setAttribute("stu_id", account);
                return Result.buildSuccessResult(AlcsErrorCode.STUDENT_LOGIN);
            }
            return Result.buildSuccessResult(AlcsErrorCode.PASSWORD_ERROR);
        }
        RaterExample raterExample = new RaterExample();
        raterExample.createCriteria().andRatAccountEqualTo(account);
        List<Rater> raterList = raterMapper.selectByExample(raterExample);
        if (raterList != null && raterList.size() == 1) {
            Rater rater = raterList.get(0);
            if (password.equals(rater.getRatPassword())) {
                Cookie cookie = new Cookie("user_id", account);
                cookie.setMaxAge(3600 * 24 * 3);
                cookie.setPath("/");
                response.addCookie(cookie);
                request.getSession().setAttribute("rat_id", account);
                return Result.buildSuccessResult(AlcsErrorCode.RATER_LOGIN);
            }
            return Result.buildSuccessResult(AlcsErrorCode.PASSWORD_ERROR);
        }
        return Result.buildSuccessResult(AlcsErrorCode.USER_NOT_EXIST);
    }

    @RequestMapping("/outLogin")
    @ResponseBody
    public Result outLogin(HttpServletRequest request) {
        request.getSession().removeAttribute("rat_id");
        request.getSession().removeAttribute("stu_id");
        return Result.buildSuccessResult();
    }

}
