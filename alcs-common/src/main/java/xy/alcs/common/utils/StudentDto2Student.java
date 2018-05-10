package xy.alcs.common.utils;

import xy.alcs.domain.Student;
import xy.alcs.dto.StudentDto;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 16:25 2018-05-09
 */
public class StudentDto2Student {

    public static Student buildStudent(StudentDto studentDto) {
        Student student = new Student();
        student.setSid(studentDto.getSid());
        student.setStuId(studentDto.getStuId());
        student.setStuClaId(studentDto.getClaId());
        student.setStuMajId(studentDto.getMajId());
        student.setStuColId(studentDto.getColId());
        student.setStuName(studentDto.getStuName());
        student.setStuGender(studentDto.getStuGender());
        student.setStuPassword(studentDto.getStuPassword());
        return student;
    }
}
