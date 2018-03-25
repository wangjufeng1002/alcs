package test;

import com.alibaba.fastjson.JSONObject;
import xy.alcs.dto.RaterDto;

import java.util.List;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 18:17 2018-03-25
 */
public class Test {

    @org.junit.Test
    public void  test(){

        String json = "[{\"ratAccount\":\"111111100\",\"ratName\":\"测试\",\"rid\":11},{\"ratAccount\":\"111111101\",\"ratName\":\"测试\",\"rid\":12},{\"ratAccount\":\"111111102\",\"ratName\":\"测试\",\"rid\":13},{\"ratAccount\":\"111111103\",\"ratName\":\"测试\",\"rid\":14},{\"ratAccount\":\"111111104\",\"ratName\":\"测试\",\"rid\":15},{\"ratAccount\":\"111111105\",\"ratName\":\"测试\",\"rid\":16},{\"ratAccount\":\"111111106\",\"ratName\":\"测试\",\"rid\":17},{\"ratAccount\":\"111111107\",\"ratName\":\"测试\",\"rid\":18},{\"ratAccount\":\"111111108\",\"ratName\":\"测试\",\"rid\":19},{\"ratAccount\":\"111111109\",\"ratName\":\"测试\",\"rid\":20}]";

        List<RaterDto> raterDtos = JSONObject.parseArray(json, RaterDto.class);

    }
}

