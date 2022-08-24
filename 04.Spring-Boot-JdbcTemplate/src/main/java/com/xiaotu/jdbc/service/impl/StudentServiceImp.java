package com.xiaotu.jdbc.service.impl;


import com.xiaotu.jdbc.bean.Student;
import com.xiaotu.jdbc.dao.StudentDao;
import com.xiaotu.jdbc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("studentService")
public class StudentServiceImp implements StudentService {

    @Autowired
    private StudentDao studentDao;


    @Override
    public int add(Student student) {
        return studentDao.add(student);
    }

    @Override
    public int update(Student student) {
        return studentDao.update(student);
    }

    @Override
    public int deleteBySno(String sno) {
        return studentDao.deleteBySno(sno);
    }

    @Override
    public List<Map<String, Object>> queryStudentListMap() {
        return studentDao.queryStudentsListMap();
    }

    @Override
    public Student queryStudentBySno(String sno) {
        return studentDao.queryStudentBySno(sno);
    }
}
