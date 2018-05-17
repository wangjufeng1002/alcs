package xy.alcs.service.impl;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;
import xy.alcs.common.enums.FileTypeEnum;
import xy.alcs.service.FileUploadService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 17:26 2018-04-29
 */
@Service("fileUploadService")
public class FileUploadServiceImpl implements FileUploadService {

    @Value("${image.Web.Url}")
    String imageWebUrl;

    @Value("${file.Web.Url}")
    String fileWebUrl;

    @Override
    public String uploadFileBase64(String base64Data, String suffix, FileTypeEnum typeEnum) {

        try {
            if (StringUtils.isEmpty(base64Data)) {
                return null;
            }
            BASE64Decoder decoder = new BASE64Decoder();
            String fileName = UUID.randomUUID().toString() + suffix;
            String filePath = "";
            //图片
            if (typeEnum.getCode().equals(FileTypeEnum.IMAGE.getCode())) {
                filePath = imageWebUrl + fileName;
            }
            //文档
            if (typeEnum.getCode().equals(FileTypeEnum.DOCUMENT.getCode())) {
                filePath = fileWebUrl + fileName;
            }
            byte[] buffer = decoder.decodeBuffer(base64Data);

            Client client = new Client();

            WebResource resource = client.resource(filePath);

            resource.put(String.class, buffer);

            return fileName;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
