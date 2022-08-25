package com.xiaotu.jdbc.dao;

import com.xiaotu.jdbc.bean.Student;

import java.util.List;
import java.util.Map;

public interface MasterStudentDao {
    List<Map<String, Object>> getAllStudents();
    int add(Student student);
    int update(Student student);
    int deleteBySno(String sno);
    Student queryStudentBySno(String sno);
}
