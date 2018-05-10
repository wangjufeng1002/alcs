package xy.alcs.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xy.alcs.common.enums.AdminPowerTypeEnum;
import xy.alcs.common.enums.AlcsErrorCode;
import xy.alcs.common.exception.BussinessException;
import xy.alcs.common.utils.Result;
import xy.alcs.dao.AdminMapper;
import xy.alcs.domain.Admin;
import xy.alcs.domain.AdminExample;
import xy.alcs.service.AdminService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 20:26 2018-05-09
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Value("${image.Web.Url}")
    String imageWebUrl;

    @Value("${file.Web.Url}")
    String fileWebUrl;


    @Value("${page.now}")
    private String pageNow;

    @Value("${page.limit}")
    private String rows;
    @Resource
    private AdminMapper adminMapper;

    @Override
    public List<Admin> queryAdminList(Map<String, Object> queryMap) {
        if (MapUtils.isEmpty(queryMap)) {
            throw BussinessException.asBussinessException(AlcsErrorCode.PARAM_EXCEPTION);
        }
        Map<String, Object> map = this.buildQueryMap(queryMap);
        List<Admin> admins = adminMapper.selectAdminList(map);
        return admins;
    }

    @Override
    public Integer countAdminList(Map<String, Object> queryMap) {
        if (MapUtils.isEmpty(queryMap)) {
            throw BussinessException.asBussinessException(AlcsErrorCode.PARAM_EXCEPTION);
        }
        Map<String, Object> map = this.buildQueryMap(queryMap);
        return adminMapper.countAdminList(map);
    }

    @Override
    public Boolean updateAdmin(Admin admin) {
        if (admin == null) {
            throw BussinessException.asBussinessException(AlcsErrorCode.PARAM_EXCEPTION);
        }
        return adminMapper.updateByPrimaryKeySelective(admin) >= 1;
    }

    @Override
    public Result addAdmin(Admin admin) {
        if (admin == null) {
            throw BussinessException.asBussinessException(AlcsErrorCode.PARAM_EXCEPTION);
        }
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andAccountEqualTo(admin.getAccount());
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        if (CollectionUtils.isNotEmpty(admins)) {
            return Result.buildErrorResult(AlcsErrorCode.USER_IS_EXIST);
        }
        int insert = adminMapper.insert(admin);
        if (insert >= 1) {
            return Result.buildSuccessResult();
        }
        return Result.buildErrorResult(AlcsErrorCode.SYSTEM_ERROR);
    }

    @Override
    public Boolean delAdmin(List<Integer> aids) {
        if (CollectionUtils.isEmpty(aids)) {
            throw BussinessException.asBussinessException(AlcsErrorCode.PARAM_EXCEPTION);
        }
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andAidIn(aids);
        return adminMapper.deleteByExample(adminExample) >= 1;

    }

    @Override
    public Result<AdminPowerTypeEnum> adminLogin(String account, String password) {
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)) {
            return Result.buildErrorResult(AlcsErrorCode.PARAM_ISNULL);
        }
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andAccountEqualTo(account);

        List<Admin> admins = adminMapper.selectByExample(adminExample);
        if (CollectionUtils.isEmpty(admins)) {
            return Result.buildErrorResult(AlcsErrorCode.USER_NOT_EXIST);
        } else {
            if (admins.get(0).getPassword().equals(password)) {
                if (admins.get(0).getPower().equals(AdminPowerTypeEnum.SUPER_ADMIN.getCode())) {
                    return Result.buildSuccessResult(AdminPowerTypeEnum.SUPER_ADMIN);
                } else {
                    return Result.buildSuccessResult(AdminPowerTypeEnum.ORDINARY_ADMIN);
                }
            }
        }
        return Result.buildErrorResult(AlcsErrorCode.LOGIN_FAIL);
    }

    @Override
    public Admin queryAdminByAccount(String account) {
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andAccountEqualTo(account);
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        if (CollectionUtils.isEmpty(admins)) {
            return null;
        }
        return admins.get(0);
    }

    private Map<String, Object> buildQueryMap(Map<String, Object> queryMap) {
        Map<String, Object> resultMap = new HashMap<>();
        for (Map.Entry item : queryMap.entrySet()) {
            if (item.getKey().toString().equals("page")) {
                if (item.getValue() == null) {
                    resultMap.put("page", Integer.parseInt(this.pageNow));
                }
                resultMap.put("page", queryMap.get("page"));
            } else if (item.getKey().toString().equals("rows")) {
                if (item.getValue() == null) {
                    resultMap.put("limit", Integer.parseInt(this.rows));
                }
                resultMap.put("limit", queryMap.get("rows"));
            } else if (item.getKey().toString().equals("name")) {
                if (item.getValue() == null || StringUtils.isEmpty(item.getValue().toString())) {
                    resultMap.put("name", null);
                } else {
                    resultMap.put("name", (String) item.getValue());
                }
            } else if (item.getKey().toString().equals("account")) {
                if (item.getValue() == null || StringUtils.isEmpty(item.getValue().toString())) {
                    resultMap.put("account", null);
                } else {
                    resultMap.put("account", Integer.parseInt(item.getValue().toString()));
                }
            }
        }
        Integer offset = ((Integer) resultMap.get("page") - 1) * (Integer) resultMap.get("limit");
        resultMap.put("offset", offset);
        return resultMap;
    }
}
