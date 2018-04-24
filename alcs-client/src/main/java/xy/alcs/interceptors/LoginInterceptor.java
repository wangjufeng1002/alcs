package xy.alcs.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import xy.alcs.controller.ContestController;
import xy.alcs.dao.RaterMapper;
import xy.alcs.dao.StudentMapper;
import xy.alcs.domain.Rater;
import xy.alcs.domain.RaterExample;
import xy.alcs.domain.Student;
import xy.alcs.domain.StudentExample;
import xy.alcs.manager.RaterManager;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 16:37 2018-04-22
 */
public class LoginInterceptor implements HandlerInterceptor {
    Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private RaterMapper raterMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        try {
            String requestURI = request.getRequestURI();
            if (requestURI.equals("/client/stuLogin/") || requestURI.equals("/client/RatLogin/")) {
                return true;
            } else {
                Object stu_id = request.getSession().getAttribute("stu_id");
                Object rat_id = request.getSession().getAttribute("rat_id");
                if (stu_id == null && rat_id == null) {
                    this.reDirect(request,response);
                    return false;
                } else {
                    StudentExample example = new StudentExample();
                    example.createCriteria().andStuIdEqualTo((String) stu_id);
                    List<Student> students = studentMapper.selectByExample(example);
                    if (students != null && students.size() != 0) {
                        return true;
                    }

                    RaterExample raterExample = new RaterExample();
                    raterExample.createCriteria().andRatAccountEqualTo((String) rat_id);
                    List<Rater> raterList = raterMapper.selectByExample(raterExample);
                    if (raterList != null && raterList.size() != 0) {
                        return true;
                    }
                }
                this.reDirect(request,response);
                return false;
            }
        } catch (Exception e) {
            logger.error("#LoginInterceptor hadppend error：{}", e);

            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {

    }
    //对于请求是ajax请求重定向问题的处理方法
    public static void reDirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取当前请求的路径
        //如果request.getHeader("X-Requested-With") 返回的是"XMLHttpRequest"说明就是ajax请求，需要特殊处理
        if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
            //告诉ajax我是重定向
            response.setHeader("REDIRECT", "REDIRECT");
            response.setHeader("SESSIONSTATUS", "TIMEOUT");
            //告诉ajax我重定向的路径
            response.setHeader("CONTENTPATH", "http://127.0.0.1/graduation/html/login.html");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }else{
            response.sendRedirect("http://127.0.0.1/graduation/html/login.html");
        }
    }
}
