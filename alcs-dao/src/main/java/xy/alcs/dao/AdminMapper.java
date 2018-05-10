package xy.alcs.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import xy.alcs.domain.Admin;
import xy.alcs.domain.AdminExample;

public interface AdminMapper {
    int countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer aid);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer aid);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);


    List<Admin> selectAdminList(Map<String, Object> queryMap);

    Integer countAdminList(Map<String, Object> queryMap);
}