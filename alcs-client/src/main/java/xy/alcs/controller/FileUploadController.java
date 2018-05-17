package xy.alcs.controller;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;
import xy.alcs.common.enums.AlcsErrorCode;
import xy.alcs.common.enums.FileTypeEnum;
import xy.alcs.common.utils.Result;
import xy.alcs.service.FileUploadService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 18:44 2018-04-21
 */
@Controller
public class FileUploadController {
    Logger logger = LoggerFactory.getLogger(ContestController.class);

    @Resource
    private FileUploadService fileUploadService;


    @RequestMapping(value = "/upload/image", method = RequestMethod.POST)
    @ResponseBody
    public Result testUploadImage(String base64Data) {
        //data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAMABVQDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDQfRtAheIXGm2EaySLGD9kVjk+gAye547AmpZfDekxNzpOmsueSIEzj6Y/rTNdj1QTRNaWcssaYIMTAksSV+6Pm7/r+NM0JrzU7j
        logger.debug("上传文件的数据："+base64Data);
        String dataPrix = "";
        String data = "";
        logger.debug("对数据进行判断");
        if(StringUtils.isBlank(base64Data)){
             return Result.buildErrorResult(AlcsErrorCode.IMAGE_UPLOAD_FAIL);
        }else{
            String [] d = base64Data.split("base64,");
            if(d!= null && d.length == 2){
                dataPrix = d[0];
                data = d[1];
            }else{
                return Result.buildErrorResult(AlcsErrorCode.IMAGE_UPLOAD_FAIL);
            }
        }
        logger.debug("对数据进行解析，获取文件名和流数据");
        String suffix = "";
        if("data:image/jpeg;".equalsIgnoreCase(dataPrix)){//data:image/jpeg;base64,base64编码的jpeg图片数据
            suffix = ".jpg";
        } else if("data:image/x-icon;".equalsIgnoreCase(dataPrix)){//data:image/x-icon;base64,base64编码的icon图片数据
            suffix = ".ico";
        } else if("data:image/gif;".equalsIgnoreCase(dataPrix)){//data:image/gif;base64,base64编码的gif图片数据
            suffix = ".gif";
        } else if("data:image/png;".equalsIgnoreCase(dataPrix)){//data:image/png;base64,base64编码的png图片数据
            suffix = ".png";
        }else{
            return Result.buildErrorResult(AlcsErrorCode.IMAGE_UPLOAD_FAIL);
        }
        String path = fileUploadService.uploadFileBase64(data, suffix, FileTypeEnum.IMAGE);
        return Result.buildSuccessResult(path);

    }

    @RequestMapping(value = "/upload/file", method = RequestMethod.POST)
    @ResponseBody
    public Result uploadFile(String base64Data){
        //data:application/vnd.openxmlformats-officedocument.wordprocessingml.document;base64,
        logger.debug("上传文件的数据："+base64Data);
        String dataPrix = "";
        String data = "";
        logger.debug("对数据进行判断");
        if(StringUtils.isBlank(base64Data)){
            return Result.buildErrorResult(AlcsErrorCode.FILE_UPLOAD_FAIL);
        }else{
            String [] d = base64Data.split("base64,");
            if(d!= null && d.length == 2){
                dataPrix = d[0];
                data = d[1];
            }else{
                return Result.buildErrorResult(AlcsErrorCode.FILE_UPLOAD_FAIL);
            }
        }
        String suffix = "";
        if(dataPrix.equals("data:application/octet-stream;")|| dataPrix.equals("data:application/vnd.openxmlformats-officedocument.wordprocessingml.document;")){
            suffix = ".docx";
        }

        else if(dataPrix.equals("data:application/msword;")){
            suffix = ".doc";
        }
        else if(dataPrix.equals("data:application/pdf;")){
            suffix = ".pdf";
        }
        else{
            return Result.buildErrorResult(AlcsErrorCode.FILE_UPLOAD_FAIL);
        }
        logger.debug("对数据进行解析，获取文件名和流数据");
        String fileName = fileUploadService.uploadFileBase64(data, suffix, FileTypeEnum.DOCUMENT);
        return Result.buildSuccessResult(fileName);
    }
}
