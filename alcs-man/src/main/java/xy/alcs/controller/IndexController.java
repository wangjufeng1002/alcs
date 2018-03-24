package xy.alcs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @RequestMapping(value = "/admin/contestList")
    public String contestList(){
        return "admin/contestTable";
    }
    @RequestMapping(value = "/admin/allotRater")
    public String rater(){
        return "admin/allotRater";
    }
}
