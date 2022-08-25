package com.xiaotu.jdbc.service.impl;

import com.xiaotu.jdbc.dao.MasterStudentDao;
import com.xiaotu.jdbc.dao.SlaveStudentDao;
import com.xiaotu.jdbc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("studentService")
public class StudentServiceImp implements StudentService{

    @Autowired
    private MasterStudentDao masterStudentDao;

    @Autowired
    private SlaveStudentDao slaveStudentDao;

    @Override
    public List<Map<String, Object>> getAllStudentsFromMaster() {
        return masterStudentDao.getAllStudents();
    }

    @Override
    public List<Map<String, Object>> getAllStudentsFromSlave() {
        return slaveStudentDao.getAllStudents();
    }
}