package com.xiaotu.multidata.service;


import com.xiaotu.multidata.bean.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {
    //写mysql1
    int add(Student student);
    int update(Student student);
    int deleteBySno(String sno);
    List<Map<String, Object>> getAllStudentsFromMaster();

    //读mysql2
    Student queryStudentBySno(String sno);
    List<Map<String, Object>> getAllStudents();

}
