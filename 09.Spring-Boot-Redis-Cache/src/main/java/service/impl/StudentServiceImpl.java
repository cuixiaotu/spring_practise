package service.impl;

import com.xiaotu.rediscache.bean.Student;
import com.xiaotu.rediscache.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.StudentService;

@Service("studentService")
public class StudentServiceImpl  implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student update(Student student) {
        studentMapper.update(student);
        return studentMapper.queryStudentBySno(student.getSno());
    }

    @Override
    public void deleteStudentBySno(String sno) {
        studentMapper.deleteStudentBySno(sno);
    }

    @Override
    public Student queryStudentBySno(String sno) {
        return studentMapper.queryStudentBySno(sno);
    }
}
