package com.xiaotu.jdbc.service;


import com.xiaotu.jdbc.bean.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {
    int add(Student student);
    int update(Student student);
    int deleteBySno(String sno);
    List<Map<String, Object>> queryStudentListMap();
    Student queryStudentBySno(String sno);
}
