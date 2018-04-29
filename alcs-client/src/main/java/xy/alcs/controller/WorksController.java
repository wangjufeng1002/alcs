package xy.alcs.controller;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xy.alcs.common.enums.AlcsErrorCode;
import xy.alcs.common.exception.BussinessException;
import xy.alcs.common.utils.Result;
import xy.alcs.domain.Works;
import xy.alcs.service.WorksService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 报告控制器
 *
 * @Author:ju
 * @Description:
 * @Date:Create in 17:58 2018-03-04
 */
@Controller
public class WorksController {

    Logger logger = LoggerFactory.getLogger(WorksController.class);


    @Autowired
    private WorksService worksService;



}
