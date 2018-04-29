package xy.alcs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 14:17 2018-03-17
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/admin/index")
    public String adminIndex(){
        return "admin/index";
    }


    @RequestMapping(value = "/admin/contest")
    public ModelAndView contestListOld(){
        System.out.println("dasdsadsad");
        ModelAndView modelAndView =new ModelAndView("admin/contest");
        return modelAndView;
    }
    @RequestMapping(value = "/admin/allotRater")
    public ModelAndView rater(){
        System.out.println("dasdsadsad");
        ModelAndView modelAndView =new ModelAndView("admin/allotRater");
        return modelAndView;
    }
    @RequestMapping(value = "/admin/scoreStatistics")
    public ModelAndView score(){
        System.out.println("dasdsadsad");
        ModelAndView modelAndView =new ModelAndView("admin/scoreStatistics");
        return modelAndView;
    }


}
