package xy.alcs.interceptors;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import xy.alcs.dao.RaterMapper;
import xy.alcs.dao.StudentMapper;
import xy.alcs.domain.Rater;
import xy.alcs.domain.RaterExample;
import xy.alcs.domain.Student;
import xy.alcs.domain.StudentExample;
import xy.alcs.service.AdminService;

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
    private AdminService adminService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        try {
            String requestURI = request.getRequestURI();
            if (requestURI.equals("/")||requestURI.equals("/man/html/admin/login") || requestURI.equals("/man/html/admin/loginHtml")) {
                return true;
            } else {
                String account = (String) request.getSession().getAttribute("admin_account");
                if (account == null) {
                    response.sendRedirect("/man/html/admin/loginHtml");
                    return false;
                }
                return true;
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
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            //告诉ajax我是重定向
            response.setHeader("REDIRECT", "REDIRECT");
            response.setHeader("SESSIONSTATUS", "TIMEOUT");
            //告诉ajax我重定向的路径
            response.setHeader("CONTENTPATH", "http://127.0.0.1/graduation/html/login.html");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } else {
            response.sendRedirect("http://127.0.0.1/graduation/html/login.html");
        }
    }
}
