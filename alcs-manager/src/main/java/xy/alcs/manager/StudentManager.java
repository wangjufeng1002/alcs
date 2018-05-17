package xy.alcs.manager;

import xy.alcs.domain.Student;

import java.util.List;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 22:22 2018-05-17
 */
public interface StudentManager {
    Boolean batchInsertStu(List<Student> studentList);
}
