package xy.alcs.json;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xy.alcs.dao.ContestMapper;
import xy.alcs.domain.Contest;

import java.util.List;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 21:33 2017-12-06
 */
@Controller
@RequestMapping("/client/json")
public class ContestJson {

    @Autowired
    private ContestMapper contestMapper;

    @RequestMapping("/listContest")
    @ResponseBody
    public List<Contest> listContest(){
         List<Contest> contests = contestMapper.selectList();
        return contests;
    }
}
