package xy.alcs.service;

import xy.alcs.common.utils.Result;

import java.io.InputStream;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 21:35 2018-05-17
 */
public interface ExcalService {
   Result importStuInfo(InputStream inputStream,String fileName);
}
