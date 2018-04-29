package xy.alcs.service;

import xy.alcs.common.enums.FileTypeEnum;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 17:25 2018-04-29
 */
public interface FileUploadService {

    String uploadFileBase64(String base64Data, String suffix, FileTypeEnum typeEnum);
}
