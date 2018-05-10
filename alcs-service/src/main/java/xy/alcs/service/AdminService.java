package xy.alcs.service;

import xy.alcs.common.enums.AdminPowerTypeEnum;
import xy.alcs.common.utils.Result;
import xy.alcs.domain.Admin;

import java.util.List;
import java.util.Map;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 20:24 2018-05-09
 */

public interface AdminService {
    List<Admin> queryAdminList(Map<String, Object> queryMap);

    Integer countAdminList(Map<String, Object> queryMap);

    Boolean updateAdmin(Admin admin);

    Result addAdmin(Admin admin);

    Boolean delAdmin(List<Integer> aids);

    Result<AdminPowerTypeEnum> adminLogin(String account, String password);


    Admin queryAdminByAccount(String account);
}
