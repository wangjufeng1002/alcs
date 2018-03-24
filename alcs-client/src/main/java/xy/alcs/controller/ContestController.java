package xy.alcs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 22:57 2017-12-06
 *
 */
@Controller
public class ContestController {

    /**
     * 主页面
     * @return
     */
    @RequestMapping("/master")
    public String master(){
        System.out.println("home==========================================");
        return "home";
    }

    @RequestMapping("/myContest")
    public ModelAndView myContest(){
        ModelAndView modelAndView = new ModelAndView();
        Map map = new HashMap();
        map.put("status","1");
        modelAndView.addObject(map);
        modelAndView.setViewName("myContest");
        return modelAndView;
    }

}
