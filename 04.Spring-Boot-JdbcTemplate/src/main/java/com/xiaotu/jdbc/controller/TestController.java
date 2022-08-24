package com.xiaotu.jdbc.controller;


import com.xiaotu.jdbc.bean.Student;
import com.xiaotu.jdbc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class TestController {
    @Autowired
    private StudentService studentService;

    @RequestMapping( value = "/querystudent", method = RequestMethod.GET)
    public Student queryStudentBySno(String sno) {
        return studentService.queryStudentBySno(sno);
    }

    @RequestMapping(value = "/queryallstudent")
    public List<Map<String,Object>> queryAllStudent(){
        return studentService.queryStudentListMap();
    }

    @RequestMapping(value = "/addstudent",method = RequestMethod.GET)
    public int saveStudent(String sno,String name,String sex){
        Student student = new Student();
        student.setSno(sno);
        student.setName(name);
        student.setSex(sex);
        return studentService.add(student);
    }

    @RequestMapping(value = "/deletestudent",method = RequestMethod.GET)
    public int deleteStudentBySno(String sno){
        return studentService.deleteBySno(sno);
    }

}
