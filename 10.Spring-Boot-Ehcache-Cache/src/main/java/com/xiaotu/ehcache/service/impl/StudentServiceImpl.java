package com.xiaotu.ehcache.service.impl;


import com.xiaotu.ehcache.bean.Student;
import com.xiaotu.ehcache.mapper.StudentMapper;
import com.xiaotu.ehcache.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

