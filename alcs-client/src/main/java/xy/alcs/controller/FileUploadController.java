package xy.alcs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 18:44 2018-04-21
 */
@Controller
public class FileUploadController {

    @RequestMapping(value="/upload/uploadBase64",method= RequestMethod.POST)
    @ResponseBody
    public void testUploadImage(String base64Data){
        System.out.println(base64Data);
    }
}
