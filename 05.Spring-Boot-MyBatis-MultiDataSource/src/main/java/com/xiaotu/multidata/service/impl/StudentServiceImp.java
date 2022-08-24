package com.xiaotu.multidata.service.impl;

import com.xiaotu.multidata.mysqldao.MasterStudentMapper;
//import com.xiaotu.multidata.mysqldao.SlaveStudentMapper;
import com.xiaotu.mybatis.bean.Student;
import com.xiaotu.mybatis.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("studentService")
public class StudentServiceImp implements StudentService {

    @Autowired
    private MasterStudentMapper masterStudentMapper;
/*    @Autowired
    private SlaveStudentMapper slaveStudentMapper;*/


    @Override
    public int add(Student student) {
        return masterStudentMapper.add(student);
    }

    @Override
    public int update(Student student) {
        return masterStudentMapper.update(student);
    }

    @Override
    public int deleteBySno(String sno) {
        return masterStudentMapper.deleteBySno(sno);
    }

    @Override
    public Student queryStudentBySno(String sno) {
       return masterStudentMapper.queryStudentBySno(sno);
    }

    //    @Override
//    public Student queryStudentBySno(String sno) {
//        return slaveStudentMapper.queryStudentBySno(sno);
//    }
//
//
//    public List<Map<String, Object>> getAllStudentsFromSlave() {
//        return slaveStudentMapper.getAllStudents();
//    }

    public List<Map<String, Object>> getAllStudentsFromMaster() {
        return masterStudentMapper.getAllStudents();
    }

}
