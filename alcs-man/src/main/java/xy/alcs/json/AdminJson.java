package xy.alcs.json;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xy.alcs.common.entity.PageData;
import xy.alcs.common.enums.AlcsErrorCode;
import xy.alcs.common.utils.Result;
import xy.alcs.domain.Admin;
import xy.alcs.dto.StudentDto;
import xy.alcs.service.AdminService;

import javax.annotation.Resource;
import javax.naming.Name;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 20:22 2018-05-09
 */
@Controller
public class AdminJson {
    /**
     * 默认的每页显示的数据
     */
    @Value("${page.limit}")
    private String PAGELIMIT;

    /**
     * 默认查询数据的起始下标
     */
    @Value("${page.offset}")
    private String PAGEOFFSET;

    @Value("${page.now}")
    private String PAGENOW;
    @Resource
    private AdminService adminService;

    @ResponseBody
    @RequestMapping(value = "/admin/list")
    public PageData<Admin> getAdminList(Integer page, Integer rows, String queryParam) {
        Map<String, Object> map = new HashMap<>();
        if (rows == null || rows.intValue() <= 0) {
            rows = Integer.parseInt(PAGELIMIT);
        }
        if (page == null || page.intValue() < 0) {
            page = Integer.parseInt(PAGENOW);
        }
        if (!StringUtils.isEmpty(queryParam)) {
            map = JSONObject.parseObject(queryParam, Map.class);
        }
        map.put("rows", rows);
        map.put("page", page);
        List<Admin> admins = adminService.queryAdminList(map);
        Integer total = adminService.countAdminList(map);
        PageData<Admin> pageData = new PageData();
        pageData.setRows(admins);
        pageData.setTotal(total);
        pageData.setPage(page);
        return pageData;
    }

    @ResponseBody
    @RequestMapping(value = "/admin/add")
    public Result addAdmin(Admin admin) {
        if (admin == null) {
            return Result.buildErrorResult(AlcsErrorCode.PARAM_ISNULL);
        }
        Boolean result = this.verifyAdmin(admin);
        if (result) {
            return adminService.addAdmin(admin);
        }
        return Result.buildErrorResult(AlcsErrorCode.PARAM_ISNULL);

    }

    @ResponseBody
    @RequestMapping(value = "/admin/edit")
    public Result updateAdmin(Admin admin) {
        if (admin == null) {
            return Result.buildErrorResult(AlcsErrorCode.PARAM_ISNULL);
        }
        Boolean aBoolean = adminService.updateAdmin(admin);
        if (aBoolean) {
            return Result.buildSuccessResult();
        }
        return Result.buildErrorResult(AlcsErrorCode.SYSTEM_ERROR);
    }

    @ResponseBody
    @RequestMapping(value = "/admin/del")
    private Result delAdmin(String aids) {
        if (StringUtils.isBlank(aids)) {
            return Result.buildErrorResult(AlcsErrorCode.PARAM_ISNULL);
        }
        List aidList = JSONObject.parseObject(aids, List.class);
        Boolean res = adminService.delAdmin(aidList);
        if (res) {
            return Result.buildSuccessResult();
        }
        return Result.buildErrorResult(AlcsErrorCode.SYSTEM_ERROR);
    }
    @ResponseBody
    @RequestMapping(value = "/admin/getInfo")
    private Result<String> getAdminName(HttpServletRequest request){
        String accountStr = (String) request.getSession().getAttribute("admin_account");
        Admin admin = adminService.queryAdminByAccount(accountStr);
        return Result.buildSuccessResult(admin.getName());

    }
    private Boolean verifyAdmin(Admin admin) {
        if (StringUtils.isBlank(admin.getName())) {
            return false;
        }
        if (StringUtils.isBlank(admin.getAccount())) {
            return false;
        }
        if (StringUtils.isBlank(admin.getPassword())) {
            return false;
        }
        if (null == admin.getPower()) {
            return false;
        }
        return true;
    }


}
