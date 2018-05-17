package xy.alcs.service.impl;

import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;
import xy.alcs.common.enums.AlcsErrorCode;
import xy.alcs.common.exception.BussinessException;
import xy.alcs.common.utils.Result;
import xy.alcs.dao.ClasMapper;
import xy.alcs.dao.CollegeMapper;
import xy.alcs.dao.MajorMapper;
import xy.alcs.dao.StudentMapper;
import xy.alcs.domain.*;
import xy.alcs.manager.StudentManager;
import xy.alcs.service.ExcalService;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 21:36 2018-05-17
 */
@Service("excalService")
public class ExcalServiceImpl implements ExcalService {
    private static Logger logger = LoggerFactory.getLogger(ContestServiceImpl.class);

    @Resource
    private StudentMapper studentMapper;
    @Resource
    private ClasMapper clasMapper;
    @Resource
    private MajorMapper majorMapper;
    @Resource
    private CollegeMapper collegeMapper;
    @Resource
    private StudentManager studentManager;

    @Override
    public Result importStuInfo(InputStream inputStream, String fileName) {
        boolean is03Excel = fileName.matches("^.+\\.(?i)(xls)$");
        Workbook workbook = null;
        try {
            workbook = is03Excel ? new HSSFWorkbook(inputStream) : new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            logger.error("#ExcalServiceImpl importStuInfo error:{}", e);
            throw BussinessException.asBussinessException(AlcsErrorCode.IMPORT_ERROR);
        }
        //查询所有学院
        CollegeExample example = new CollegeExample();
        example.createCriteria().andColIdIsNotNull();
        List<College> collegeList = collegeMapper.selectByExample(example);
        //查询所有专业
        MajorExample majorExample = new MajorExample();
        majorExample.createCriteria().andMajIdIsNotNull();
        List<Major> majorList = majorMapper.selectByExample(majorExample);
        //查询所有班级
        ClasExample clasExample = new ClasExample();
        clasExample.createCriteria().andClaIdIsNotNull();
        List<Clas> clasList = clasMapper.selectByExample(clasExample);

        Sheet sheet = workbook.getSheetAt(0);
        int rows = sheet.getPhysicalNumberOfRows();
        //姓名
        String name = null;
        String stuId = null;
        String stuName = null;
        String genger = null;
        String collegeName = null;
        String majorName = null;
        String clasName = null;
        List<Student> studentList = new ArrayList<>();
        if (rows > 1) {
            for (int i = 1; i < rows; i++) {
                //读取一行
                Row row = sheet.getRow(i);
                //读取姓名
                Cell cellStuName = row.getCell(0);
                stuName = cellStuName.getStringCellValue();
                //读取学号
                Cell cellStuId = row.getCell(2);
                stuId = cellStuId.getStringCellValue();
                //读取性别
                Cell cellGender = row.getCell(6);
                genger = cellGender.getStringCellValue();
                //读取部门信息：
                Cell cellDepart = row.getCell(5);
                String depart = cellDepart.getStringCellValue();
                String[] departs = depart.split("-");
                //学院名称
                collegeName = departs[2];
                //专业名称
                majorName = departs[4].substring(0, 2);
                //班级名称
                clasName = departs[4];
                Student student = new Student();
                student.setStuId(stuId);
                student.setStuName(stuName);
                student.setStuPassword(stuId);
                student.setStuGender(this.getGender(genger));
                student.setStuClaId(this.getClaId(clasList, clasName));
                student.setStuMajId(this.getMajorId(majorList, majorName));
                student.setStuColId(this.getCollegeId(collegeList, collegeName));
                studentList.add(student);
            }
            Boolean res = studentManager.batchInsertStu(studentList);
            if(res){
                return Result.buildSuccessResult();
            }
            return Result.buildErrorResult(AlcsErrorCode.IMPORT_ERROR);
        }
        return Result.buildErrorResult(AlcsErrorCode.EXCAL_IS_NULL);
    }

    private Integer getCollegeId(List<College> colleges, String collegeName) {

        for (College college : colleges) {
            if (college.getColName().equals(collegeName)) {
                return college.getColId();
            }
            continue;
        }
        throw BussinessException.asBussinessException(AlcsErrorCode.COLLEGE_NOT_EXIST);
    }

    private Integer getMajorId(List<Major> majors, String majorName) {

        for (Major major : majors) {
            if (major.getMajName().equals(majorName)) {
                return major.getMajId();
            }
            continue;
        }
        throw BussinessException.asBussinessException(AlcsErrorCode.MAJOR_NOT_EXIST);
    }

    private Integer getClaId(List<Clas> clasList, String clasName) {

        for (Clas clas : clasList) {
            if (clas.getClaName().equals(clasName)) {
                return clas.getClaId();
            }
            continue;
        }
        throw BussinessException.asBussinessException(AlcsErrorCode.CLA_NOT_EXIST);
    }

    private Integer getGender(String genderName) {
        if ("男".equals(genderName)) {
            return 1;
        }
        if ("女".equals(genderName)) {
            return 0;
        }
        return null;
    }
}
