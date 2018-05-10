package xy.alcs.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.omg.PortableInterceptor.ObjectReferenceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xy.alcs.common.enums.AlcsErrorCode;
import xy.alcs.common.enums.DepartmentTypeEnum;
import xy.alcs.common.exception.BussinessException;
import xy.alcs.common.utils.Result;
import xy.alcs.dao.ClasMapper;
import xy.alcs.dao.CollegeMapper;
import xy.alcs.dao.MajorMapper;
import xy.alcs.dao.StudentMapper;
import xy.alcs.domain.*;
import xy.alcs.dto.StudentDto;
import xy.alcs.service.StudentService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 21:57 2018-04-23
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;
    @Resource
    private CollegeMapper collegeMapper;
    @Resource
    private MajorMapper majorMapper;
    @Resource
    private ClasMapper clasMapper;

    @Value("${page.now}")
    private String pageNow;

    @Value("${page.limit}")
    private String rows;

    @Override
    public StudentDto queryStudentDtoByStuId(String stuId) {
        if (stuId == null) {
            throw BussinessException.asBussinessException(AlcsErrorCode.PARAM_ISNULL);
        }
        return studentMapper.selectStuInfo(stuId);
    }

    @Override
    public List<StudentDto> queryStudentList(Map<String, Object> queryMap) {
        if (MapUtils.isEmpty(queryMap)) {
            throw BussinessException.asBussinessException(AlcsErrorCode.PARAM_ISNULL);
        }
        Map<String, Object> map = this.buildQueryMap(queryMap);
        return studentMapper.selectStuList(map);
    }

    @Override
    public Integer countStudentListTotal(Map<String, Object> queryMap) {
        if (MapUtils.isEmpty(queryMap)) {
            throw BussinessException.asBussinessException(AlcsErrorCode.PARAM_ISNULL);
        }
        Map<String, Object> map = this.buildQueryMap(queryMap);
        return studentMapper.countStuList(map);
    }

    @Override
    public Object queryDepartment(Integer type, Integer id) {
        // 查询学院
        if (DepartmentTypeEnum.COLLEGE.getCode().equals(type)) {
            if (id != null) {
                CollegeExample collegeExample = new CollegeExample();
                collegeExample.createCriteria().andColIdEqualTo(id);
                return collegeMapper.selectByExample(collegeExample);
            } else {
                CollegeExample collegeExample = new CollegeExample();
                collegeExample.createCriteria().andColIdIsNotNull();
                return collegeMapper.selectByExample(collegeExample);
            }

        }
        //查询专业时,id为 学院id
        if (DepartmentTypeEnum.MAJOR.getCode().equals(type)) {
            MajorExample majorExample = new MajorExample();
            majorExample.createCriteria().andCollegeIdEqualTo(id);
            return majorMapper.selectByExample(majorExample);
        }
        //查询班级时，id为专业
        if (DepartmentTypeEnum.CLASS.getCode().equals(type)) {
            ClasExample clasExample = new ClasExample();
            clasExample.createCriteria().andMajorIdEqualTo(id);
            return clasMapper.selectByExample(clasExample);
        }
        return null;
    }

    @Override
    public Result addStudent(Student student) {
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andStuIdEqualTo(student.getStuId());
        List<Student> students = studentMapper.selectByExample(studentExample);
        if (CollectionUtils.isNotEmpty(students)) {
            return Result.buildErrorResult(AlcsErrorCode.USER_IS_EXIST);
        }
        int insert = studentMapper.insert(student);
        if (insert >= 1) {
            return Result.buildSuccessResult();
        } else {
            return Result.buildErrorResult(AlcsErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public Boolean delStudnet(List<String> stuId, List<Long> sids) {
        StudentExample studentExample = new StudentExample();
        if (CollectionUtils.isNotEmpty(sids)) {
            studentExample.createCriteria().andSidIn(sids);
            return studentMapper.deleteByExample(studentExample) > 0;
        }
        if (CollectionUtils.isEmpty(stuId)) {
            throw BussinessException.asBussinessException(AlcsErrorCode.PARAM_EXCEPTION);
        } else {
            studentExample.createCriteria().andStuIdIn(stuId);
            return studentMapper.deleteByExample(studentExample) > 0;
        }
    }

    @Override
    public Boolean updateStudent(Student student) {
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andSidEqualTo(student.getSid());
        return studentMapper.updateByExampleSelective(student, studentExample) >= 1;

    }

    @Override
    public Boolean changePassWord(String account, String password) {
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andStuIdEqualTo(account);

        Student student = new Student();
        student.setStuPassword(password);

        return studentMapper.updateByExampleSelective(student, studentExample) >= 1;

    }

    /**
     *
     * @param queryMap
     * @return
     */
    /**
     * <if test="majorName != null">
     * and major.maj_name LIKE #{majorName}
     * </if>
     * <if test="clasName != null">
     * and clas.cla_name LIKE #{clasName}
     * </if>
     * <if test="colName != null">
     * and college.col_name LIKE #{colName}
     * </if>
     * <if test="majId != null">
     * AND major.maj_id = #{majId}
     * </if>
     * <if test="claId != null">
     * AND clas.cla_id = #{claId}
     * </if>
     * <if test="colId != null">
     * AND college.col_id = #{colId}
     * </if>
     */
    private Map<String, Object> buildQueryMap(Map<String, Object> queryMap) {
        Map<String, Object> resultMap = new HashMap<>();
        for (Map.Entry item : queryMap.entrySet()) {
            if (item.getKey().toString().equals("page")) {
                if (item.getValue() == null) {
                    resultMap.put("page", Integer.parseInt(this.pageNow));
                } else {
                    resultMap.put("page", queryMap.get("page"));
                }
            } else if (item.getKey().toString().equals("rows")) {
                if (item.getValue() == null) {
                    resultMap.put("limit", Integer.parseInt(this.rows));
                } else {
                    resultMap.put("limit", queryMap.get("rows"));
                }
            }
            if ("majorName".equals(item.getKey())) {
                if (item.getValue() == null || StringUtils.isBlank(item.getValue().toString())) {
                    resultMap.put("majorName", null);
                } else {
                    resultMap.put("majorName", item.getValue());
                }
            }
            if ("clasName".equals(item.getKey())) {
                if (item.getValue() == null || StringUtils.isBlank(item.getValue().toString())) {
                    resultMap.put("clasName", null);
                } else {
                    resultMap.put("clasName", item.getValue());
                }
            }
            if ("colName".equals(item.getKey())) {
                if (item.getValue() == null || StringUtils.isBlank(item.getValue().toString())) {
                    resultMap.put("colName", null);
                } else {
                    resultMap.put("colName", item.getValue());
                }
            }
            if ("majId".equals(item.getKey())) {
                if (item.getValue() == null || StringUtils.isEmpty(item.getValue().toString()) || item.getValue().equals(-1)) {
                    resultMap.put("majId", null);
                } else {
                    resultMap.put("majId", item.getValue());
                }
            }
            if ("claId".equals(item.getKey())) {
                if (item.getValue() == null || StringUtils.isEmpty(item.getValue().toString()) || item.getValue().equals(-1)) {
                    resultMap.put("claId", null);
                } else {
                    resultMap.put("claId", item.getValue());
                }
            }
            if ("colId".equals(item.getKey())) {
                if (item.getValue() == null || StringUtils.isEmpty(item.getValue().toString()) || item.getValue().equals(-1)) {
                    resultMap.put("colId", null);
                } else {
                    resultMap.put("colId", item.getValue());
                }
            }
            if ("stuId".equals(item.getKey())) {
                if (item.getValue() == null || StringUtils.isEmpty(item.getValue().toString())) {
                    resultMap.put("stuId", null);
                } else {
                    resultMap.put("stuId", item.getValue());
                }
            }
        }
        Integer offset = ((Integer) resultMap.get("page") - 1) * (Integer) resultMap.get("limit");
        resultMap.put("offset", offset);
        return resultMap;
    }
}
