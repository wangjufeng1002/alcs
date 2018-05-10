package xy.alcs.json;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xy.alcs.common.entity.PageData;
import xy.alcs.common.enums.AlcsErrorCode;
import xy.alcs.common.exception.BussinessException;
import xy.alcs.common.utils.Result;
import xy.alcs.common.utils.StudentDto2Student;
import xy.alcs.dto.StudentDto;
import xy.alcs.service.StudentService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 15:55 2018-05-09
 */
@Controller
public class StudentJson {
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
    private StudentService studentService;


    @ResponseBody
    @RequestMapping(value = "/student/list")
    public PageData<StudentDto> getStudentList(Integer page, Integer rows, String queryParam) {
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
        List<StudentDto> studentDtoList = studentService.queryStudentList(map);
        Integer total = studentService.countStudentListTotal(map);
        PageData<StudentDto> pageData = new PageData();
        pageData.setRows(studentDtoList);
        pageData.setTotal(total);
        pageData.setPage(page);
        return pageData;
    }

    /**
     * 获取 班级，专业。学院
     *
     * @param type 类型
     * @param id   主键
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/student/department")
    public Result<Object> getStudentDepartment(Integer type, Integer id) {
        if (type == null) {
            return Result.buildErrorResult(AlcsErrorCode.PARAM_ISNULL);
        }
        Object res = studentService.queryDepartment(type, id);
        return Result.buildSuccessResult(res);
    }


    @ResponseBody
    @RequestMapping(value = "/student/add")
    public Result addStudent(StudentDto studentDto) {
        if(studentDto == null){
            return Result.buildErrorResult(AlcsErrorCode.PARAM_ISNULL);
        }
        if(!this.verifyStudentParam(studentDto)){
            return Result.buildErrorResult(AlcsErrorCode.PARAM_ISNULL);
        }
        return studentService.addStudent(StudentDto2Student.buildStudent(studentDto));
    }

    @ResponseBody
    @RequestMapping(value = "/student/del")
    public Result delStudent(String stuIds, String sids) {
        if (StringUtils.isEmpty(stuIds) && StringUtils.isEmpty(sids)) {
            throw BussinessException.asBussinessException(AlcsErrorCode.PARAM_EXCEPTION);
        }
        List<String> stuIdlist = null;
        List<Long> sidList = null;
        if(StringUtils.isNotBlank(stuIds)){
            stuIdlist = JSONObject.parseObject(stuIds, List.class);
        }
        if(StringUtils.isNotBlank(sids)){
            sidList = JSONObject.parseObject(sids, List.class);
        }
        Boolean res = studentService.delStudnet(stuIdlist, sidList);
        if (res) {
            return Result.buildSuccessResult();
        }
        return Result.buildErrorResult(AlcsErrorCode.SYSTEM_ERROR);
    }

    @ResponseBody
    @RequestMapping(value = "/student/edit")
    public Result editStudent(StudentDto studentDto) {
        Boolean aBoolean = studentService.updateStudent(StudentDto2Student.buildStudent(studentDto));
        if (aBoolean) {
            return Result.buildSuccessResult();
        }
        return Result.buildErrorResult(AlcsErrorCode.SYSTEM_ERROR);
    }
    private boolean verifyStudentParam(StudentDto studentDto){
        if(studentDto.getStuGender() == null){
            return false;
        }
        if(StringUtils.isEmpty(studentDto.getStuId())){
            return false;
        }
        if(StringUtils.isEmpty(studentDto.getStuName())){
            return false;
        }
        if(StringUtils.isEmpty(studentDto.getStuPassword())){
            return false;
        }
        if(studentDto.getColId() == null){
            return false;
        }
        if(studentDto.getMajId() == null){
            return false;
        }
        if(studentDto.getClaId() == null){
            return false;
        }
        return true;

    }
}
