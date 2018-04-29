package xy.alcs.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.ResponseBody;
import xy.alcs.dao.StudentCompetitionMapper;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 23:42 2017-12-21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-config.xml"})
public class TestJunit {
    @Resource
    private StudentCompetitionMapper studentCompetitionMapper;

    @Test
    public void test(){
        Map queryMap = new HashMap();
        queryMap.put("sId","04141063");
        queryMap.put("workCommit",0);
        List list = studentCompetitionMapper.selectContestByWorkCommitStatus(queryMap);
        System.out.println(list.size());
    }
}
