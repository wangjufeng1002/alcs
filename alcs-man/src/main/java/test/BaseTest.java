package test;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xy.alcs.dao.RaterMapper;
import xy.alcs.domain.Rater;
import xy.alcs.domain.RaterExample;
import xy.alcs.dto.RaterDto;
import xy.alcs.service.RaterService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 15:47 2018-03-25
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-config.xml")
public class BaseTest {

    @Resource
    private RaterService raterService;

    @Resource
    private RaterMapper raterMapper;

    @Test
    public void test(){
        for (int i = 0 ;i< 10;i++){
            Rater rater = new Rater();
            rater.setRatAccount("22222220"+i);
            rater.setRatName("测试测试2"+i);
            rater.setRatPassword("123456");
            raterService.addRater(rater);
        }
      /*  RaterExample raterExample = new RaterExample();
        Map map = new HashMap();
        map.put("offset",0);
        map.put("limit",10);
        List<RaterDto> list = raterMapper.selectRaterByParam(map);
        String s = JSONObject.toJSONString(list);
        System.out.println(s);*/
    }


}
