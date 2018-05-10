package xy.alcs.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import xy.alcs.common.enums.AdminPowerTypeEnum;
import xy.alcs.common.enums.AlcsErrorCode;
import xy.alcs.common.utils.Result;
import xy.alcs.domain.Admin;
import xy.alcs.service.AdminService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 14:17 2018-03-17
 */
@Controller
public class IndexController {

    @Resource
    private AdminService adminService;

    @RequestMapping(value = "/admin/login")
    public ModelAndView login(HttpServletRequest request, String account, String password) {
        ModelAndView modelAndView = new ModelAndView();
        String accountStr = (String) request.getSession().getAttribute("admin_account");
        if (StringUtils.isBlank(accountStr)) {
            Result<AdminPowerTypeEnum> result = adminService.adminLogin(account, password);
            if (result.getCode().equals(AlcsErrorCode.SUCCESS.getCode())) {

                if (result.getData().getCode().equals(AdminPowerTypeEnum.SUPER_ADMIN.getCode())) {
                    request.getSession().setAttribute("admin_account",account);
                    modelAndView.setViewName("redirect:/man/html/admin/indexSuper");
                    return modelAndView;
                } else {
                    request.getSession().setAttribute("admin_account",account);
                    modelAndView.setViewName("redirect:/man/html/admin/indexOrdinary");
                    return modelAndView;
                }
            } else {
                modelAndView.setViewName("redirect:/man/html/admin/loginHtml");
                modelAndView.addObject("msg", "登录失败");
                return modelAndView;
            }
        }

        Admin admin = adminService.queryAdminByAccount(accountStr);
        if (admin == null) {
            modelAndView.setViewName("redirect:/man/html/admin/loginHtml");
        } else {
            if (admin.getPower().equals(AdminPowerTypeEnum.SUPER_ADMIN.getCode())) {
                request.getSession().setAttribute("admin_account",account);
                modelAndView.setViewName("redirect:/man/html/admin/indexSuper");
            } else {
                request.getSession().setAttribute("admin_account",account);
                modelAndView.setViewName("redirect:/man/html/admin/indexOrdinary");
            }
        }
        return modelAndView;
    }

    @RequestMapping(value = "/admin/loginHtml")
    public String adminIndex() {
        return "admin/login";
    }
    @RequestMapping(value = "/admin/indexSuper")
    public String superAdmin(){
        return "admin/indexSuper";
    }
    @RequestMapping(value = "/admin/indexOrdinary")
    public String ordinaryAdmin(){
        return "admin/indexOrdinary";
    }

    @RequestMapping(value = "/admin/contest")
    public ModelAndView contestListOld() {
        System.out.println("dasdsadsad");
        ModelAndView modelAndView = new ModelAndView("admin/contest");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/allotRater")
    public ModelAndView allotRater() {
        System.out.println("dasdsadsad");
        ModelAndView modelAndView = new ModelAndView("admin/allotRater");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/scoreStatistics")
    public ModelAndView score() {
        System.out.println("dasdsadsad");
        ModelAndView modelAndView = new ModelAndView("admin/scoreStatistics");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/work")
    public ModelAndView work() {
        System.out.println("dasdsadsad");
        ModelAndView modelAndView = new ModelAndView("admin/work");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/student")
    public ModelAndView studnet() {
        System.out.println("dasdsadsad");
        ModelAndView modelAndView = new ModelAndView("admin/student");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/admin")
    public ModelAndView admin() {
        System.out.println("dasdsadsad");
        ModelAndView modelAndView = new ModelAndView("admin/admin");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/rater")
    public ModelAndView rater() {
        System.out.println("dasdsadsad");
        ModelAndView modelAndView = new ModelAndView("admin/rater");
        return modelAndView;
    }

}
